package ares.command;

import ares.exception.AresException;
import ares.storage.Storage;
import ares.task.Task;
import ares.task.TaskList;
import ares.ui.Ui;

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
     * @return A string that describes what the execution has done.
     * @throws AresException If an error occurs while saving.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
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
