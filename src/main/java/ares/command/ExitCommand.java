package ares.command;

import ares.exception.AresException;
import ares.task.Task;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasklist cannot be null!";
        assert ui != null : "Ui cannot be null!";
        assert storage != null : "Storage cannot be null!";
        ui.printBye();
        return response();
    }

    /**
     * Returns the response after execution.
     */
    public String response() {
        return "Nice chatting with you today\n"
                + "See you again next time.";
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