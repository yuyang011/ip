package ares.command;

import ares.ui.Ui;
import ares.task.TaskList;
import ares.storage.Storage;
import ares.exception.AresException;
import ares.exception.OutOfBoundException;

public class UnmarkCommand extends Command {
    private int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        if (taskNum >= tasks.size()) {
            throw new OutOfBoundException("You have entered an invalid task number");
        }
        if (!tasks.getTask(taskNum).status()) {
            throw new AresException("You have not completed this task in the first place");
        }
        tasks.getTask(taskNum).markAsNotDone();
        ui.printUnmark(tasks, taskNum);
        storage.save(tasks);
    }
}