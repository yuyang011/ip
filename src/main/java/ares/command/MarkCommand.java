package ares.command;

import ares.task.Task;
import ares.ui.Ui;

import ares.task.TaskList;

import ares.storage.Storage;

import ares.exception.AresException;
import ares.exception.OutOfBoundException;

/**
 * Represents a command to mark the tasks in the TaskList as Done with a X.
 */
public class MarkCommand extends Command {
    private int taskNum;

    /**
     * Constructs a new MarkCommand with the specified task index.
     *
     * @param taskNum The index of the task to be deleted.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the mark command by marking the tasks in the TaskList as Done with a X.
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
        if (tasks.getTask(taskNum).status()) {
            throw new AresException("You have already completed this task");
        }
        tasks.getTask(taskNum).markAsDone();
        ui.printMark(tasks, taskNum);
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
        return "NICE, I have marked this task as completed:\n"
                + tasks.getTask(taskNum).toString() + "\n";
    }
}