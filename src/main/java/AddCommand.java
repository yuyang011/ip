class AddCommand extends Command {
    private final Task description;

    public AddCommand(Task description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        tasks.addTask(description);
        ui.printInserted(tasks, tasks.size());
        storage.save(tasks);
    }
}