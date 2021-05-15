package required.delegation;

import required.Figure;
import required.Multiplier;
import required.enums.Operator;

public class Health extends Multiplier {
    public Health(double vProb) { super(vProb); }

    @Override
    public int getValue(Figure target) { return (int) (getProb() * target.getMaxHealth()); }

    @Override
    public void applyEqValue(Operator vOP, Figure target) {
        boolean fullHealth = target.getHealth() == target.getMaxHealth();
        target.setMaxHealth((int) vOP.apply(target.getMaxHealth(), getValue(target)));

        if (fullHealth) target.setHealth(target.getMaxHealth());
    }

    @Override
    public void printText() {
        System.out.printf("%-10s", "Heal");
    }

    @Override
    public void applySkValue(Figure owner, Figure target) {
        if (target.getHealth() < target.getMaxHealth()) {
            target.setHealth(getValue(target) + target.getHealth());
            if (target.getHealth() > target.getMaxHealth())
                target.setHealth(target.getMaxHealth());
        }
    }
}
