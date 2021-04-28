package required;

public abstract class Figure extends Thing {
    private int level; // level -> 1 - 50
    private int exp; // mulai dari 1
    private int maxHealth;
    private int health;
    private int strength;
    private int maxShield;
    private int shield;
    private boolean isAction; // apakah sudah melakukan aksi ketika battle?

    public Figure(String vName, String vDescription, Integer vLevel, Integer vExp,
                  Integer vMaxHealth, Integer vStrength, Integer vMaxShield) {
        super(vName, vDescription);
        level = vLevel;
        exp = vExp;
        maxHealth = vMaxHealth;
        health = maxHealth;
        strength = vStrength;
        maxShield = vMaxShield;
        shield = maxShield;
    }

/*
    @Override
    public void describe() {
        super.describe();
        System.out.println("Level       : " + level);
        System.out.println("EXP         : " + exp);
        System.out.println("Max Health  : " + maxHealth);
        System.out.println("Health      : " + health);
        System.out.println("Strength    : " + strength);
        System.out.println("Max Shield  : " + maxShield);
        System.out.println("Shield      : " + shield);
    }
*/

    @Override
    public void describe() {
        System.out.println(getName());

        System.out.printf("%-10s%-10s%-10s%-10s%n",
                "Level", "Health", "Strength", "Shield");
        System.out.printf("%-10s%-10s%-10s%-10s%n",
                getLevel(), getHealth(), getStrength(), getShield());
    }

    public void printSkill(int figureIndex) {
        int i = 1;
        for (Thing objThing : getArrThing()) {
            if (objThing.getTrueClassType().equals("Skill")) {
                System.out.print("\n" + "[" + figureIndex + i + "] ");
                objThing.describe();
                i++;
            }
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
