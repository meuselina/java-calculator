import java.util.Scanner;

/**
 * Einfacher Konsolen-Taschenrechner.
 * Unterstuetzt: + - * / % und Potenz (^)
 */
public class Taschenrechner {

    private static final java.util.List<String> OPERATOREN =
            java.util.Arrays.asList("+", "-", "*", "x", "/", "%", "^");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Taschenrechner ===");
        System.out.println("Operatoren: +  -  *  /  %  ^");
        System.out.println("Zum Beenden 'q' eingeben.\n");

        while (true) {
            System.out.print("Erste Zahl: ");
            String eingabe = scanner.next();
            if (eingabe.equalsIgnoreCase("q")) {
                break;
            }

            double a;
            try {
                a = Double.parseDouble(eingabe.replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ungueltige Zahl. Bitte erneut versuchen.\n");
                continue;
            }

            System.out.print("Operator: ");
            String operator = scanner.next();
            // Operator sofort pruefen, nicht erst nach der zweiten Zahl
            if (!istOperator(operator)) {
                System.out.println("Unbekannter Operator: " + operator + ". Erlaubt sind + - * / % ^\n");
                continue;
            }

            System.out.print("Zweite Zahl: ");
            double b;
            try {
                b = Double.parseDouble(scanner.next().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ungueltige Zahl. Bitte erneut versuchen.\n");
                continue;
            }

            try {
                double ergebnis = berechne(a, operator, b);
                System.out.printf("Ergebnis: %s %s %s = %s%n%n",
                        formatiere(a), operator, formatiere(b), formatiere(ergebnis));
            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println("Fehler: " + e.getMessage() + "\n");
            }
        }

        System.out.println("Programm beendet.");
        scanner.close();
    }

    /** Fuehrt die eigentliche Rechnung aus. */
    public static double berechne(double a, String operator, double b) {
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
                    throw new ArithmeticException("Division durch null ist nicht erlaubt.");
                }
                return a / b;
            case "%":
                if (b == 0) {
                    throw new ArithmeticException("Modulo mit null ist nicht erlaubt.");
                }
                return a % b;
            case "^":
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Unbekannter Operator: " + operator);
        }
    }

    /**
     * Ganze Zahlen ohne ".0" ausgeben.
     * Nur bei Zahlen, die sicher in einen long passen: vorher wurde z. B.
     * 2 ^ 100 faelschlich als 9223372036854775807 (groesster long) angezeigt.
     */
    static String formatiere(double wert) {
        if (Double.isNaN(wert)) {
            return "nicht definiert";
        }
        if (wert == Math.floor(wert) && Math.abs(wert) < 1e15) {
            return String.valueOf((long) wert);
        }
        return String.valueOf(wert);
    }

    /** Prueft, ob ein Operator unterstuetzt wird. */
    static boolean istOperator(String operator) {
        return OPERATOREN.contains(operator);
    }
}
