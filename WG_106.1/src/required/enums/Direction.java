package required.enums;

public enum Direction {
    W("North"), A("West"), S("South"), D("East");

    // https://www.jcombat.com/core-java/the-power-of-enums-in-java
    // https://dzone.com/articles/java-enums-you-have-grace
    // https://medium.com/javarevisited/advanced-java-enum-features-you-need-to-know-b516a191c7e2

    private final String text;

    // Constructor
    Direction(String vText) { text = vText; }

    @Override
    public String toString() { return text; }
}
