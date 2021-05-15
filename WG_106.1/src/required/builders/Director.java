package required.builders;


import main.Hero;
import main.item.Weapon;
import required.Thing;
import required.builders.equipment.EquipmentBuilders;
import required.enums.ArmorType;
import required.enums.RarityType;
import required.enums.WeaponType;

public class Director {
    public static void main(String[] args) {
        Hero elon = new Hero("Elon", "", 1, 0,
                80, 40, 30, WeaponType.SWORD, ArmorType.HEAVY);

        Weapon weapon1 = EquipmentBuilders.newWeapon()
                .name("Long Sword")
                .description("Nothing")
                .owner(elon)
                .price(500)
                .rarity(RarityType.EPIC)
                .level(1)
                .strengthMultiplier(0.5)
                .shieldMultiplier(0.2)
                .weaponType(WeaponType.SWORD)
                .build();

        weapon1.describe();
    }

    public void createCommonSword(Thing vOwner) {
        Weapon commonSword = EquipmentBuilders.newWeapon()
                .name("Sword")
                .description("An old rusted sword")
                .owner(vOwner)
                .price(10)
                .rarity(RarityType.COMMON)
                .level(1)
                .strengthMultiplier(0.2)
                .shieldMultiplier(0.025)
                .weaponType(WeaponType.SWORD)
                .build();
    }

}
