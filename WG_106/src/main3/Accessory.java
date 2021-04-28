package main3;

import required.Equipment;
import required.Thing;

public class Accessory extends Equipment {
    public Accessory(String vName, String vDescription, Thing vOwner, int vPrice, String vRarity, double vStrengthMultiplier, double vHealthMultiplier, double vShieldMultiplier) {
        super(vName, vDescription, vOwner, vPrice, vRarity, vStrengthMultiplier, vHealthMultiplier, vShieldMultiplier);
    }

    @Override
    public void describe() {
        super.describe();
    }
}
