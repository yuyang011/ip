package ares.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a specific task.
     *
     * @param tasks The description of the task to be initialized with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index The task index that is to be removed.
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the TaskList.
     *
     * @param index The task in the position of the specified index.
     * @return The task in the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Calculates the size of the TaskList
     *
     * @return The number of tasks in the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves all tasks in the TaskList.
     *
     * @return A list of all tasks in the TaskList.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public TaskList findTasks(String keyword) {
        TaskList found = new TaskList();
        for(Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                found.addTask(task);
            }
        }
        return found;
    }
}