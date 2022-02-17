package duke.ui;

import duke.commands.Commands;
import duke.tasks.TaskManager;

import java.util.Scanner;

public class Parser {

    static final Scanner sc = new Scanner(System.in);

    private static void process(String inputMessage) {
        if (inputMessage.trim().equals(Commands.list.toString())) {
            Display.printStylised(TaskManager.list());
        } else if (inputMessage.startsWith(Commands.mark.toString())) {
            Display.printStylised(TaskManager.mark(Integer.parseInt(inputMessage.substring(Commands.mark.toString().length() + 1))));
            TaskManager.save();
        } else if (inputMessage.startsWith(Commands.unmark.toString())) {
            Display.printStylised(TaskManager.unmark(Integer.parseInt(inputMessage.substring(Commands.unmark.toString().length() + 1))));
            TaskManager.save();
        } else if (inputMessage.startsWith(Commands.delete.toString())) {
            Display.printStylised(TaskManager.delete(Integer.parseInt(inputMessage.substring(Commands.delete.toString().length() + 1))));
            TaskManager.save();
        } else if (inputMessage.startsWith(Commands.find.toString())) {
            Display.printStylised(TaskManager.find(inputMessage.substring(Commands.find.toString().length() + 1)));
        }  else if (inputMessage.startsWith(Commands.findBetter.toString())) {
            Display.printStylised(TaskManager.find(inputMessage.substring(Commands.findBetter.toString().length() + 1)));
        } else {
            try {
                Display.printStylised(TaskManager.generateAndAdd(inputMessage));
                TaskManager.save();
            } catch (Exception e) {
                Display.printStylised(e.getMessage());
            }
        }
    }

    public static void parseInput() {
        String inputMessage;
        while (sc.hasNext()) {
            inputMessage = sc.nextLine();
            if (inputMessage.equals(Commands.bye.toString())) {
                Display.exit();
                break;
            } else {
                process(inputMessage);
            }
        }
    }
}
