package main.item;

import main.Hero;
import required.Consumable;
import required.Item;
import required.delegation.Health;
import required.enums.Operator;
import required.Thing;
import required.enums.RarityType;

public class MedKit extends Item implements Consumable {
    private final Health healthMultiplier;
//    private final double healthMultiplier; // pengali untuk menentukan nilai asli heal()

    public MedKit(String vName, String vDescription, Thing vOwner,
                  int vPrice, RarityType vRarity, Health vHealthMultiplier) {
        super(vName, vDescription, vOwner, vPrice, vRarity);
        healthMultiplier = vHealthMultiplier;
    }

    @Override
    public void describe() {
        super.describe();
        healthMultiplier.printProbability();
    }

    @Override
    public void used(Thing healTarget) {
        if (healTarget.getTrueClassName().equals("Hero")) {
            Hero tempHero = (Hero) healTarget;

            // tambah max health hero
            healthMultiplier.applyEqValue(Operator.ADDITION, tempHero);

            // Java akan otomatis menghapus objek ke dalam garbage collection,
            // ketika kita tidak me-referensi objek ini lagi
            getOwner().removeThing(this);
        }
    }

}
