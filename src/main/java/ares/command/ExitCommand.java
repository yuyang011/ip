package ares.command;

import ares.ui.Ui;
import ares.task.TaskList;
import ares.storage.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}