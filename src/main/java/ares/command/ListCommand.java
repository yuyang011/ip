package ares.command;

import ares.storage.Storage;
import ares.task.TaskList;
import ares.ui.Ui;

/**
 * Represents a command to list the tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the TaskList.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving and loading tasks.
     * @return A string that describes what the execution has done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
        return response(tasks);
    }

    /**
     * Returns the response after execution.
     *
     * @param tasks   The list of tasks.
     */
    public String response(TaskList tasks) {
        StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1)
                    .append(".")
                    .append(tasks.getTask(i))
                    .append("\n");
        }
        return list.toString();
    }
}
