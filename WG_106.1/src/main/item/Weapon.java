package main.item;

import main.Hero;
import required.Equipment;
import required.Multiplier;
import required.Thing;
import required.enums.RarityType;
import required.enums.WeaponType;

import java.util.ArrayList;
import java.util.HashMap;

public class Weapon extends Equipment {
    private final WeaponType weaponType;

    // Constructor
    public Weapon(String vName, String vDescription, Thing vOwner,
                  int vPrice, RarityType vRarity, int vLevel,
                  ArrayList<Multiplier> vMultipliers,
                  HashMap<String, Integer> vRequiredMaterials,
                  WeaponType vWeaponType) {
        super(vName, vDescription, vOwner, vPrice, vRarity, vLevel, vMultipliers, vRequiredMaterials);
        weaponType = vWeaponType;
    }

    // Method
    @Override
    public void describe() {
        super.describe();
        System.out.println("WeaponType : " + weaponType);
    }

    @Override
    public void equipped(Hero vNewOwner) {
        if (vNewOwner.getWeaponType() == weaponType)
            super.equipped(vNewOwner);
    }

}
