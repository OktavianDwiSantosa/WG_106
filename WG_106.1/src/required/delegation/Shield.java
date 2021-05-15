package required.delegation;

import required.Figure;
import required.Multiplier;
import required.enums.Operator;

public class Shield extends Multiplier {
    public Shield(double vProb) { super(vProb); }

    @Override
    public int getValue(Figure target) { return (int) (getProb() * target.getMaxShield()); }

    @Override
    public void applyEqValue(Operator vOp, Figure target) {
        boolean fullShield = target.getShield() == target.getMaxShield();
        target.setMaxShield((int) vOp.apply(target.getMaxShield(), getValue(target)));

        if (fullShield) target.setShield(target.getMaxShield());
    }

    @Override
    public void printText() {
        System.out.printf("%-10s", "Shield");
    }

    @Override
    public void applySkValue(Figure owner, Figure target) {
        if (target.getShield() < target.getMaxShield()) {
            target.setShield(getValue(target) + target.getShield());
            if (target.getShield() > target.getMaxShield())
                target.setShield(target.getMaxShield());
        }
    }
}
