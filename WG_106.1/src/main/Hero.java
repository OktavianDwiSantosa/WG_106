package main;

import required.enums.ArmorType;
import required.enums.Direction;
import required.Figure;
import required.enums.WeaponType;

public class Hero extends Figure {
    private boolean isParty; // terdapat di activeParty?
    private final WeaponType weaponType; // weaponType -> Sword, Projectile, Gauntlet, Saber, CyberWare
    private final ArmorType armorType;
    private int expToLevelUp; // exp yang diperlukan untuk naik 1 level

    public Hero(String vName, String vDescription, int vLevel, int vExp,
                int vMaxHealth, int vStrength, int vMaxShield,
                WeaponType vWeaponType, ArmorType vArmorType) {
        super(vName, vDescription, vLevel, vExp, vMaxHealth, vStrength, vMaxShield);
        isParty = false;
        weaponType = vWeaponType;
        armorType = vArmorType;
        expToLevelUp = expFormula();
    }

    @Override
    public void describe() { // describe di luar battle
        /*
         * 1. Nama
         * 2. Deskripsi
         * 3. Level
         * 4. EXP + sisa exp yang diperlukan untuk naik level
         * 5. Max Health + Strength + Max Shield
         * 6. Skill
         */
        super.describe();
    }

    private int expFormula() { // dipakai untuk level up
        return (int) Math.round(0.13 * (getLevel() ^ 3) + 1.3 * (getLevel() ^ 2) + 13 * getLevel());
    }

    public boolean levelUp() {
        if (getExp() > expToLevelUp) {
            setLevel(getLevel() + 1); // level ditambah 1
            setExpToLevelUp(expFormula()); // exp yang diperlukan meningkat
            return true;
        }
        return false;
    }

    public void upgradeStats() {
        if (levelUp()) {
            setMaxHealth((int) (getMaxHealth() * 1.07));
            setMaxShield((int) (getMaxShield() * 1.07));
            setStrength((int) (getStrength() * 1.07));
        }
    }

    public void increaseExp(int vTotalExp) {
        setExp(getExp() + vTotalExp);
    }

    public void addToParty() {
        isParty = true;
        GameInfo.activeParty.add(this);
    }

    public void removeFromParty() {
        isParty = false;
        GameInfo.activeParty.remove(this);
    }

    public static void moveToNextRoom(Direction userChoice) {
        if (GameInfo.activeRoom != null) {
            if (GameInfo.activeRoom.getNextRoom().get(userChoice) != null) {
                GameInfo.activeRoom = GameInfo.activeRoom.getNextRoom().get(userChoice);
                System.out.println("\nYou are moving into the next room...");
            } else {
                System.out.println("\nThere is no room in that way!");
            }
        } else {
            System.out.println("Error! Active Room is null!\n");
        }
    }

    // Getter & Setter
    public boolean isParty() { return isParty; }

    public WeaponType getWeaponType() { return weaponType; }

    public ArmorType getArmorType() { return armorType; }

    public int getExpToLevelUp() { return expToLevelUp; }

    public void setExpToLevelUp(int vExpToLevelUp) { expToLevelUp = vExpToLevelUp; }
}
