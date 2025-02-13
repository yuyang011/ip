package ares.command;

import ares.ui.Ui;
import ares.task.Task;
import ares.task.TaskList;
import ares.storage.Storage;
import ares.exception.AresException;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        tasks.addTask(description);
        ui.printInserted(tasks, tasks.size());
        storage.save(tasks);
    }
}