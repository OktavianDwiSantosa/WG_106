package main3;

import required.Equipment;
import required.Thing;

public class Weapon extends Equipment {
    private String weaponType;

    public Weapon(String vName, String vDescription, Thing vOwner, int vPrice, String vRarity,
                  double vStrengthMultiplier, double vHealthMultiplier, double vShieldMultiplier) {
        super(vName, vDescription, vOwner, vPrice, vRarity, vStrengthMultiplier, vHealthMultiplier, vShieldMultiplier);
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("Weapon Type : ");
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String vWeaponType) {
        weaponType = vWeaponType;
    }
}
