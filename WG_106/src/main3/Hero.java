package main3;

import required.Figure;

public class Hero extends Figure {
    private boolean isParty; // terdapat di arrActiveParty?
    private String weaponType; // weaponType -> Sword, Projectile, Gauntlet, Saber, CyberWare
    private static Room activeRoom; // Ruangan aktif semua Hero

    public Hero(String vName, String vDescription, Integer vLevel, Integer vExp,
                Integer vMaxHealth, Integer vStrength, Integer vMaxShield) {
        super(vName, vDescription, vLevel, vExp, vMaxHealth, vStrength, vMaxShield);
    }

    @Override
    public void describe() {
        super.describe();
//        System.out.println("Active Party : " + isParty);
//        System.out.println("Weapon Type : " + weaponType);
    }

    // Getter & Setter
    public boolean isParty() {
        return isParty;
    }

    public void setParty(boolean vParty) {
        isParty = vParty;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String vWeaponType) {
        weaponType = vWeaponType;
    }

    public static Room getActiveRoom() {
        return activeRoom;
    }

    public static void setActiveRoom(Room vActiveRoom) {
        activeRoom = vActiveRoom;
    }
}
