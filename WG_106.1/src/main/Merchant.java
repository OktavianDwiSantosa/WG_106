package main;

import required.Thing;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class Merchant extends Thing {
    private static Merchant theMerchant;

    private Merchant() {
        super("Stranger Merchant", "He was just a strange stranger merchant");
    }

    // Singleton
    public static Merchant getMerchant() {
        if (theMerchant == null) {
            theMerchant = new Merchant();
        }
        return theMerchant;
    }

    private ArrayList<Thing> buyGUI() {
        ArrayList<Thing> tempItem = null;
        Scanner sc = new Scanner(System.in);

        System.out.println("\n" + "=".repeat(30));
        System.out.println("           Buy Item");
        System.out.println("=".repeat(30) + "\n");
        System.out.println("[1] Armor");
        System.out.println("[2] Material");
        System.out.println("[3] MedKit");
        System.out.println("[4] Weapon");
        System.out.println("[0] Cancel");
        System.out.println("\n" + "=".repeat(30));
        System.out.print("Select item to buy : ");

        try {
            int selectItem = sc.nextInt();
            switch (selectItem) {
                case 0 -> System.out.println("You cancelling to buy an item");
                case 1 -> tempItem = getOneClass("Armor");
                case 2 -> tempItem = getOneClass("Material");
                case 3 -> tempItem = getOneClass("MedKit");
                case 4 -> tempItem = getOneClass("Weapon");
                default -> {
                    System.out.println("\nYour choice doesn't available!\n");
                    buyGUI();
                }
            }
        } catch (InputMismatchException e) { // jika input bukan integer
            System.out.println("Your input doesn't valid!");
            buyGUI();
        }

        return tempItem;
    }

    public void buyMenu() {
        ArrayList<Thing> tempItem = buyGUI();

        if (tempItem != null) {
            Scanner sc = new Scanner(System.in);

            System.out.println("\n" + "=".repeat(30));
            System.out.println("           Buy Item");
            System.out.println("=".repeat(30) + "\n");
            for (Thing objItem : tempItem) {
                objItem.describe();
            }
            System.out.println("\n" + "=".repeat(30));
            System.out.print("Select item to buy : ");
        }
    }

    public void sellMenu() {

    }

    public void craftMenu() {

    }

    public void upgradeMenu() {

    }

}
