package required.builders.equipment;

import required.Equipment;
import required.Thing;
import required.enums.RarityType;

/*
    Generic Types : https://www.programiz.com/java-programming/generics --> ini dulu baru yang di bawah
    SkillBuilder Pattern x Generic : http://www.javabyexamples.com/lets-discuss-builder-pattern --> recommend
    SkillBuilder x Inherit : https://ducmanhphan.github.io/2020-04-06-how-to-apply-builder-pattern-with-inhertitance/
    Subclass SkillBuilder : https://stackoverflow.com/questions/17164375/subclassing-a-java-builder-class
    Get This Actual Type : http://www.angelikalanger.com/GenericsFAQ/FAQSections/ProgrammingIdioms.html#FAQ205
    Subclass SkillBuilder Next : https://chrononaut.org/2012/02/24/subclassing-with-blochs-builder-pattern/
    Return Subclass : https://dan.iftodi.com/2020/03/abstractbuilder-pattern-that-returns-the-subclass-instance/
 */

public interface EquipmentBuilder<SELF extends EquipmentBuilder<SELF, TTarget>,
        TTarget extends Equipment> {

    SELF name(String vName);

    SELF description(String vDescription);

    SELF owner(Thing vOwner);

    SELF price(int vPrice);

    SELF rarity(RarityType vRarity);

    SELF level(int vLevel);

    SELF strengthMultiplier(double vStrengthMultiplier);

    SELF healthMultiplier(double vHealthMultiplier);

    SELF shieldMultiplier(double vShieldMultiplier);

    SELF requiredMaterials(String vMaterialName, int vAmount);

    TTarget build();

}
