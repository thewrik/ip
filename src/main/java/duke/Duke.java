package duke;

import duke.storage.Storage;
import duke.ui.Display;
import duke.ui.Parser;


public class Duke {
    public static void main(String[] args) {

        Storage.initialise();
        Display.greet();
        Parser.parseInput();
    }
}
