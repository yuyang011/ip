public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}