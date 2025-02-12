package ares.command;

import ares.ui.Ui;

import ares.task.TaskList;

import ares.storage.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}