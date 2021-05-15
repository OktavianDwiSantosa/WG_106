package required.delegation;

import required.Figure;
import required.Multiplier;
import required.enums.Operator;

public class Strength extends Multiplier {
    public Strength(double vProb) { super(vProb); }

    @Override
    public int getValue(Figure target) { return (int) (getProb() * target.getStrength()); }

    @Override
    public void applyEqValue(Operator vOp, Figure target) {
        target.setStrength((int) vOp.apply(target.getStrength(), getValue(target)));
    }

    @Override
    public void printText() {
        System.out.printf("%-10s", "Damage");
    }

    @Override
    public void applySkValue(Figure owner, Figure target) {
        if (target.getShield() > 0) {
            target.setShield((int) Operator.SUBTRACTION
                    .apply(target.getShield(), getValue(owner)));
            if (target.getShield() < 0) target.setShield(0);
        } else if (target.getShield() == 0) {
            // else if agar shield benar-benar menyerap semua damage
            target.setHealth((int) Operator.SUBTRACTION
                    .apply(target.getHealth(), getValue(owner)));
            if (target.getHealth() < 0) target.setHealth(0);
        }
    }
}
