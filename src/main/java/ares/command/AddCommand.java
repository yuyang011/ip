package ares.command;

import ares.exception.AresException;
import ares.ui.Ui;
import ares.task.Task;
import ares.task.TaskList;
import ares.storage.Storage;

/**
 * Represents a command to add a task from the TaskList.
 */
public class AddCommand extends Command {
    private final Task description;

    /**
     * Constructs a new AddCommand with the specified task.
     *
     * @param description The description of the task to add.
     */
    public AddCommand(Task description) {
        this.description = description;
    }

    /**
     * Executes the add command by adding the task to the task list and saving it.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws AresException If an error occurs while saving.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        assert tasks != null : "Tasklist cannot be null!";
        assert ui != null : "Ui cannot be null!";
        assert storage != null : "Storage cannot be null!";
        tasks.addTask(description);
        ui.printInserted(tasks, tasks.size());
        storage.save(tasks);
        return response(description, tasks.size());
    }

    /**
     * Returns the response after execution.
     *
     * @param task   The list of tasks.
     * @param size   The user interface for displaying messages.
     */
    public String response(Task task, int size) {
        return "Got it. I've added this task: \n"
                + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list";
    }
}