package required;

import main.GameInfo;
import required.enums.RarityType;

public abstract class Item extends Thing {
    private Thing owner; // pemilik Item
    private final int price; // harga Item
    private final RarityType rarity; // rarity -> Common, Rare, Epic, Legendary

    // Constructor
    public Item(String vName, String vDescription, Thing vOwner,
                int vPrice, RarityType vRarity) {
        super(vName, vDescription);
        owner = vOwner;
        price = vPrice;
        rarity = vRarity;
    }

    @Override
    public void describe() {
        super.describe();
        if (owner.getTrueClassName().equals("Hero")) {
            System.out.println("Owner : " + owner.getName());
        }
        System.out.println("Price  : " + price);
        System.out.println("Rarity : " + rarity);
    }

    public void switchOwner(Thing newOwner) {
        owner = newOwner;
    }

    public void moveItemTo(Thing newOwner) {
        newOwner.addThing(this);
        owner.removeThing(this);
        switchOwner(newOwner);
    }

    public void sold() { // menjual barang kepada Merchant
        GameInfo.userInventory.addGold((int) (price * 0.66)); // gold Inventory ditambah
        moveItemTo(GameInfo.theMerchant);
    }

    public void purchased() { // membeli Item dari Merchant
        if (GameInfo.userInventory.getGold() >= price) {
            GameInfo.userInventory.reduceGold(price); // gold Inventory dikurangi
            moveItemTo(GameInfo.userInventory);
        }
    }

    // Getter & Setter
    public Thing getOwner() { return owner; }

    public int getPrice() { return price; }

}
