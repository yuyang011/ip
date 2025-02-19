package ares.command;

import ares.exception.AresException;
import ares.storage.Storage;

import ares.task.Task;
import ares.task.TaskList;
import ares.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        assert tasks != null : "Tasklist cannot be null!";
        assert ui != null : "Ui cannot be null!";
        assert storage != null : "Storage cannot be null!";
        TaskList sameTasks = tasks.findTasks(keyword);
        ui.printFoundList(sameTasks);
        return response(tasks);
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