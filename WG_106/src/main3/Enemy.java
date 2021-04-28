package main3;

import required.Figure;

public class Enemy extends Figure {
    private int gold;

    public Enemy(String vName, String vDescription, Integer vLevel, Integer vExp,
                 Integer vMaxHealth, Integer vStrength, Integer vMaxShield) {
        super(vName, vDescription, vLevel, vExp, vMaxHealth, vStrength, vMaxShield);
    }

    @Override
    public void describe() {
        super.describe();
//        System.out.println("Gold : " + gold);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int vGold) {
        gold = vGold;
    }
}
