package ares.command;

import ares.exception.AresException;
import ares.storage.Storage;
import ares.task.TaskList;
import ares.ui.Ui;

/**
 * Represents a command to Find a certain task in the program.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a new FindCommand with the specified task.
     *
     * @param keyword The description of the task to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the Find command by displaying all tasks that matches the given input in the TaskList.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving and loading tasks.
     * @return A string that describes what the execution has done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        TaskList sameTasks = tasks.findTasks(keyword);
        ui.printFoundList(sameTasks);
        return response(sameTasks);
    }

    /**
     * Returns the response after execution.
     *
     * @param tasks   The list of tasks.
     */
    public String response(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder matchingList = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            matchingList.append(i + 1)
                    .append(".")
                    .append(tasks.getTask(i))
                    .append("\n");
        }
        return matchingList.toString();
    }
}
