package ares;

import ares.storage.Storage;
import ares.ui.Ui;
import ares.task.TaskList;
import ares.exception.AresException;
import ares.command.Command;
import ares.parser.Parser;

/**
 * Main class that acts as the entry point for the Ares bot.
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
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AresException e) {
                ui.printAresException(e.getMessage());
            }
        }
    }

    /**
     * Entry point of the Ares bot program.
     */
    public static void main(String[] args) {
        new Ares("data/ares.txt").run();
    }
}
