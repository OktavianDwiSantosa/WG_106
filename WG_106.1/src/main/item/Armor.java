package main.item;

import main.Hero;
import required.Equipment;
import required.Multiplier;
import required.Thing;
import required.enums.ArmorType;
import required.enums.RarityType;

import java.util.ArrayList;
import java.util.HashMap;

public class Armor extends Equipment {
    private final ArmorType armorType;

    public Armor(String vName, String vDescription, Thing vOwner,
                 int vPrice, RarityType vRarity, int vLevel,
                 ArrayList<Multiplier> vMultipliers,
                 HashMap<String, Integer> vRequiredMaterials,
                 ArmorType vArmorType) {
        super(vName, vDescription, vOwner, vPrice, vRarity, vLevel, vMultipliers, vRequiredMaterials);
        armorType = vArmorType;
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("ArmorType : " + armorType);
    }

    @Override
    public void equipped(Hero vNewOwner) {
        if (vNewOwner.getArmorType() == armorType)
            super.equipped(vNewOwner);
    }

}
