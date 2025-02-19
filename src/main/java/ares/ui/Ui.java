package ares.ui;

import ares.task.TaskList;
import ares.task.Task;

import java.util.Scanner;

/**
 * Shows the interaction with the user by displaying messages and TaskList.
 */
public class Ui {
    private static final String LINE = "--------------------------------------------------------------";
    private static final String INDENT = "   ";
    private Scanner scanner;

    /**
     * Constructs a new UI to reads input from user.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message for the user.
     */
    public String printWelcome() {
        printLine();
        System.out.println(INDENT + "Hello! I'm Ares");
        System.out.println(INDENT + "Its been awhile");
        System.out.println(INDENT + "What can I do for you today?");
        printLine();
        return "Hello! I'm Ares\n" + "Its been awhile\n" + "What can I do for you today?\n";
    }

    /**
     * Displays a line for separating the messages that is displayed.
     */
    public void printLine() {
        System.out.println(INDENT + LINE);
    }

    /**
     * Reads the next input from the user.
     */
    public String readNextLine() {
        return scanner.nextLine();
    }

    /**
     * Displays a goodbye message for the user.
     */
    public void printBye() {
        System.out.println(INDENT + "Nice chatting with you today\n" + INDENT + "See you again next time.");
        printLine();
    }

    /**
     * Displays all tasks in the TaskList for the user.
     */
    public void printList(TaskList tasks) {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(INDENT + LINE);
    }

    /**
     * Displays a confirmation that the task is marked as done for the user.
     */
    public void printMark(TaskList tasks, int taskNum) {
        System.out.println(INDENT + "NICE, I have marked this task as completed:");
        System.out.println(INDENT + "  " + getString(tasks, taskNum));
        System.out.println(INDENT + LINE);
    }

    /**
     * Displays a confirmation that the task is unmarked as not done for the user.
     */
    public void printUnmark(TaskList tasks, int taskNum) {
        System.out.println(INDENT + "Alright, I have marked this task as not completed yet:");
        System.out.println(INDENT + "  " + getString(tasks, taskNum));
        System.out.println(INDENT + LINE);
    }

    /**
     * Displays a confirmation that the task is added into the TaskList for the user.
     */
    public void printInserted(TaskList tasks, int counter) {
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + "  " + tasks.getTask(counter - 1));
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(INDENT + LINE);
    }

    /**
     * Displays a confirmation that the task is deleted from the TaskList for the user.
     */
    public void printDeleted(TaskList tasks, Task deletedTask) {
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + deletedTask);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(INDENT + LINE);
    }

    /**
     * Displays the error occurred when executing the program for the user.
     */
    public String printAresException(String message) {
        System.out.println("STOP RIGHT THERE!!! " + message);
        System.out.println(INDENT + LINE);
        return "STOP RIGHT THERE!!! " + message;
    }

    /**
     * Displays the matching tasks from the TaskList.
     *
     * @param sameTasks The TaskList containing the tasks that are matched.
     */
    public void printFoundList(TaskList sameTasks) {
        if (sameTasks.isEmpty()) {
            System.out.println(INDENT + "No matching tasks found.");
            System.out.println(INDENT + LINE);
            return;
        }
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        for (int i = 0; i < sameTasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + sameTasks.getTask(i));
        }
        System.out.println(INDENT + LINE);
    }

    /**
     * Returns string of the task.
     *
     * @param tasks The list of tasks.
     * @param taskNum The user interface for displaying messages.
     * @return The string of the task.
     */
    private static String getString(TaskList tasks, int taskNum) {
        return tasks.getTask(taskNum).toString();
    }
}