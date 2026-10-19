/**
 * Automated tests for the calculator.
 *
 *   javac *.java
 *   java CalculatorTest
 */
public class CalculatorTest {

    private static int passed = 0;
    private static int failed = 0;

    private static void check(String name, String expected, String actual) {
        if (expected.equals(actual)) {
            passed++;
        } else {
            failed++;
            System.out.println("FAILED " + name + ": expected <" + expected + ">, got <" + actual + ">");
        }
    }

    private static String compute(double a, String op, double b) {
        return Calculator.format(Calculator.calculate(a, op, b));
    }

    private static String exceptionOf(double a, String op, double b) {
        try {
            Calculator.calculate(a, op, b);
            return "no exception";
        } catch (RuntimeException e) {
            return e.getClass().getSimpleName();
        }
    }

    public static void main(String[] args) {
        check("Addition", "5", compute(2, "+", 3));
        check("Subtraction", "-1", compute(2, "-", 3));
        check("Multiplication", "12", compute(3, "*", 4));
        check("Multiplication with x", "12", compute(3, "x", 4));
        check("Division", "2.5", compute(5, "/", 2));
        check("Modulo", "1", compute(7, "%", 3));
        check("Power", "1024", compute(2, "^", 10));
        check("Large power", "1.2676506002282294E30", compute(2, "^", 100));
        check("Negative exponent", "0.125", compute(2, "^", -3));
        check("Undefined result", "undefined", compute(-8, "^", 0.5));
        check("Division by zero", "ArithmeticException", exceptionOf(1, "/", 0));
        check("Modulo by zero", "ArithmeticException", exceptionOf(1, "%", 0));
        check("Unknown operator", "IllegalArgumentException", exceptionOf(1, "?", 2));
        check("Operator recognized", "true", String.valueOf(Calculator.isOperator("^")));
        check("Operator unknown", "false", String.valueOf(Calculator.isOperator("?")));

        System.out.println(passed + " tests passed, " + failed + " failed.");
        if (failed > 0) {
            System.exit(1);
        }
    }
}
