package required;

import required.enums.Operator;

public abstract class Multiplier {
    private final double prob;

    public Multiplier(double vProb) { prob = vProb; }

    public abstract int getValue(Figure target);

    public abstract void applyEqValue(Operator vOp, Figure target);

    public abstract void applySkValue(Figure owner, Figure target);

    public abstract void printText();

    public void printSkillValue(Figure target) {
        System.out.printf("%-10s", getValue(target));
    }

    public void printEqValue(Figure target) {
        System.out.println(getClass().getSimpleName() + " Value : " + getValue(target));
    }

    public void printProbability() {
        System.out.println(getClass().getSimpleName() + " + " + (prob * 100) + "%");
    }

    public double getProb() { return prob; }
}
