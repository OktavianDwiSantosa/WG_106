package required;

import main3.GameInfo;
import main3.Merchant;

public abstract class Item extends Thing {
    private Thing owner; // pemilik Item
    private int price; // harga Item
    private String rarity; // rarity -> Common, Rare, Epic, Legendary
    private int level; // level -> 1 - 50

    // Constructor
    public Item(String vName, String vDescription, Thing vOwner, int vPrice, String vRarity) {
        super(vName, vDescription);
        owner = vOwner;
        price = vPrice;
        rarity = vRarity;
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("Price  : " + price);
        System.out.println("Rarity : " + rarity);
        System.out.println("Level  : " + level);
    }

    public Thing switchOwner(Thing oldOwner, Thing newOwner) {
        setOwner(newOwner);
        return oldOwner;
    }

    public void sold(Merchant objMerchant) { // menjual barang kepada Merchant
        if (switchOwner(owner, objMerchant) == GameInfo.userInventory) { // pemilik Item berubah menjadi objMerchant
            GameInfo.userInventory.setGold(GameInfo.userInventory.getGold() + (int) (price * 0.6)); // gold Inventory ditambah
            objMerchant.addThing(this); // tambah Item ke dalam Merchant
            GameInfo.userInventory.removeThing(this); // hapus Item dari Inventory
        }
    }

    public void purchased() { // membeli Item dari Merchant
        if (GameInfo.userInventory.getGold() > price) {
            GameInfo.userInventory.setGold(GameInfo.userInventory.getGold() - price); // gold Inventory dikurangi
            Thing tempMerchant = switchOwner(owner, GameInfo.userInventory); // pemilik Item berubah menjadi userInventory
            GameInfo.userInventory.addThing(this); // tambah Item ke dalam Inventory
            tempMerchant.removeThing(this); // hapus Item dari Merchant
        }
    }

    // Getter & Setter
    public Thing getOwner() {
        return owner;
    }

    public void setOwner(Thing vOwner) {
        owner = vOwner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int vPrice) {
        price = vPrice;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String vRarity) {
        rarity = vRarity;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int vLevel) {
        level = vLevel;
    }
}
