package main3;

import required.Equipment;
import required.Figure;
import required.Thing;

public class Skill extends Equipment {
    private String skillType; // Light, Normal, & Heavy
    private boolean targetFoe; // apakah Skill ini dilancarkan untuk pihak lawan?
    private int timestamp; // waktu yang dibutuhkan untuk melancarkan Skill
    private Figure tempTarget; // target dari Skill

    public Skill(String vName, String vDescription, Thing vOwner, int vPrice, String vRarity,
                 double vStrengthMultiplier, double vHealthMultiplier, double vShieldMultiplier,
                 String vSkillType, boolean vTargetFoe, int vTimestamp) {
        super(vName, vDescription, vOwner, vPrice, vRarity, vStrengthMultiplier, vHealthMultiplier, vShieldMultiplier);
        skillType = vSkillType;
        targetFoe = vTargetFoe;
        timestamp = vTimestamp;
        this.setStrengthValue((int) (((Figure) vOwner).getStrength() * vStrengthMultiplier));
        this.setHealthValue((int) (((Figure) vOwner).getMaxHealth() * vHealthMultiplier));
        this.setShieldValue((int) (((Figure) vOwner).getMaxShield() * vShieldMultiplier));
    }

    public Skill(String vName, String vDescription, Thing vOwner, String vRarity,
                 double vStrengthMultiplier, String vSkillType, int vTimestamp) {
        /*
         * secara default inilah constructor untuk Skill yang ditargetkan ke pihak lawan
         * dari pengguna Skill ini. Skill ini hanya memiliki daya rusak untuk pihak
         * lawan
         */
        this(vName, vDescription, vOwner, 0, vRarity,
                vStrengthMultiplier, 0, 0,
                vSkillType, true, vTimestamp);
    }

    public Skill(String vName, String vDescription, Thing vOwner, String vRarity,
                 double vHealthMultiplier, double vShieldMultiplier, String vSkillType, int vTimestamp) {
        /*
         * secara default inilah constructor untuk Skill yang ditargetkan ke pihak kawan
         * dari pengguna Skill ini. Skill ini memiliki daya penyembuhan dan perlindungan
         * untuk pihak kawan
         */
        this(vName, vDescription, vOwner, 0, vRarity,
                0, vHealthMultiplier, vShieldMultiplier,
                vSkillType, false, vTimestamp);
    }

    /*
        @Override
        public void describe() {
            super.describe();
            System.out.println("Type : " + skillType);
            System.out.println("Timestamp : " + timestamp);
        }
    */

/*    @Override
    public void describe() {
        System.out.println(getName());
        if (isTargetFoe()) {
            System.out.println("Target : Foe");
        } else {
            System.out.println("Target : Ally");
        }
        System.out.println("Damage Deals : " + getStrengthValue());
        System.out.println("Heal Power : " + getHealthValue());
        System.out.println("Defense Power : " + getShieldValue());
        System.out.println("Type : " + skillType);
        System.out.println("Timestamp : " + timestamp);
    }*/

    @Override
    public void describe() {
        String vTarget;
        if (isTargetFoe()) {
            vTarget = "Foe";
        } else {
            vTarget = "Ally";
        }

        System.out.println(getName());

        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%n",
                "Target", "DMG", "HEAL", "DEF", "Type", "Timestamp");
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%n",
                vTarget, getStrengthValue(), getHealthValue(),
                getShieldValue(), getSkillType(), getTimestamp());
    }

//    public void damageDeal() {
//        double damageDeal = (((Figure) getOwner()).getStrength() * getStrengthMultiplier());
//        setStrengthValue((int) damageDeal);
//    }
//
//    public void healPower() {
//        double healPower = (((Figure) getOwner()).getHealth() * getHealthMultiplier());
//        setStrengthValue((int) healPower);
//    }
//
//    public void defensePower() {
//        double defensePower = (((Figure) getOwner()).getShield() * getShieldMultiplier());
//        setStrengthValue((int) defensePower);
//    }
//
//    public void calculateSkill() {
//        damageDeal();
//        healPower();
//        defensePower();
//    }

    public void damage(Figure target) {
        if (getStrengthValue() > 0) {
            if (target.getShield() > 0) {
                // rusak shield target
                target.setShield(target.getShield() - getStrengthValue());
                if (target.getShield() <= 0) {
                    target.setShield(0);
                }
            } else {
                // kurangi health target
                target.setHealth(target.getHealth() - getStrengthValue());
                if (target.getHealth() <= 0) {
                    target.setHealth(0);
                }
            }
        }
    }

    public void repair(Figure target) {
        if (getHealthValue() > 0) {
            // tambah health target
            target.setHealth(target.getHealth() + getHealthValue());
            if (target.getHealth() > target.getMaxHealth()) {
                target.setHealth(target.getMaxHealth());
            }
        }

        if (getShieldValue() > 0) {
            // tambah shield target
            target.setShield(target.getShield() + getShieldValue());
            if (target.getShield() > target.getMaxShield()) {
                target.setShield(target.getMaxShield());
            }
        }
    }

    public void execute() {
        // menunjuk ke alamat owner, bukan membuat objek Figure
        Figure target = getTempTarget();

        // rusak shield dulu --> baru kurangi health
        damage(target);

        // tambah shield & health
        repair(target);
    }

    // Getter & Setter
    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String vSkillType) {
        skillType = vSkillType;
    }

    public boolean isTargetFoe() {
        return targetFoe;
    }

    public void setTargetFoe(boolean vTargetFoe) {
        targetFoe = vTargetFoe;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int vTimestamp) {
        timestamp = vTimestamp;
    }

    public Figure getTempTarget() {
        return tempTarget;
    }

    public void setTempTarget(Figure vTempTarget) {
        tempTarget = vTempTarget;
    }
}
