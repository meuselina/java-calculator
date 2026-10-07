/**
 * Automatische Tests fuer den Taschenrechner.
 *
 *   javac *.java
 *   java TaschenrechnerTest
 */
public class TaschenrechnerTest {

    private static int bestanden = 0;
    private static int fehlgeschlagen = 0;

    private static void pruefe(String name, String erwartet, String tatsaechlich) {
        if (erwartet.equals(tatsaechlich)) {
            bestanden++;
        } else {
            fehlgeschlagen++;
            System.out.println("FEHLER " + name + ": erwartet <" + erwartet + ">, bekommen <" + tatsaechlich + ">");
        }
    }

    private static String rechne(double a, String op, double b) {
        return Taschenrechner.formatiere(Taschenrechner.berechne(a, op, b));
    }

    private static String fehler(double a, String op, double b) {
        try {
            Taschenrechner.berechne(a, op, b);
            return "kein Fehler";
        } catch (RuntimeException e) {
            return e.getClass().getSimpleName();
        }
    }

    public static void main(String[] args) {
        pruefe("Addition", "5", rechne(2, "+", 3));
        pruefe("Subtraktion", "-1", rechne(2, "-", 3));
        pruefe("Multiplikation", "12", rechne(3, "*", 4));
        pruefe("Multiplikation mit x", "12", rechne(3, "x", 4));
        pruefe("Division", "2.5", rechne(5, "/", 2));
        pruefe("Modulo", "1", rechne(7, "%", 3));
        pruefe("Potenz", "1024", rechne(2, "^", 10));
        pruefe("Grosse Potenz", "1.2676506002282294E30", rechne(2, "^", 100));
        pruefe("Negative Potenz", "0.125", rechne(2, "^", -3));
        pruefe("Nicht definiert", "nicht definiert", rechne(-8, "^", 0.5));
        pruefe("Division durch null", "ArithmeticException", fehler(1, "/", 0));
        pruefe("Modulo durch null", "ArithmeticException", fehler(1, "%", 0));
        pruefe("Unbekannter Operator", "IllegalArgumentException", fehler(1, "?", 2));
        pruefe("Operator erkannt", "true", String.valueOf(Taschenrechner.istOperator("^")));
        pruefe("Operator unbekannt", "false", String.valueOf(Taschenrechner.istOperator("?")));

        System.out.println(bestanden + " Tests bestanden, " + fehlgeschlagen + " fehlgeschlagen.");
        if (fehlgeschlagen > 0) {
            System.exit(1);
        }
    }
}
