import java.util.Scanner;
import style.Style;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        String greetingMessage = "Hello! I am Morty, Rick's assistant.\nWhat can I do for you, today?";
        String inputMessage;
        String farewellMessage = "Hope you had fun talking to me, and \nbye! Have a nice day ahead!";
        Style.printStylised(greetingMessage);

        while(sc.hasNext()) {
            inputMessage = sc.nextLine();
            if (inputMessage.equals("bye")) {
                Style.printStylised(farewellMessage);
                break;
            } else {
                Style.printStylised(inputMessage);
            }
        }
    }
}
