package required;

public abstract class Figure extends Thing {
    private int level; // level -> 1 - 30
    private int exp; // mulai dari 1
    private int maxHealth;
    private int health;
    private int strength;
    private int maxShield;
    private int shield;
    private boolean isAction; // apakah sudah melakukan aksi ketika battle?

    public Figure(String vName, String vDescription, int vLevel, int vExp,
                  int vMaxHealth, int vStrength, int vMaxShield) {
        super(vName, vDescription);
        level = vLevel;
        exp = vExp;
        maxHealth = vMaxHealth;
        health = maxHealth;
        strength = vStrength;
        maxShield = vMaxShield;
        shield = maxShield;
    }

    public void describeInBattle() {
        System.out.println(getName());

        System.out.printf("%-10s%-10s%-10s%-10s%n",
                "Level", "Health", "Strength", "Shield");
        System.out.printf("%-10s%-10s%-10s%-10s%n",
                getLevel(), getHealth() + "/" + getMaxHealth(), getStrength(), getShield() + "/" + getMaxShield());
    }

    public void printSkills(int figureIndex) {
        int i = 1;
        for (Thing objThing : getArrThing())
            if (objThing.getTrueClassName().equals("Skill")) {
                System.out.print("\n" + "[" + figureIndex + i + "] ");
                objThing.describe();
                i++;
            }
    }

    // Getter & Setter
    public int getLevel() {
        return level;
    }

    public void setLevel(int vLevel) {
        level = vLevel;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int vExp) {
        exp = vExp;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int vMaxHealth) {
        maxHealth = vMaxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int vHealth) {
        health = vHealth;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int vStrength) {
        strength = vStrength;
    }

    public int getMaxShield() {
        return maxShield;
    }

    public void setMaxShield(int vMaxShield) {
        maxShield = vMaxShield;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int vShield) {
        shield = vShield;
    }

    public boolean isAction() {
        return isAction;
    }

    public void setAction(boolean vAction) {
        isAction = vAction;
    }
}
