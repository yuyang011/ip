package ares.command;

import ares.ui.Ui;

import ares.task.TaskList;

import ares.storage.Storage;

import ares.exception.AresException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AresException;

    public boolean isExit() {
        return false;
    }
}