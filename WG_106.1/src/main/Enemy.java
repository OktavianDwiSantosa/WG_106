package main;

import required.Figure;

public class Enemy extends Figure {
    private final int gold;

    public Enemy(String vName, String vDescription, int vLevel, int vExp,
                 int vMaxHealth, int vStrength, int vMaxShield, int vGold) {
        super(vName, vDescription, vLevel, vExp, vMaxHealth, vStrength, vMaxShield);
        gold = vGold;
    }

    @Override
    public void describe() { // describe di luar battle
        /*
         * 1. Nama
         * 2. Deskripsi
         * 3. Max Health + Strength + Max Shield
         * 4. Skill
         * 5. Drop Item
         */
        super.describe();
    }

    public int getGold() {
        return gold;
    }

}
