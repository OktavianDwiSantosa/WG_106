package required.builders.skill;

import main.Hero;
import main.Skill;
import required.Figure;
import required.Multiplier;
import required.delegation.*;
import required.enums.ArmorType;
import required.enums.SkillType;
import required.enums.WeaponType;

import java.util.ArrayList;

public class SkillBuilders implements SkillBuilder<SkillBuilders> {

    private String name;
    private String description;
    private Figure owner;
    private int unlockedLevel;
    private SkillType skillType;
    private boolean targetFoe;
    private int timestamp;
    private final ArrayList<Multiplier> multipliers = new ArrayList<>();

    @Override
    public SkillBuilders name(String vName) {
        if (vName == null || vName.isEmpty())
            throw new IllegalArgumentException("Name cannot be empty!");

        name = vName;
        return this;
    }

    @Override
    public SkillBuilders description(String vDescription) {
        if (vDescription == null || vDescription.isEmpty())
            throw new IllegalArgumentException("Description cannot be empty!");

        description = vDescription;
        return this;
    }

    @Override
    public SkillBuilders owner(Figure vOwner) {
        if (vOwner == null)
            throw new IllegalArgumentException("Owner cannot be empty!");

        owner = vOwner;
        return this;
    }

    @Override
    public SkillBuilders unlockedLevel(int vUnlockedLevel) {
        if (vUnlockedLevel < 0)
            throw new IllegalArgumentException("Unlocked Level cannot be smaller than 0!");

        unlockedLevel = vUnlockedLevel;
        return this;
    }

    @Override
    public SkillBuilders skillType(SkillType vSkillType) {
        if (vSkillType == null)
            throw new IllegalArgumentException("Skill Type cannot be empty!");

        skillType = vSkillType;
        return this;
    }

    @Override
    public SkillBuilders targetFoe(boolean vTargetFoe) {
        targetFoe = vTargetFoe;
        return this;
    }

    @Override
    public SkillBuilders timestamp(int vTimestamp) {
        if (vTimestamp < 1)
            throw new IllegalArgumentException("Timestamp cannot be smaller than 1!");

        timestamp = vTimestamp;
        return this;
    }

    @Override
    public SkillBuilders strengthMultiplier(double vStrengthMultiplier) {
        if (vStrengthMultiplier < 0)
            throw new IllegalArgumentException("Strength Multiplier cannot be smaller than 0!");

        multipliers.add(new Strength(vStrengthMultiplier));
        return this;
    }

    @Override
    public SkillBuilders healthMultiplier(double vHealthMultiplier) {
        if (vHealthMultiplier < 0)
            throw new IllegalArgumentException("Health Multiplier cannot be smaller than 0!");

        multipliers.add(new Health(vHealthMultiplier));
        return this;
    }

    @Override
    public SkillBuilders shieldMultiplier(double vShieldMultiplier) {
        if (vShieldMultiplier < 0)
            throw new IllegalArgumentException("Shield Multiplier cannot be smaller than 0!");

        multipliers.add(new Shield(vShieldMultiplier));
        return this;
    }

    public Skill build() {
        return new Skill(name, description, owner, unlockedLevel,
                skillType, targetFoe, timestamp, multipliers);
    }

    public static void main(String[] args) {
        Hero hero = new Hero("Elon", "", 1, 0,
                50, 15, 20, WeaponType.SWORD, ArmorType.HEAVY);

        Skill skill = new SkillBuilders()
                .name("Slash")
                .description("-")
                .owner(hero)
                .unlockedLevel(1)
                .skillType(SkillType.LIGHT)
                .targetFoe(true)
                .timestamp(15)
                .strengthMultiplier(0.2)
                .shieldMultiplier(0.025)
                .build();

        System.out.println(skill.toString());
    }
}
