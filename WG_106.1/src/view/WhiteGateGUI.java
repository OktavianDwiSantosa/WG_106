package view;

import main.*;
import required.Item;
import required.Thing;
import required.enums.Direction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class WhiteGateGUI {
    /*
        https://refactoring.guru/design-patterns/decorator
        https://refactoring.guru/design-patterns/decorator/java/example
        https://refactoring.guru/duplicate-observed-data
    */

    private static GameEngine objGameEngine;

    public static void startMenu(GameEngine ge) {
        Scanner sc = new Scanner(System.in);
        int actionChoice = -1;
        objGameEngine = ge;

        while (actionChoice != 0) {
            System.out.println("\n======================================");
            System.out.println("         Welcome to White Gate");
            System.out.println("======================================");
            System.out.println("[1] Start a new game");
            System.out.println("[2] Load a save game");
            System.out.println("[0] Exit");
            System.out.println("======================================");
            System.out.print("Choose your hero action : ");

            try {
                actionChoice = sc.nextInt();

                switch (actionChoice) {
                    case 0 -> exit(0);
                    case 1 -> objGameEngine.startNewGame();
                    case 2 -> objGameEngine.loadGame();
                    default -> System.out.println("\nYour choice doesn't available!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nYour input doesn't valid!");
            }
        }
    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        int actionChoice = -1;
        /*
            Alur Main Menu
            1. User pilih menu
            2. Pilihan Menu
                [1] Inventory
                [2] Equipment
                [3] Status
                [4] Active Party
                [5] Enemies Record
                [6] Chapter Selection
                [7] Continue
                [0] Exit Game
        */
        while (actionChoice != 0) {
            System.out.println("\n======================================");
            System.out.println("               Main Menu");
            System.out.println("======================================");
            System.out.println("[1] Inventory");
            System.out.println("[2] Equipment");
            System.out.println("[3] Hero Status");
            System.out.println("[4] Active Party");
            System.out.println("[5] Enemies Record");
            System.out.println("[6] Chapter Selection");
            System.out.println("[7] Continue");
            System.out.println("[0] Exit Game");
            System.out.println("======================================");
            System.out.print("Your choice : ");

            try {
                actionChoice = sc.nextInt();

                switch (actionChoice) {
                    case 0 -> {
                        System.out.println("You're exit the game!");
                        exit(0);
                    }
                    case 1 -> GameInfo.userInventory.showAllClass();
                    case 2 -> {
                        ArrayList<Hero> knownHeroes = GameInfo.knownHeroes;
                        for (int i = 0; i < knownHeroes.size(); i++) {
                            Hero hero = knownHeroes.get(i);
                            System.out.println("[" + (i + 1) + "] " + hero.getName());
                        }

                        System.out.print("Select hero : ");
                        int selectHero = sc.nextInt();

                        Hero tempHero = GameInfo.knownHeroes.get(selectHero - 1);

                        for (Thing objItem : GameInfo.userInventory.getArrThing()) {
                            if (((Item) objItem).getOwner() == tempHero) {
                                objItem.describe();
                            }
                        }
                    }
                    case 3 -> GameInfo.knownHeroes.forEach(Hero::describe);
                    case 4 -> {
                        for (int i = 0; i < GameInfo.activeParty.size(); i++) {
                            Hero objHero = GameInfo.activeParty.get(i);
                            System.out.println("[" + (i + 1) + "] " + objHero.getName());
                        }

                        /*
                         * 1. Edit active party?
                         * 2. (Y) --> editMenu();
                         * 2.1 Add Hero --> Max active party == 3 heroes
                         * 2.2 Remove Hero --> Min active party == 1 heroes
                         * 3. (N) --> mainMenu();
                         */

                        System.out.println("\nEdit active party? (Y/N) ");
                        String editChoice = sc.next().toUpperCase();

                        if ("Y".equals(editChoice)) {
                            System.out.println("         Edit Menu");
                            System.out.println("[1] Add Hero to Party");
                            System.out.println("[2] Remove Hero from Party");
                            System.out.print("Choose Menu : ");
                            int editMenu = sc.nextInt();

                            if (editMenu == 1) {
                                for (int i = 0; i < GameInfo.knownHeroes.size(); i++) {
                                    Hero objHero = GameInfo.knownHeroes.get(i);
                                    System.out.println("[ " + i + "] " + objHero.getName());
                                }

                                System.out.print("Select Hero : ");
                                int selectHero = sc.nextInt();

                                // Hero selection logic
                                if (!GameInfo.knownHeroes.get(selectHero - 1).isParty())
                                    GameInfo.knownHeroes.get(selectHero - 1).addToParty();
                                else {
                                    System.out.println("Please select another hero!");
                                    System.out.println(GameInfo.knownHeroes.get(selectHero - 1).getName()
                                            + " has already been in party before!");
                                }
                            } else if (editMenu == 2) {
                                for (int i = 0; i < GameInfo.activeParty.size(); i++) {
                                    Hero objHero = GameInfo.activeParty.get(i);
                                    System.out.println("[ " + i + "] " + objHero.getName());
                                }

                                System.out.print("Select Hero : ");
                                int selectHero = sc.nextInt();

                                // Hero selection logic
                                if (GameInfo.activeParty.get(selectHero - 1).isParty())
                                    GameInfo.activeParty.get(selectHero - 1).removeFromParty();
                            } else {
                                System.out.println("\nChanges has been saved!\n");
                            }
                        } else if ("N".equals(editChoice))
                            System.out.println("\nYou're not editing active party!\n");
                        else {
                            System.out.println("\nYour input doesn't valid!\n");
                        }
                    }
                    case 5 -> GameInfo.knownEnemies.forEach(Enemy::describe);
                    case 6 -> GameInfo.knownChapters.forEach(Chapter::describe);
                    case 7 -> adventureMenu();
                    default -> throw new IllegalStateException("Unexpected value: " + actionChoice);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nYour input doesn't valid!");
            }
        }
    }

    public static void adventureMenu() {
        Scanner sc = new Scanner(System.in);
        int actionChoice = -1;

        while (actionChoice != 0) {
            System.out.println("\n======================================");
            System.out.print("\nNow you're in ");
            GameInfo.activeRoom.describe();

            System.out.println("\n======================================");
            System.out.println("              Hero Action");
            System.out.println("======================================");
            System.out.println("[1] Attack the enemy");
            System.out.println("[2] Open available box");
            System.out.println("[3] Move to the next room");
            System.out.println("[0] Main menu");
            System.out.println("======================================");
            System.out.print("Choose your hero action : ");


            try {
                actionChoice = sc.nextInt();

                switch (actionChoice) {
                    case 0 -> mainMenu();
                    case 1 -> {
                        ArrayList<Thing> tempEnemy = GameInfo.activeRoom.getOneClass("Enemy");
                        if (tempEnemy.isEmpty()) { // jika tidak ada objEnemy di activeRoom
                            System.out.println("\nThere's no enemy to be attacked in this room");
                            break;
                        }

                        objGameEngine.getBattleSystem().startBattle();
                    }
                    case 2 -> {
                        // tampilan box yang ada di ruangan
                        ArrayList<Thing> tempBox = GameInfo.activeRoom.getOneClass("Box");
                        if (tempBox.isEmpty()) {
                            // jika tidak ada objBox di activeRoom
                            System.out.println("\nThere's no box to be opened in this room");
                            break;
                        }

                        GameInfo.activeRoom.showOneClass("Box");
                        System.out.print("Choose the box number that you want to open : ");

                        try {
                            int boxChoice = sc.nextInt();
                            if (boxChoice < 1 || boxChoice > tempBox.size()) {
                                System.out.println("\nYour choice doesn't available!");
                            } else {
                                ((Box) tempBox.get(boxChoice - 1)).opened();
                            }
                        } catch (InputMismatchException e) { // jika input bukan integer
                            System.out.println("\nYour input doesn't valid!");
                        }
                    }
                    case 3 -> {
                        // pilih arah untuk pergi ke ruangan selanjutnya
                        System.out.print("\nChoose direction (W,A,S,D): ");
                        String roomDirection = sc.next().toUpperCase();
                        try {
                            // method valueOf akan memeriksa apakah terdapat nilai dari String roomDirection
                            Direction direction = Direction.valueOf(roomDirection);
                            Hero.moveToNextRoom(direction);
                            objGameEngine.saveGame();
                        } catch (IllegalArgumentException e) { // jika roomDirection bukan (W,A,S,D)
                            System.out.println("\nYour input doesn't valid!");
                        }
                    }
                    default -> System.out.println("\nYour choice doesn't available!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nYour input doesn't valid!");
            }
        }
    }

    public static void setObjGameEngine(GameEngine vObjGameEngine) { objGameEngine = vObjGameEngine; }
}
