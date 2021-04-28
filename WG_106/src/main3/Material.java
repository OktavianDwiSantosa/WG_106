package main3;

import required.Consumable;
import required.Thing;

public class Material extends Consumable {
    public Material(String vName, String vDescription, Thing vOwner, int vPrice, String vRarity) {
        super(vName, vDescription, vOwner, vPrice, vRarity);
    }

    @Override
    public void describe() {
        super.describe();
    }

    @Override
    public void used(Thing newOwner) {
        setOwner(newOwner);
    }
}
