package ares.command;

import ares.exception.AresException;
import ares.storage.Storage;

import ares.task.TaskList;
import ares.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        TaskList sameTasks = tasks.findTasks(keyword);
        ui.printFoundList(sameTasks);
    }
}