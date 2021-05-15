package main;

import required.Figure;
import required.Multiplier;
import required.Thing;
import required.delegation.*;
import required.enums.SkillType;

import java.util.ArrayList;

public class Skill extends Thing {
    private final Figure owner; // Pemilik skill --> ketika cukup level --> unlocked skill
    private final int unlockedLevel; // terbuka ketika unlockedLevel == owner.level
    private final SkillType skillType; // Light, Normal, & Heavy
    private final boolean targetFoe; // apakah Skill ini dilancarkan untuk pihak lawan?
    private final int timestamp; // waktu yang dibutuhkan untuk melancarkan Skill
    private final ArrayList<Multiplier> multipliers;
    private Figure tempTarget; // target dari Skill --> dipilih di battleSystem

    /*
     * GoF design pattern - SkillBuilder
     * https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
     */

    public Skill(String vName, String vDescription, Figure vOwner, int vUnlockedLevel,
                 SkillType vSkillType, boolean vTargetFoe, int vTimestamp,
                 ArrayList<Multiplier> vMultipliers) {
        super(vName, vDescription);
        owner = vOwner;
        unlockedLevel = vUnlockedLevel;
        skillType = vSkillType;
        targetFoe = vTargetFoe;
        timestamp = vTimestamp;
        multipliers = vMultipliers;
    }

    @Override
    public void describe() {
        String vTarget = isTargetFoe() ? "Foe" : "Ally"; // ternary operator --> true == "Foe"

        System.out.println(getName());
        System.out.printf("%-10s%-10s%-10s", "Target", "Type", "Timestamp");
        multipliers.forEach(Multiplier::printText);
        System.out.printf("%n%-10s%-10s%-10s", vTarget, skillType, timestamp);
        multipliers.forEach(multiplier -> multiplier.printSkillValue(owner));
    }

    public void damage() { //  hanya bisa merusak lawan
        for (Multiplier multiplier : multipliers)
            if (multiplier instanceof Strength)
                multiplier.applySkValue(owner, tempTarget);
    }

    public void repair() { // hanya bisa memperbaiki kawan
        for (Multiplier multiplier : multipliers)
            if (multiplier instanceof Health)
                multiplier.applySkValue(owner, tempTarget);

        for (Multiplier multiplier : multipliers)
            if (multiplier instanceof Shield)
                multiplier.applySkValue(owner, tempTarget);
    }

    public void execute() {
        // rusak shield lawan dulu --> baru kurangi health lawan
        damage(); // jika terdapat strength multiplier

        // tambah shield & health kawan
        repair(); // jika terdapat health dan shield multiplier
    }

    public int getDamage() {
        for (Multiplier multiplier : multipliers)
            if (multiplier instanceof Strength)
                return multiplier.getValue(owner);
        return 0;
    }

    public int getHeal() {
        for (Multiplier multiplier : multipliers)
            if (multiplier instanceof Health)
                return multiplier.getValue(owner);
        return 0;
    }

    public int getDefense() {
        for (Multiplier multiplier : multipliers)
            if (multiplier instanceof Shield)
                return multiplier.getValue(owner);
        return 0;
    }

    // Getter & Setter
    public boolean isTargetFoe() { return targetFoe; }

    public int getTimestamp() { return timestamp; }

    public Figure getTempTarget() { return tempTarget; }

    public void setTempTarget(Figure vTempTarget) { tempTarget = vTempTarget; }

    public Figure getOwner() { return owner; }

}
