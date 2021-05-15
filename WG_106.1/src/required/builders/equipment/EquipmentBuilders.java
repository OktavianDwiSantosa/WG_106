package required.builders.equipment;

import main.item.Armor;
import main.Hero;
import main.item.Weapon;
import required.Equipment;
import required.Multiplier;
import required.Thing;
import required.delegation.*;
import required.enums.ArmorType;
import required.enums.RarityType;
import required.enums.WeaponType;

import java.util.ArrayList;
import java.util.HashMap;

/*
    Inner Class : https://www.programmersought.com/article/97114162536/
    Generic Types Documentation : https://docs.oracle.com/javase/tutorial/java/generics/types.html
    Generic Explanation Video : https://www.youtube.com/watch?v=bvWRDAl30Gs
*/

public abstract class EquipmentBuilders {
    /**
     * Static factory methods
     */
    public static ArmorBuilder newArmor() { return new ArmorBuilder(); }

    public static WeaponBuilder newWeapon() { return new WeaponBuilder(); }


    /**
     * Armor SkillBuilder
     */
    public static class ArmorBuilder extends AbstractEquipmentBuilder<ArmorBuilder, Armor> {

        private ArmorType armorType;

        public ArmorBuilder armorType(ArmorType vArmorType) {
            if (vArmorType == null)
                throw new IllegalArgumentException("Armor Type cannot be empty!");

            armorType = vArmorType;
            return getThis();
        }

        @Override
        protected Armor internalBuild() {
            return new Armor(name, description, owner, price,
                    rarity, level, multipliers, requiredMaterials, armorType);
        }

        @Override
        protected ArmorBuilder getThis() {
            return this;
        }
    }

    /**
     * Weapon SkillBuilder
     */
    public static class WeaponBuilder extends AbstractEquipmentBuilder<WeaponBuilder, Weapon> {

        private WeaponType weaponType;

        public WeaponBuilder weaponType(WeaponType vWeaponType) {
            if (vWeaponType == null)
                throw new IllegalArgumentException("Weapon Type cannot be empty!");

            weaponType = vWeaponType;
            return getThis();
        }

        @Override
        protected Weapon internalBuild() {
            return new Weapon(name, description, owner, price,
                    rarity, level, multipliers, requiredMaterials, weaponType);
        }

        @Override
        protected WeaponBuilder getThis() {
            return this;
        }
    }

    abstract static class AbstractEquipmentBuilder<SELF extends EquipmentBuilder<SELF, TTarget>,
            TTarget extends Equipment> implements EquipmentBuilder<SELF, TTarget> {

        protected String name;
        protected String description;
        protected Thing owner;
        protected int price;
        protected RarityType rarity;
        protected int level;
        protected ArrayList<Multiplier> multipliers = new ArrayList<>();
        protected HashMap<String, Integer> requiredMaterials = new HashMap<>();

        @Override
        public SELF name(String vName) {
            if (vName == null || vName.isEmpty())
                throw new IllegalArgumentException("Name cannot be empty!");

            name = vName;
            return self();
        }

        @Override
        public SELF description(String vDescription) {
            if (vDescription == null || vDescription.isEmpty())
                throw new IllegalArgumentException("Description cannot be empty!");

            description = vDescription;
            return self();
        }

        @Override
        public SELF owner(Thing vOwner) {
            if (vOwner == null)
                throw new IllegalArgumentException("Owner cannot be empty!");

            owner = vOwner;
            return self();
        }

        @Override
        public SELF price(int vPrice) {
            if (vPrice < 0)
                throw new IllegalArgumentException("Price cannot be smaller than 0!");

            price = vPrice;
            return self();
        }

        @Override
        public SELF rarity(RarityType vRarity) {
            if (vRarity == null)
                throw new IllegalArgumentException("Rarity cannot be empty!");

            rarity = vRarity;
            return self();
        }

        @Override
        public SELF level(int vLevel) {
            if (vLevel < 1)
                throw new IllegalArgumentException("Level cannot be smaller than 1!");

            level = vLevel;
            return self();
        }

        @Override
        public SELF strengthMultiplier(double vStrengthMultiplier) {
            if (vStrengthMultiplier < 0)
                throw new IllegalArgumentException("Strength Multiplier cannot be smaller than 0!");

            multipliers.add(new Strength(vStrengthMultiplier));
            return self();
        }

        @Override
        public SELF healthMultiplier(double vHealthMultiplier) {
            if (vHealthMultiplier < 0)
                throw new IllegalArgumentException("Health Multiplier cannot be smaller than 0!");

            multipliers.add(new Health(vHealthMultiplier));
            return self();
        }

        @Override
        public SELF shieldMultiplier(double vShieldMultiplier) {
            if (vShieldMultiplier < 0)
                throw new IllegalArgumentException("Shield Multiplier cannot be smaller than 0!");

            multipliers.add(new Shield(vShieldMultiplier));
            return self();
        }

        @Override
        public SELF requiredMaterials(String vMaterialName, int vAmount) {
            if (vMaterialName == null || vMaterialName.isEmpty())
                throw new IllegalArgumentException("Material Name cannot be empty!");
            else if (vAmount < 1)
                throw new IllegalArgumentException("Amount cannot be smaller than 1!");

            requiredMaterials.put(vMaterialName, vAmount);
            return self();
        }

        public TTarget build() { return internalBuild(); }

        protected abstract TTarget internalBuild();

        protected abstract SELF getThis();

        private SELF self() { return getThis(); }
    }

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

        // System.out.println(weapon1.getOwner().getName());
        weapon1.describe();
    }
}
