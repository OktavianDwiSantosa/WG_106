package main;

import required.Figure;
import required.Thing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class BattleSystem {
    private static BattleSystem battleSystem;
    private Scanner sc = new Scanner(System.in);
    private boolean isBattleStopped = false;

    // untuk menyimpan data arraylist hero & enemy yang diambil dari gameEngine
    private final ArrayList<Thing> lifeHero = new ArrayList<>();
    private final ArrayList<Thing> lifeEnemy = new ArrayList<>();

    // untuk menyimpan hero atau enemy yang mati di tengah pertempuran
    private final ArrayList<Thing> deadHero = new ArrayList<>();
    private final ArrayList<Thing> deadEnemy = new ArrayList<>();

    // karena ini hanya dipakai internal untuk BattleSystem, maka gunakan ArrayList<Skill>
    private final ArrayList<Skill> readySkill = new ArrayList<>();

    private BattleSystem() {}

    // Singleton
    public static BattleSystem getBattleSystem() {
        if (battleSystem == null) {
            battleSystem = new BattleSystem();
        }
        return battleSystem;
    }

    // Methods
    public void addSkill(Skill tempSkill) { readySkill.add(tempSkill); }

    public void enemyMenu() {
        for (Thing objEnemy : lifeEnemy) {
            ArrayList<Thing> tempArrSkill = objEnemy.getOneClass("Skill");

            // set nilai max berdasarkan banyaknya skill milik objEnemy
            int max = tempArrSkill.size();

            // lakukan math random untuk memilih skill enemy
            Skill tempSkill = (Skill) tempArrSkill.get((int) (Math.random() * (max - 1)));

            // pilih target skill, kemudian tambahkan ke arrSkill
            addSkill(enemySelectTarget(tempSkill));
        }
    }

    private Skill enemySelectTarget(Skill tempSkill) {
        Figure target;

        if (tempSkill.isTargetFoe()) {
            target = enemyTarget(lifeHero);
        } else {
            target = enemyTarget(lifeEnemy);
        }

        tempSkill.setTempTarget(target);

        return tempSkill;
    }

    public Figure enemyTarget(ArrayList<Thing> arrObjTarget) {
        int max = arrObjTarget.size();
        Thing selectTarget = arrObjTarget.get((int) (Math.random() * (max - 1)));
        return (Figure) selectTarget;
    }

    public void heroMenu() {
        int arrSize = lifeHero.size();
        int i = 0;

        while (i < arrSize) {
            System.out.println("\n======================================");
            System.out.println("               Your Hero");
            System.out.println("======================================");
            printHeroes();
            System.out.print("\nYour choice : ");

            try {
                String userChoice = sc.next();

                // ambil digit pertama, asumsinya jumlah hero di dalam array tidak lebih dari 9
                int hero = Integer.parseInt(userChoice.substring(0, 1));
                // ambil digit kedua, asumsinya jumlah skill di dalam array tidak lebih dari 9
                int skill = Integer.parseInt(userChoice.substring(1, 2));

                Hero tempHero = (Hero) lifeHero.get((hero - 1));

                if (!tempHero.isAction()) {
                    ArrayList<Thing> tempArrSkill = lifeHero.get((hero - 1)).getOneClass("Skill");
                    Skill tempSkill = (Skill) tempArrSkill.get(skill - 1);

                    // pilih target skill, kemudian tambahkan ke arrSkill
                    addSkill(heroSelectTarget(tempSkill));

                    // ubah status isAction milik tempHero
                    tempHero.setAction(true);

                    // jumlah hero yang sudah menggunakan skill bertambah
                    i++;
                } else {
                    System.out.println("\n" + "*".repeat(60));
                    System.out.println("Select another hero!");
                    System.out.println(tempHero.getName() + " has already been take an action!");
                    System.out.println("*".repeat(60));
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) { // jika input != string sepanjang 2 char
                System.out.println("\nYour input doesn't valid!");
                // bersihkan scanner dengan cara membuat objek baru;
                sc = new Scanner(System.in);
            }
        }
    }

    public void printHeroes() {
        int heroIndex = 1;

        for (Thing objHero : lifeHero) {
            System.out.println("\n" + "=".repeat(30));
            System.out.print(objHero.getName());
            if (((Hero) objHero).isAction()) {
                System.out.println(" -> has taken an action");
            } else {
                System.out.println();
            }
            System.out.println("=".repeat(30));
            ((Figure) objHero).describeInBattle();

            // cetak skill-skill milik objHero
            ((Hero) objHero).printSkills(heroIndex);
            heroIndex++;
        }
    }

    public Skill heroSelectTarget(Skill tempSkill) {
        Figure target;

        // tampilkan pilihan target untuk hero yang sedang melakukan aksi
        try {
            if (tempSkill.isTargetFoe()) {
                target = heroTarget(lifeEnemy);
            } else {
                target = heroTarget(lifeHero);
            }

            // set target untuk skill
            tempSkill.setTempTarget(target);
        } catch (IndexOutOfBoundsException | InputMismatchException e) { // jika input di luar cakupan array
            System.out.println("\nYour input doesn't valid!");
            heroSelectTarget(tempSkill);
        }

        return tempSkill;
    }

    public Figure heroTarget(ArrayList<Thing> arrObjTarget) {
        int i = 1;
        int selectTarget;

        System.out.println("\n" + "=".repeat(20));
        System.out.println("       Target");
        System.out.println("=".repeat(20));

        for (Thing objTarget : arrObjTarget) {
            System.out.print("\n[" + i + "] ");
            ((Figure) objTarget).describeInBattle();
            i++;
        }

        System.out.print("\nChoose your target: ");
        selectTarget = sc.nextInt();

        return (Figure) arrObjTarget.get(selectTarget - 1);
    }

    public void calculateBattle() {
        /*
            Alur method calculateBattle
            1. Sorting arrSkill secara ascending berdasarkan atribut timestamp
            2. Ambil data dari arrSkill
            3. Kerjakan method execute() setiap objSkill
            4. Kosongkan arrSkill
            5. Reset action hero dan enemy
         */

        // sort ascending berdasarkan timestamp
        readySkill.sort(Comparator.comparing(Skill::getTimestamp));
        int i = 1;

        for (Skill objSkill : readySkill) {
            if (objSkill.getOwner().getHealth() > 0) {
                objSkill.execute(); // lakukan kalkulasi damage dan repair

                // tampilkan urutan eksekusi
                System.out.println("\nExecuted Skill " + i);
                System.out.println("Skill : " + objSkill.getName());
                System.out.println("Owner : " + objSkill.getOwner().getName());
                System.out.println("Target : " + objSkill.getTempTarget().getName());
                System.out.println("Timestamp : " + objSkill.getTimestamp());
                System.out.println("Damage : " + objSkill.getDamage());
                System.out.println("Heal : " + objSkill.getHeal());
                System.out.println("Defense : " + objSkill.getDefense());

                checkBattleCondition(); // periksa dulu apakah hero dan enemy masih hidup

                // karena hero atau enemy yang mati sudah ditambahkan ke deadHero atau deadEnemy,
                // hapus hero atau enemy dari lifeHero atau lifeEnemy
                removeDeadFigure(objSkill.getTempTarget());

                i++;
            }
        }

        readySkill.clear(); // kosongkan arrSkill untuk turn selanjutnya

        // reset isAction
        resetAction(lifeHero);
        resetAction(lifeEnemy);
    }

    public void checkBattleCondition() {
        int heroDied = checkAlive(lifeHero, deadHero);
        int enemyDied = checkAlive(lifeEnemy, deadEnemy);

        if (heroDied == lifeHero.size()) {
            System.out.println("\nYou are lost!\n");
            isBattleStopped = true;
        }

        if (enemyDied == lifeEnemy.size()) {
            System.out.println("\nYou are win!\n");

            /*
                Alur method ketika win:
                1. Tambah exp hero --> kalau cukup untuk naik level --> upgrade status hero
                2. Tambah gold inventory
                3. Pindahkan item dari Enemy ke Inventory
                4. Hapus Enemy dari Room
            */

            addExpAndGold();
            moveItems();

            isBattleStopped = true;
        }
    }

    public int checkAlive(ArrayList<Thing> aliveFigure, ArrayList<Thing> deadFigure) {
        int died = 0;

        for (Thing objFigure : aliveFigure) {
            if (((Figure) objFigure).getHealth() <= 0) {
                died++;

                // tambahkan hero atau enemy yang mati ke dalam arrayList deadFigure
                // belum dipindahkan karena masih digunakan oleh skill sebagai target
                deadFigure.add(objFigure);
            }
        }

        return died;
    }

    public void addExpAndGold() {
        int totalExp = 0;
        int totalGold = 0;
        for (Thing objEnemy : deadEnemy) {
            totalExp += ((Enemy) objEnemy).getExp();
            totalGold += ((Enemy) objEnemy).getGold();
        }

        // tambahkan totalExp ke setiap hero yang berada di active party
        for (Hero objHero : GameInfo.activeParty) {
            objHero.increaseExp(totalExp);
            objHero.upgradeStats(); // naik level dan upgrade stats --> jika exp cukup untuk naik level
        }

        // tambahkan totalGold ke userInventory
        GameInfo.userInventory.addGold(totalGold);

        System.out.println("You're get " + totalGold + " gold");
        System.out.println("All your active heroes get " + totalExp + " exp");
    }

    public void moveItems() {
        for (Thing objEnemy : deadEnemy) { // untuk setiap enemy
            for (Thing objItem : objEnemy.getArrThing()) { // untuk setiap item milik enemy
                if (!objItem.getTrueClassName().equals("Skill")) { // jika bukan class Skill
                    GameInfo.userInventory.addThing(objItem); // tambahkan item ke dalam userInventory
                }
            }
            GameInfo.activeRoom.removeThing(objEnemy); // hapus enemy dari activeRoom
        }
    }

    public void removeDeadFigure(Figure tempTarget) {
        if (tempTarget.getHealth() <= 0) {
            if (lifeHero.contains(tempTarget)) {
                // barulah hapus hero yang mati dari lifeHero
                lifeHero.remove(tempTarget);
            } else {
                // barulah hapus enemy yang mati dari lifeEnemy
                lifeEnemy.remove(tempTarget);
            }
        }
    }

    public void resetAction(ArrayList<Thing> arrFigure) {
        for (Thing objFigure : arrFigure) {
            ((Figure) objFigure).setAction(false);
        }
    }

    public void startBattle() {
        int turn = 1;

        lifeHero.addAll(GameInfo.activeParty);
        lifeEnemy.addAll(GameInfo.activeRoom.getOneClass("Enemy"));

        while (!isBattleStopped) {
            /*
                Alur Enemy
                1. Ambil objek enemy dari Array
                2. Pilih skill enemy
                3.
                Set setiap enemy di dalam array dengan looping agar menggunakan skill
            */
            enemyMenu();

            /*
                Alur User
                1. Pilih hero
                2. Pilih skill hero -> skill hero memiliki atribut "Value",
                   untuk menampung data damage, heal, maupun shield.
                3. Pilih salah satu enemy atau hero sebagai target skill
                4. Looping sampai semua hero memilih skill
            */
            heroMenu();

            calculateBattle();
            turn = turn + 1;
        }
    }
}
