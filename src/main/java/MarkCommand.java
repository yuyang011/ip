public class MarkCommand extends Command {
    private int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        if (taskNum >= tasks.size()) {
            throw new OutOfBoundException("You have entered an invalid task number");
        }
        if (tasks.getTask(taskNum).status()) {
            throw new AresException("You have already completed this task");
        }
        tasks.getTask(taskNum).markAsDone();
        ui.printMark(tasks, taskNum);
        storage.save(tasks);
    }
}