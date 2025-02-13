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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}