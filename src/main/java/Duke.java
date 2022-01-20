import java.util.Scanner;
import style.Style;
import tasks.Commands;
import tasks.TaskManager;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String greetingMessage = "Hello! I am Morty, Rick's assistant.\nWhat can I do for you, today?";
        String inputMessage;
        String farewellMessage = "Hope you had fun talking to me, and bye!\nHave a nice day ahead!";

        Style.printStylised(greetingMessage);

        while(sc.hasNext()) {
            inputMessage = sc.nextLine();
            if (inputMessage.equals(Commands.bye.toString())) {
                Style.printStylised(farewellMessage);
                break;
            } else {
                TaskManager.process(inputMessage);
            }
        }
    }
}
