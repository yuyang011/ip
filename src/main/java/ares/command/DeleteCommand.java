package ares.command;

import ares.ui.Ui;

import ares.task.Task;
import ares.task.TaskList;

import ares.storage.Storage;

import ares.exception.AresException;
import ares.exception.OutOfBoundException;

/**
 * Represents a command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Constructs a new DeleteCommand with the specified task index.
     *
     * @param taskNum The index of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the delete command by removing the task from the task list and saving it.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
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
        Task deletedTask = tasks.deleteTask(taskNum);
        ui.printDeleted(tasks, deletedTask);
        storage.save(tasks);
        return response(deletedTask, tasks.size());
    }

    /**
     * Returns the response after execution.
     *
     * @param task   The list of tasks.
     * @param size    The user interface for displaying messages.
     */
    public String response(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list";
    }
}