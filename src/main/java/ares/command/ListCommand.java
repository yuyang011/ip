package ares.command;

import ares.ui.Ui;

import ares.task.TaskList;

import ares.storage.Storage;

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
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasklist cannot be null!";
        assert ui != null : "Ui cannot be null!";
        assert storage != null : "Storage cannot be null!";
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