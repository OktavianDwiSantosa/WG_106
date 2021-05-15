package main;

import main.item.Weapon;
import required.builders.equipment.EquipmentBuilders;
import required.builders.skill.SkillBuilders;
import required.enums.*;
import view.WhiteGateGUI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

// class GameEngine tidak dibuat final karena nanti bisa loadGame
public class GameEngine implements java.io.Serializable {
    private final BattleSystem battleSystem = BattleSystem.getBattleSystem();

    public GameEngine() {
        /*
            https://refactoring.guru/design-patterns/memento
            https://refactoring.guru/design-patterns/prototype
            https://refactoring.guru/design-patterns/observer
            https://wiki.sei.cmu.edu/confluence/pages/viewpage.action?pageId=88487725
        */

        // ini anggap belum bisa read & write file --> start new game
        // rencananya kalau sudah bisa read & write file
        // --> buat constructor baru
        // --> data diambil dari file

        GameInfo.userInventory = Inventory.getInventory();
        GameInfo.theMerchant = Merchant.getMerchant();
        Chapter initChapter = new Chapter("The Broken World", 1);
        Room room1 = new Room("Basement", "The basement of destroyed building", 1);
        initChapter.addThing(room1);
        GameInfo.activeRoom = (Room) initChapter.getOneClass("Room").get(0);
        GameInfo.knownChapters = new ArrayList<>();
        GameInfo.knownHeroes = new ArrayList<>();
        GameInfo.activeParty = new ArrayList<>();
        GameInfo.knownEnemies = new ArrayList<>();
    }

    public void loopGame() {
        while (!GameInfo.isGameOver) {
            WhiteGateGUI.setObjGameEngine(this);
            WhiteGateGUI.adventureMenu();
        }
    }

    public void startNewGame() {
        System.out.println("Starting a new game!");
        loopGame();
    }

    public void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream("WG.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            System.out.println("Game Saved\n");
        } catch (Exception e) {
            System.out.println("ERROR! Can't save the data!");
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
    }

    public void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("WG.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ois.readObject();
            ois.close();
            System.out.println("Game Loaded\n");
            loopGame();
        } catch (Exception e) {
            System.out.println("ERROR! Can't load the data!");
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
    }


    // Getter
    public BattleSystem getBattleSystem() { return battleSystem; }

    public static void main(String[] args) {
        WhiteGateGUI.startMenu(new GameEngine());
        /*System.out.println("Size array userInventory : " + GameInfo.userInventory.arrSize());
        Hero hero99 = new Hero("Elon Musk", "", 1, 0,
                200, 100, 50, WeaponType.SWORD, ArmorType.HEAVY);

        hero99.describe();*/

        /*ArrayList<Multiplier> objMultipliers = new ArrayList<>();
        Strength strength1 = new Strength(0.5);
        Shield shield1 = new Shield(0.2);
        objMultipliers.add(strength1);
        objMultipliers.add(shield1);*/

        /*Weapon weapon1 = new Weapon("Sword", "Old Sword",
                hero99, 250, RarityType.RARE, 1,
                objMultipliers, null, WeaponType.SWORD);*/

        /*Weapon sword1 = EquipmentBuilders.newWeapon()
                .name("Long Sword")
                .description("A solid steel sword")
                .owner(hero99)
                .price(500)
                .rarity(RarityType.RARE)
                .level(1)
                .strengthMultiplier(0.5)
                .shieldMultiplier(0.2)
                .weaponType(WeaponType.SWORD)
                .build();
        GameInfo.userInventory.addThing(sword1);

        System.out.println("\nApakah di inventory ada sword1 ? " + GameInfo.userInventory.searchThing(sword1));
        System.out.println("\nKelas Asal dari Thing : " +
                GameInfo.userInventory.getThing(0).getClass().getSimpleName());*/

        // exit(0);

        /*GameInfo.activeParty.add(hero99);

        ArrayList<Thing> arrHero = new ArrayList<>(GameInfo.activeParty);
        ArrayList<Thing> deadHero = new ArrayList<>();

        hero99.describe(); // obj reference
        arrHero.get(0).describe(); // setelah ditambahkan ke arrHero
        GameInfo.activeParty.get(0).describe(); // setelah ditambahkan ke activeHero

        ((Hero) arrHero.get(0)).setHealth(0);

        deadHero.add(arrHero.get(0));
        arrHero.remove(deadHero.get(0));
        deadHero.get(0).describe();
        GameInfo.activeParty.get(0).describe();*/

        Hero hero1 = new Hero("Elon", "A Dedicated Engineer", 1,
                0, 50, 12, 10,
                WeaponType.SWORD, ArmorType.HEAVY);

        Skill skill1 = new SkillBuilders()
                .name("Elon Skill A")
                .description("-")
                .owner(hero1)
                .unlockedLevel(1)
                .skillType(SkillType.LIGHT)
                .targetFoe(true)
                .timestamp(15)
                .strengthMultiplier(0.25)
                .build();

        Skill skill2 = new SkillBuilders()
                .name("Elon Skill B")
                .description("-")
                .owner(hero1)
                .unlockedLevel(1)
                .skillType(SkillType.NORMAL)
                .targetFoe(true)
                .timestamp(30)
                .strengthMultiplier(0.5)
                .build();

        Skill skill3 = new SkillBuilders()
                .name("Elon Skill C")
                .description("-")
                .owner(hero1)
                .unlockedLevel(1)
                .skillType(SkillType.HEAVY)
                .targetFoe(true)
                .timestamp(45)
                .strengthMultiplier(0.75)
                .build();

        hero1.addThing(skill1);
        hero1.addThing(skill2);
        hero1.addThing(skill3);

        Hero hero2 = new Hero("Tony", "Tech Pro", 1,
                0, 40, 15, 10,
                WeaponType.CYBERWARE, ArmorType.ROBE);

        Skill skill4 = new SkillBuilders()
                .name("Tony Skill A")
                .description("-")
                .owner(hero2)
                .unlockedLevel(1)
                .skillType(SkillType.LIGHT)
                .targetFoe(true)
                .timestamp(15)
                .strengthMultiplier(0.25)
                .build();

        Skill skill5 = new SkillBuilders()
                .name("Tony Skill B")
                .description("-")
                .owner(hero2)
                .unlockedLevel(1)
                .skillType(SkillType.NORMAL)
                .targetFoe(true)
                .timestamp(30)
                .strengthMultiplier(0.5)
                .build();

        Skill skill6 = new SkillBuilders()
                .name("Tony Skill C")
                .description("-")
                .owner(hero2)
                .unlockedLevel(1)
                .skillType(SkillType.HEAVY)
                .targetFoe(true)
                .timestamp(45)
                .strengthMultiplier(0.75)
                .build();

        hero2.addThing(skill4);
        hero2.addThing(skill5);
        hero2.addThing(skill6);

        Enemy enemy1 = new Enemy("Robot A", "", 1,
                10, 50, 10, 15, 6);

        Skill skill7 = new SkillBuilders()
                .name("Enemy1 Skill A")
                .description("-")
                .owner(enemy1)
                .unlockedLevel(1)
                .skillType(SkillType.LIGHT)
                .targetFoe(true)
                .timestamp(15)
                .strengthMultiplier(0.25)
                .build();

        Skill skill8 = new SkillBuilders()
                .name("Enemy1 Skill B")
                .description("-")
                .owner(enemy1)
                .unlockedLevel(1)
                .skillType(SkillType.NORMAL)
                .targetFoe(true)
                .timestamp(30)
                .strengthMultiplier(0.5)
                .build();

        Skill skill9 = new SkillBuilders()
                .name("Enemy1 Skill C")
                .description("-")
                .owner(enemy1)
                .unlockedLevel(1)
                .skillType(SkillType.HEAVY)
                .targetFoe(true)
                .timestamp(45)
                .strengthMultiplier(0.75)
                .build();

        enemy1.addThing(skill7);
        enemy1.addThing(skill8);
        enemy1.addThing(skill9);

        Enemy enemy2 = new Enemy("Robot B", "", 2,
                12, 40, 12, 10, 10);

        Skill skill10 = new SkillBuilders()
                .name("Enemy2 Skill A")
                .description("-")
                .owner(enemy2)
                .unlockedLevel(1)
                .skillType(SkillType.LIGHT)
                .targetFoe(true)
                .timestamp(15)
                .strengthMultiplier(0.25)
                .build();

        Skill skill11 = new SkillBuilders()
                .name("Enemy2 Skill B")
                .description("-")
                .owner(enemy2)
                .unlockedLevel(1)
                .skillType(SkillType.NORMAL)
                .targetFoe(true)
                .timestamp(30)
                .strengthMultiplier(0.5)
                .build();

        Skill skill12 = new SkillBuilders()
                .name("Enemy2 Skill C")
                .description("-")
                .owner(enemy2)
                .unlockedLevel(1)
                .skillType(SkillType.HEAVY)
                .targetFoe(true)
                .timestamp(45)
                .strengthMultiplier(0.75)
                .build();

        enemy2.addThing(skill10);
        enemy2.addThing(skill11);
        enemy2.addThing(skill12);

        GameInfo.activeParty.add(hero1);
        GameInfo.activeParty.add(hero2);
        GameInfo.knownHeroes.addAll(GameInfo.activeParty);

        Box box1 = new Box("Steel Box", "Unlocked box", 10);
        Weapon sword1 = EquipmentBuilders.newWeapon()
                .name("Long Sword")
                .description("A solid steel sword")
                .owner(hero1)
                .price(500)
                .rarity(RarityType.RARE)
                .level(1)
                .strengthMultiplier(0.5)
                .shieldMultiplier(0.2)
                .weaponType(WeaponType.SWORD)
                .build();
        box1.addThing(sword1);

        Room room2 = new Room("Soldier Armory", "This room is full of enemy", 2);
        HashMap<Direction, Room> nextRoom1 = new HashMap<>();
        nextRoom1.put(Direction.D, room2);

        room2.addThing(enemy1);
        room2.addThing(enemy2);
        room2.addThing(box1);

        HashMap<Direction, Room> nextRoom2 = new HashMap<>();
        nextRoom2.put(Direction.A, GameInfo.activeRoom);

        GameInfo.activeRoom.setNextRoom(nextRoom1);
        room2.setNextRoom(nextRoom2);
//        ge.battleSystem.startBattle();
    }
}
