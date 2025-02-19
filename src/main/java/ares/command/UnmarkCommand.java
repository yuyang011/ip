package ares.command;

import ares.ui.Ui;

import ares.task.TaskList;

import ares.storage.Storage;

import ares.exception.AresException;
import ares.exception.OutOfBoundException;

/**
 * Represents a command to unmark the tasks in the TaskList as not Done by removing the X.
 */
public class UnmarkCommand extends Command {
    private int taskNum;

    /**
     * Constructs a new UnmarkCommand with the specified task index.
     *
     * @param taskNum The index of the task to be deleted.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the unmark command by unmarking the tasks in the TaskList as not Done by removing the X.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving and loading tasks.
     * @throws AresException If an error occurs while saving or the index provided is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        assert tasks != null : "Tasklist cannot be null!";
        assert ui != null : "Ui cannot be null!";
        assert storage != null : "Storage cannot be null!";
        assert taskNum >=0 : "Task index cannot be negative";
        if (taskNum >= tasks.size()) {
            throw new OutOfBoundException("You have entered an invalid task number");
        }
        if (!tasks.getTask(taskNum).status()) {
            throw new AresException("You have not completed this task in the first place");
        }
        tasks.getTask(taskNum).markAsNotDone();
        ui.printUnmark(tasks, taskNum);
        storage.save(tasks);
        return response(tasks, taskNum);
    }

    /**
     * Returns the response after execution.
     *
     * @param tasks   The list of tasks.
     * @param taskNum    The user interface for displaying messages.
     */
    public String response(TaskList tasks, int taskNum) {
        return "Alright, I have marked this task as not completed yet:\n"
                + getString(tasks, taskNum) + "\n";
    }

    /**
     * Returns string of the task.
     *
     * @param tasks The list of tasks.
     * @param taskNum The user interface for displaying messages.
     * @return The string of the task.
     */
    private static String getString(TaskList tasks, int taskNum) {
        return tasks.getTask(taskNum).toString();
    }
}