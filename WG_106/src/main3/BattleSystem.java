package main3;

import required.Figure;
import required.Thing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BattleSystem {
    private Scanner sc = new Scanner(System.in);
    private boolean isBattleStopped = false;
    private int turn;

    // untuk menyimpan data arraylist hero & enemy yang diambil dari gameEngine
    private ArrayList<Thing> arrHero = new ArrayList<>();
    private ArrayList<Thing> arrEnemy = new ArrayList<>();

    // karena ini hanya dipakai internal untuk BattleSystem, maka gunakan ArrayList<Skill>
    private ArrayList<Skill> arrSkill = new ArrayList<>();

    // Getter & Setter
    public int getTurn() { return turn; }

    public void setTurn(int vTurn) { turn = vTurn; }

    public ArrayList<Thing> getArrHero() { return arrHero; }

    public void setArrHero(ArrayList<Thing> vArrHero) { arrHero = vArrHero; }

    public ArrayList<Thing> getArrEnemy() { return arrEnemy; }

    public void setArrEnemy(ArrayList<Thing> vArrEnemy) { arrEnemy = vArrEnemy; }

    public ArrayList<Skill> getArrSkill() { return arrSkill; }

    public void setArrSkill(ArrayList<Skill> vArrSkill) { arrSkill = vArrSkill; }

    // Methods
    public void addHero(Figure vObjHero) { arrHero.add(vObjHero); }

    public void addEnemy(Figure vObjEnemy) { arrEnemy.add(vObjEnemy); }

    public void addSkill(Skill tempSkill) { arrSkill.add(tempSkill); }

    public void printHeroes() {
        int heroIndex = 1;

        for (Thing objHero : arrHero) {
            // harusnya periksa dulu apakah Hero masih hidup

            System.out.println("\n" + "=".repeat(30));
            System.out.print(objHero.getName());

            // lakukan down casting dari class Thing ke class Hero
            // down cast tidak berarti membuat objek baru, tapi hanya mengubah jenis kelas
            if (((Hero) objHero).isAction()) {
                System.out.println(" -> has taken an action");
            } else {
                System.out.println();
            }

            System.out.println("=".repeat(30));

            // cetak skill-skill milik objCharacter
            // objHero.printSkill(i);
            ((Hero) objHero).printSkill(heroIndex);
            heroIndex++;
        }
    }

    public Figure target(ArrayList<Thing> arrObjTarget) {
        int i = 1;
        int selectTarget;

        System.out.println("\n" + "=".repeat(20));
        System.out.println("       Target");
        System.out.println("=".repeat(20) + "\n");

        for (Thing objTarget : arrObjTarget) {
            System.out.print("\n[" + i + "] ");
            objTarget.describe();
            i++;
        }

        System.out.print("\nChoose your target: ");
        selectTarget = sc.nextInt();

        // di down cast
        return (Figure) arrObjTarget.get(selectTarget - 1);
    }

    public Figure enemyTarget(ArrayList<Thing> arrObjTarget) {
        int max = arrObjTarget.size();
        Thing selectTarget = arrObjTarget.get((int) (Math.random() * (max - 1)));

        // di down cast
        return (Figure) selectTarget;
    }

    public Skill selectTarget(Skill tempSkill) {
        Figure target;
        // tampilkan pilihan target untuk hero yang sedang melakukan aksi
        try {
            if (tempSkill.isTargetFoe()) {
                target = target(arrEnemy);
            } else {
                target = target(arrHero);
            }

            // set target untuk skill
            tempSkill.setTempTarget(target);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nYour input doesn't valid!");
            selectTarget(tempSkill);
        }

        return tempSkill;
    }

    private Skill enemySelectTarget(Skill tempSkill) {
        Figure target;
        // tampilkan pilihan target untuk hero yang sedang melakukan aksi
        if (tempSkill.isTargetFoe()) {
            target = enemyTarget(arrHero);
        } else {
            target = enemyTarget(arrEnemy);
        }
        // set target untuk skill
        tempSkill.setTempTarget(target);

        return tempSkill;
    }

    public void heroMenu() {
        int arrSize = arrHero.size();
        int i = 0;

        while (i < arrSize) {
            System.out.println("\n======================================");
            System.out.println("               Your Hero");
            System.out.println("======================================");
            printHeroes();
            System.out.print("\nYour choice : ");

            try {
                String userChoice = sc.next();

                // ambil digit pertama, asumsinya jumlah hero di dalam array tidak lebih dari 10
                int hero = Integer.parseInt(userChoice.substring(0, 1));
                // ambil digit kedua, asumsinya jumlah skill di dalam array tidak lebih dari 10
                int skill = Integer.parseInt(userChoice.substring(1, 2));

                Hero tempHero = (Hero) arrHero.get((hero - 1));

                if (!tempHero.isAction()) {
                    ArrayList<Thing> tempArrSkill = arrHero.get((hero - 1)).getOneClass("Skill");
                    Skill tempSkill = (Skill) tempArrSkill.get(skill - 1);

                    // pilih target skill, kemudian tambahkan ke arrSkill
                    addSkill(selectTarget(tempSkill));

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
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\nYour input doesn't valid!");
                // bersihkan scanner dengan cara membuat objek baru;
                sc = new Scanner(System.in);
            }
        }
    }

    public void enemyMenu() {
        int arrSize = arrEnemy.size();
        int i = 0;

        while (i < arrSize) {
            for (Thing objEnemy : arrEnemy) {
                ArrayList<Thing> tempArrSkill = objEnemy.getOneClass("Skill");

                // set nilai max berdasarkan banyaknya skill milik objEnemy
                int max = tempArrSkill.size();

                // lakukan math random dalam range (1 - max) untuk memilih skill enemy
                Skill tempSkill = (Skill) tempArrSkill.get((int) (Math.random() * (max - 1)));

                // pilih target skill, kemudian tambahkan ke arrSkill
                addSkill(enemySelectTarget(tempSkill));

                // jumlah enemy yang sudah menggunakan skill bertambah
                i++;
            }
        }
    }

    public void calculateBattle() {
        /*
            Alur method calculateBattle
            1. Sorting arrSkill secara ascending berdasarkan atribut timestamp
            2. Ambil data dari arrSkill
            3. Kerjakan method execute() setiap objSkill
         */

        // sort ascending berdasarkan timestamp
        arrSkill.sort(Comparator.comparing(Skill::getTimestamp));
        int i = 1;

        System.out.println("Ready Skill : ");
        for (Skill objSkill : arrSkill) {
            objSkill.execute(); // lakukan kalkulasi damage dan repair
            checkBattleCondition(); // periksa dulu apakah hero dan enemy masih hidup

            // tampilkan urutan eksekusi
            System.out.println("Skill " + i);
            System.out.println("Target : " + objSkill.getTempTarget().getName());
            System.out.println("Timestamp : " + objSkill.getTimestamp());
            i++;
        }
    }

    public int checkAlive(ArrayList<Thing> arrThing) {
        int died = 0;

        for (Thing objThing : arrThing) {
            if (((Figure) objThing).getHealth() <= 0) {
                died++;
            }
        }

        return died;
    }

    public void checkBattleCondition() {
        int heroDied = checkAlive(arrHero);
        int enemyDied = checkAlive(arrEnemy);

        if (heroDied == arrHero.size()) {
            System.out.println("You are lost!");
            isBattleStopped = true;
        }

        if (enemyDied == arrEnemy.size()) {
            System.out.println("You are win!");

            /*
                Alur method ketika win:
                1. Pindahkan item dari Enemy ke Inventory
                2. Tambah exp hero --> kalau cukup untuk naik level --> upgrade status hero
                3. Hapus Enemy dari Room
            */

            isBattleStopped = true;
        }
    }

    public void startBattle() {
        turn = 1;

        while (!isBattleStopped) {
            // Alur Enemy
            // Set setiap enemy di dalam array dengan looping agar menggunakan skill
            enemyMenu();

            /*
                Alur User
                1. Pilih hero
                2. Pilih aksi hero -> aksi hero memiliki atribut "Value",
                   untuk menampung data damage, heal, maupun shield.
                3. Pilih salah satu enemy atau hero sebagai target skill
                4. Looping sampai semua hero memilih skill
            */
            heroMenu();

            calculateBattle();
            turn++;
        }
    }
}
