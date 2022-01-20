package style;

public class Style {
    private static String hline = "--------------------------------------";
    public static void printStylised(String message) {
        System.out.println(String.format("%s\n%s\n%s", hline, message, hline));
    }
}
