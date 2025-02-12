package ares.command;

import ares.ui.Ui;

import ares.task.Task;
import ares.task.TaskList;

import ares.storage.Storage;

import ares.exception.AresException;
import ares.exception.OutOfBoundException;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        if (taskNum >= tasks.size()) {
            throw new OutOfBoundException("You have entered an invalid task number");
        }
        Task deletedTask = tasks.deleteTask(taskNum);
        ui.printDeleted(tasks, deletedTask);
        storage.save(tasks);
    }
}