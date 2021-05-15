package required.enums;

public enum Operator {
    ADDITION("+") {
        @Override
        public double apply(double x1, double x2) {
            return x1 + x2;
        }
    },
    SUBTRACTION("-") {
        @Override
        public double apply(double x1, double x2) {
            return x1 - x2;
        }
    },
    MULTIPLICATION("*") {
        @Override
        public double apply(double x1, double x2) {
            return x1 * x2;
        }
    },
    DIVISION("/") {
        @Override
        public double apply(double x1, double x2) {
            if (x2 != 0)
                return x1 / x2;
            else
                System.out.println("Can't divide by zero.");
            return 0;
        }
    };

    // Enum di Java, sangat powerful mantap jiwa! :)
    public abstract double apply(double x1, double x2);

    private final String text;

    Operator(String vText) { text = vText; }

    @Override
    public String toString() { return text; }
}
