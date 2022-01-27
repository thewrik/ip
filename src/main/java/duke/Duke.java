package duke;

import duke.storage.Storage;
import duke.ui.Parser;
import duke.ui.Display;

public class Duke {
    public static void main(String[] args) {

        Storage.initialise();
        Display.greet();
        Parser.parseInput();
    }
}
