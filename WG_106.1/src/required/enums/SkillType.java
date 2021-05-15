package required.enums;

public enum SkillType {
    LIGHT(1, "Light"), NORMAL(2, "Normal"), HEAVY(3, "Heavy");

    private final int order;
    private final String name;

    SkillType(int vOrder, String vName) {
        order = vOrder;
        name = vName;
    }

    @Override
    public String toString() { return name; }

    public int getOrder() { return order; }
}
