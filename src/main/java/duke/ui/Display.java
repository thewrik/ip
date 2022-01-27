package duke.ui;

public class Display {
    private static final String hline = "----------------------------------------------";

    static final String greetingMessage = "Hello! I am Morty, Rick's assistant.\nWhat can I do for you, today?";
    static final String farewellMessage = "Hope you had fun talking to me, and bye!\nHave a nice day ahead!";

    public static void printStylised(Object message) {
        System.out.println(String.format("%s\n%s\n%s", hline, message.toString(), hline));
    }

    public static void greet() {
        printStylised(greetingMessage);
    }

    public static void exit() {
        printStylised(farewellMessage);
    }
}
