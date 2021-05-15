package main.item;

import required.Consumable;
import required.Item;
import required.Thing;
import required.enums.RarityType;

public class Material extends Item implements Consumable {
    public Material(String vName, String vDescription, Thing vOwner, int vPrice, RarityType vRarity) {
        super(vName, vDescription, vOwner, vPrice, vRarity);
    }

    @Override
    public void used(Thing vOwner) {
        // di dalam array equipment akan dihitung jumlah item yang namanya == nama item ini
        // owner awal -> inventory player
        // owner baru --> equipment
        vOwner.removeThing(this);
    }
}
