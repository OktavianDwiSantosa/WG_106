package main;

import required.Thing;

public final class Inventory extends Thing {
    private static Inventory userInventory;
    private int gold = 0;

    private Inventory(String vName) { super(vName, ""); }

    // https://refactoring.guru/design-patterns/singleton

    // Singleton
    public static Inventory getInventory() {
        if (userInventory == null) {
            userInventory = new Inventory("User Inventory");
        }
        return userInventory;
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("Your gold : " + gold);
        showAllClass();
    }

    @Override
    public void showOneClass(String vTrueClassType) {
        int i = 1;
        System.out.println("=".repeat(20));
        System.out.println(vTrueClassType + " inventory");
        System.out.println("=".repeat(20));
        super.showOneClass(vTrueClassType);
        System.out.println("\n" + "=".repeat(20));
    }

    public void addGold(int vGold) {
        gold += vGold;
    }

    public void reduceGold(int vPrice) { gold -= vPrice; }

    // Getter
    public int getGold() {
        return gold;
    }
}
