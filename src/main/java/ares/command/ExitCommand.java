package ares.command;

import ares.exception.AresException;
import ares.ui.Ui;

import ares.task.TaskList;

import ares.storage.Storage;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a farewell message.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBye();
    }

    /**
     * Indicates that the command is exit command.
     *
     * @return Returns true to signal that this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}