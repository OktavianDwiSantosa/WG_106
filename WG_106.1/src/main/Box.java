package main;

import required.Item;
import required.Thing;

public class Box extends Thing {
    private final int gold;
    private boolean isOpened;

    public Box(String vName, String vDescription, int vGold) {
        super(vName, vDescription);
        gold = vGold;
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("Gold : " + getGold());
        showAllClass();
    }

    public void opened() {
        if (!isOpened) {
            GameInfo.userInventory.addGold(gold);
            System.out.printf("\nYou have been opened the %s!\n", getName());
            describe();

            for (Thing objItem : getArrThing()) {
                ((Item) objItem).moveItemTo(GameInfo.userInventory);
            }

            isOpened = true;
        } else {
            System.out.println("\n " + getName() + " is empty!");
            System.out.println(getName() + " has been opened before!\n");
        }
    }

    // Getter & Setter
    public int getGold() {
        return gold;
    }

}
