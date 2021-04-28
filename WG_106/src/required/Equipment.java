package required;

public abstract class Equipment extends Item {
    private boolean isEquip; // Item sedang dipakai?

    // nilai pengali untuk menentukan nilai asli
    private double strengthMultiplier;
    private double healthMultiplier;
    private double shieldMultiplier;

    // nilai asli yang akan ditambahkan pada class Figure
    private int strengthValue;
    private int healthValue;
    private int shieldValue;

    public Equipment(String vName, String vDescription, Thing vOwner, int vPrice, String vRarity,
                     double vStrengthMultiplier, double vHealthMultiplier, double vShieldMultiplier) {
        super(vName, vDescription, vOwner, vPrice, vRarity);
        strengthMultiplier = vStrengthMultiplier;
        healthMultiplier = vHealthMultiplier;
        shieldMultiplier = vShieldMultiplier;
        strengthValue = 0;
        healthValue = 0;
        shieldValue = 0;
    }

    @Override
    public void describe() {
        super.describe();
        if (isEquip()) {
            System.out.println("Item has been equipped");
        } else {
            System.out.println("Item hasn't been equipped");
        }
    }

    public void equipped() {

    }

    public void unequipped() {

    }

    // Getter & Setter
    public boolean isEquip() {
        return isEquip;
    }

    public void setEquip(boolean vEquip) {
        isEquip = vEquip;
    }

    public double getStrengthMultiplier() {
        return strengthMultiplier;
    }

    public void setStrengthMultiplier(double vStrengthMultiplier) {
        strengthMultiplier = vStrengthMultiplier;
    }

    public double getHealthMultiplier() {
        return healthMultiplier;
    }

    public void setHealthMultiplier(double vHealthMultiplier) {
        healthMultiplier = vHealthMultiplier;
    }

    public double getShieldMultiplier() {
        return shieldMultiplier;
    }

    public void setShieldMultiplier(double vShieldMultiplier) {
        shieldMultiplier = vShieldMultiplier;
    }

    public int getStrengthValue() {
        return strengthValue;
    }

    public void setStrengthValue(int vStrengthValue) {
        strengthValue = vStrengthValue;
    }

    public int getHealthValue() {
        return healthValue;
    }

    public void setHealthValue(int vHealthValue) {
        healthValue = vHealthValue;
    }

    public int getShieldValue() {
        return shieldValue;
    }

    public void setShieldValue(int vShieldValue) {
        shieldValue = vShieldValue;
    }
}
