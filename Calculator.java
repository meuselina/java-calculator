import java.util.Scanner;

/**
 * Simple console calculator.
 * Supports: + - * / % and power (^)
 */
public class Calculator {

    private static final java.util.List<String> OPERATORS =
            java.util.Arrays.asList("+", "-", "*", "x", "/", "%", "^");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Calculator ===");
        System.out.println("Operators: +  -  *  /  %  ^");
        System.out.println("Type 'q' to quit.\n");

        while (true) {
            System.out.print("First number: ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("q")) {
                break;
            }

            double a;
            try {
                a = Double.parseDouble(input.replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.\n");
                continue;
            }

            System.out.print("Operator: ");
            String operator = scanner.next();
            // Check the operator right away, not only after the second number
            if (!isOperator(operator)) {
                System.out.println("Unknown operator: " + operator + ". Allowed are + - * / % ^\n");
                continue;
            }

            System.out.print("Second number: ");
            double b;
            try {
                b = Double.parseDouble(scanner.next().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.\n");
                continue;
            }

            try {
                double result = calculate(a, operator, b);
                System.out.printf("Result: %s %s %s = %s%n%n",
                        format(a), operator, format(b), format(result));
            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }

        System.out.println("Goodbye.");
        scanner.close();
    }

    /** Performs the actual calculation. */
    public static double calculate(double a, String operator, double b) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
            case "x":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                return a / b;
            case "%":
                if (b == 0) {
                    throw new ArithmeticException("Modulo by zero is not allowed.");
                }
                return a % b;
            case "^":
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    /**
     * Prints whole numbers without ".0".
     * Only for numbers that safely fit into a long: before this check,
     * 2 ^ 100 was wrongly shown as 9223372036854775807 (the largest long).
     */
    static String format(double value) {
        if (Double.isNaN(value)) {
            return "undefined";
        }
        if (value == Math.floor(value) && Math.abs(value) < 1e15) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    /** Checks whether an operator is supported. */
    static boolean isOperator(String operator) {
        return OPERATORS.contains(operator);
    }
}
