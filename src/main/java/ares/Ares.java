package ares;

import ares.storage.Storage;
import ares.ui.Ui;
import ares.task.TaskList;
import ares.exception.AresException;
import ares.command.Command;
import ares.parser.Parser;

public class Ares {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        new Ares("data/ares.txt").run();
    }
}
