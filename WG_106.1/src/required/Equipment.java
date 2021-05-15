package required;

import main.GameInfo;
import main.Hero;
import main.item.Material;
import required.enums.ModifiedType;
import required.enums.Operator;
import required.enums.RarityType;

import java.util.ArrayList;
import java.util.HashMap;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

// https://refactoring.guru/design-patterns/visitor
// https://refactoring.guru/design-patterns/visitor-double-dispatch

// TODO : buat method untuk memilah Equipment berdasarkan Type
// https://refactoring.guru/design-patterns/builder/java/example

public abstract class Equipment extends Item {
    private boolean isEquip; // Item sedang dipakai?
    private int level; // level --> 1 - 5
    private int upgradeCost;

    // nilai-nilai pengali strength, health & shield
    private final ArrayList<Multiplier> multipliers;
    // nama & jumlah Material yang diperlukan untuk upgrade
    private final HashMap<String, Integer> requiredMaterials;

    // Constructor
    public Equipment(String vName, String vDescription, Thing vOwner,
                     int vPrice, RarityType vRarity, int vLevel,
                     ArrayList<Multiplier> vMultipliers,
                     HashMap<String, Integer> vRequiredMaterials) {
        super(vName, vDescription, vOwner, vPrice, vRarity);
        if (vOwner instanceof Hero) {
            equipped((Hero) vOwner);
        } else if (vOwner != null) {
            moveItemTo(vOwner);
        }
        level = vLevel;
        upgradeCost = (int) (vPrice * 1.2);
        multipliers = vMultipliers;
        requiredMaterials = vRequiredMaterials;
    }

    // Method
    @Override
    public void describe() {
        super.describe();
        System.out.println(getTrueClassName() + " Level : " + level);
        multipliers.forEach(Multiplier::printProbability);
    }

    public void equipped(Hero vNewOwner) {
        if (!isEquip) {
            calcOwnerFields(Operator.ADDITION, vNewOwner);
            switchOwner(vNewOwner);
            isEquip = true;
        }
    }

    public void unequipped() {
        if (isEquip) {
            calcOwnerFields(Operator.SUBTRACTION, (Hero) getOwner());
            switchOwner(GameInfo.userInventory);
            isEquip = false;
        }
    }

    public boolean modified(int cost, ModifiedType vType) {
        addMaterials();

        if (GameInfo.userInventory.getGold() >= cost
                && countMaterials() == requiredMaterials.size()) {
            if (vType == ModifiedType.UPGRADE && level < 5) {
                increaseLevelAndCost();
                return true;
            } else if (vType == ModifiedType.CREATE && level <= 5) {
                useMaterials();
                return true;
            }
        }

        returnMaterials();
        return false;
    }

    private void increaseLevelAndCost() {
        level += 1; // urutan langkah ini diperlukan untuk upgrade
        upgradeCost += (int) (upgradeCost * (level * 0.2)); // rumus upgrade
        useMaterials();
    }

    public void calcOwnerFields(Operator vOp, Figure vOwner) {
        multipliers.forEach(multiplier -> multiplier.applyEqValue(vOp, vOwner));
    }

    public void addMaterials() { // tambahkan objek-objek Material yang diperlukan
        for (String requiredMaterial : requiredMaterials.keySet())
            for (Thing objMaterial : GameInfo.userInventory.getOneClass("Material"))
                if (objMaterial.getName().equals(requiredMaterial))
                    ((Material) objMaterial).moveItemTo(this);
    }

    public void useMaterials() { // used setiap objek Material
        for (String requiredMaterial : requiredMaterials.keySet()) {
            // jumlah objek Material yang dibutuhkan untuk modified
            int remainingMaterial = requiredMaterials.get(requiredMaterial);

            for (Thing objMaterial : getArrThing()) {
                if (objMaterial.getName().equals(requiredMaterial) && remainingMaterial > 0) {
                    // objek Material digunakan oleh Equipment
                    ((Material) objMaterial).used(this);
                    remainingMaterial--;
                } else if (objMaterial.getName().equals(requiredMaterial) && remainingMaterial == 0) {
                    // objek Material dikembalikan ke userInventory
                    ((Item) objMaterial).moveItemTo(GameInfo.userInventory);
                }
            }
        }
    }

    public void returnMaterials() { // pindahkan material ke userInventory
        getArrThing().forEach(objMaterial -> ((Item) objMaterial).moveItemTo(GameInfo.userInventory));
    }

    public int countMaterials() {
        // note : isi arrThing dari objek turunan Equipment hanya objek Material
        // hitung jumlah objek Material yang tersedia dan diperlukan untuk levelUp
        HashMap<String, Long> materials =
                (HashMap<String, Long>) getArrThing().stream()
                        .collect(groupingBy(Thing::getName, counting()));

        int availableItem = 0;
        for (String requiredMaterial : requiredMaterials.keySet())
            for (String availableMaterial : materials.keySet())
                if (requiredMaterial.equals(availableMaterial)
                        && requiredMaterials.get(requiredMaterial)
                        <= materials.get(availableMaterial).intValue())
                    // total akhir harus == jumlah persyaratan Material
                    availableItem++;

        return availableItem;
    }

    // Getter
    public boolean isEquip() { return isEquip; }

    public int getLevel() { return level; }

    public int getUpgradeCost() { return upgradeCost; }

    public ArrayList<Multiplier> getMultipliers() { return multipliers; }
}
