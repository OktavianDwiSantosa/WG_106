package required;

public abstract class Consumable extends Item {
    private boolean isUse; // Item digunakan?

    public Consumable(String vName, String vDescription, Thing vOwner, int vPrice, String vRarity) {
        super(vName, vDescription, vOwner, vPrice, vRarity);
    }

    protected abstract void used(Thing newOwner); // Material & MedKit memiliki penerapan used() yang berbeda

    // Getter & Setter
    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean vUse) {
        isUse = vUse;
    }
}
