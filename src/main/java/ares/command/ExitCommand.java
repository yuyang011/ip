package ares.command;

import ares.storage.Storage;
import ares.task.TaskList;
import ares.ui.Ui;

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
     * @return A string that describes what the execution has done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
