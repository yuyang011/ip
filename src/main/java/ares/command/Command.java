package ares.command;

import ares.ui.Ui;

import ares.task.TaskList;

import ares.storage.Storage;

import ares.exception.AresException;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving and loading tasks.
     * @throws AresException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws AresException;

    /**
     * Determines if the command is exit command.
     *
     * @return Returns false if this command is an exit command, otherwise return true.
     */
    public boolean isExit() {
        return false;
    }
}