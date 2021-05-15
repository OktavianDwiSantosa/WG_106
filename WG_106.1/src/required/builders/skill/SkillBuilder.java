package required.builders.skill;

import main.Skill;
import required.Figure;
import required.enums.SkillType;

public interface SkillBuilder<T extends SkillBuilder<T>> {

    T name(String vName);

    T description(String vDescription);

    T owner(Figure vOwner);

    T unlockedLevel(int vUnlockedLevel);

    T skillType(SkillType vSkillType);

    T targetFoe(boolean vTargetFoe);

    T timestamp(int vTimestamp);

    T strengthMultiplier(double vStrengthMultiplier);

    T healthMultiplier(double vHealthMultiplier);

    T shieldMultiplier(double vShieldMultiplier);

    Skill build();

}
