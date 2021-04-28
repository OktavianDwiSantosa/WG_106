package main3;

import required.Thing;

public class Box extends Thing {
    private int gold;
    private boolean isOpened;

    public Box(String vName, String vDescription, int vGold) {
        super(vName, vDescription);
        this.setGold(vGold);
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("Gold : " + getGold());
        showAllClass();
    }

    public void opened() {
        GameInfo.userInventory.addGold(gold);
        GameInfo.userInventory.getArrThing().addAll(this.getArrThing()); // tambah semua Item ke dalam Inventory

        System.out.printf("\nYou have been opened the %s!\n", this.getName());
        describe();

        isOpened = true;
        this.getArrThing().clear(); // hapus semua Item dari Box
    }

    // Getter & Setter
    public int getGold() {
        return gold;
    }

    public void setGold(int vGold) {
        gold = vGold;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean vOpened) {
        isOpened = vOpened;
    }
}
