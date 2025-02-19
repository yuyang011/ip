package ares.command;

import ares.exception.AresException;
import ares.storage.Storage;

import ares.task.Task;
import ares.task.TaskList;
import ares.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewCommand extends Command {
    private final LocalDate date;

    public ViewCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the list command by displaying all tasks in the TaskList.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
        assert tasks != null : "Tasklist cannot be null!";
        assert ui != null : "Ui cannot be null!";
        assert storage != null : "Storage cannot be null!";
        TaskList sameDate = tasks.findDate(date);
        ui.printSchedule(sameDate, date);
        return response(sameDate);
    }

    /**
     * Returns the response after execution.
     *
     * @param tasks   The list of tasks that occurs on the given date.
     */
    public String response(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder scheduleForDate = new StringBuilder("Here are the scheduled tasks for " + date + ":\n");
        for (int i = 0; i < tasks.size(); i++) {
            scheduleForDate.append(i + 1)
                    .append(".")
                    .append(tasks.getTask(i))
                    .append("\n");
        }
        return scheduleForDate.toString();
    }
}
