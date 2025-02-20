package ares.command;

import java.time.LocalDate;

import ares.exception.AresException;
import ares.storage.Storage;
import ares.task.TaskList;
import ares.ui.Ui;

/**
 * Represents a command to view tasks that occurs on a certain date in the program.
 */
public class ViewCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs a new ViewCommand with the specified date.
     *
     * @param date The date the task to find occurs on.
     */
    public ViewCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the view command by displaying task that occurs on a certain date in the TaskList.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving and loading tasks.
     * @return A string that describes what the execution has done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AresException {
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
            return "No tasks found for the day.";
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
