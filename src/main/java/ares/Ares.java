package ares;

import ares.command.Command;
import ares.exception.AresException;
import ares.parser.Parser;
import ares.storage.Storage;
import ares.task.TaskList;
import ares.ui.Ui;

/**
 * Acts as the entry point for the Ares bot Main class.
 */
public class Ares {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Ares with a specific filePath.
     *
     * @param filePath The path to the file where tasks are stored or loaded from.
     */
    public Ares(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AresException e) {
            ui.printAresException(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Ares bot program.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readNextLine();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                assert tasks != null : "Tasklist cannot be null!";
                assert ui != null : "Ui cannot be null!";
                assert storage != null : "Storage cannot be null!";
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AresException e) {
                ui.printAresException(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (AresException e) {
            return ui.printAresException(e.getMessage());
        }
    }

    /**
     * Prints welcome message.
     */
    public String sayHi() {
        return ui.printWelcome();
    }

    /**
     * Acts as Ares bot program entry point.
     */
    public static void main(String[] args) {
        new Ares("data/ares.txt").run();
    }
}
