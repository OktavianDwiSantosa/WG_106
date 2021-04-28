package main3;

import required.Thing;

public class Inventory extends Thing {
    private int gold;

    public Inventory(String vName, String vDescription) {
        super(vName, vDescription);
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

    // Getter & Setter
    public int getGold() {
        return gold;
    }

    public void setGold(int vGold) {
        gold = vGold;
    }
}
