public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AresException;

    public boolean isExit() {
        return false;
    }
}