import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "--------------------------------------------------------------";
    private static final String INDENT = "   ";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void printWelcome() {
        printLine();
        System.out.println(INDENT + "Hello! I'm Ares");
        System.out.println(INDENT + "Its been awhile\n" + INDENT + "What can I do for you today?");
        printLine();
    }

    public void printLine() {
        System.out.println(INDENT + LINE);
    }

    public String readNextLine() {
        return scanner.nextLine();
    }

    public void printBye() {
        System.out.println(INDENT + "Nice chatting with you today\n" + INDENT + "See you again next time.");
        System.out.println(INDENT + LINE);
    }

    public void printList(TaskList tasks) {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(INDENT + LINE);
    }

    public void printMark(TaskList tasks, int taskNum) {
        System.out.println(INDENT + "NICE, I have marked this task as completed:");
        System.out.println(INDENT + "  " + tasks.getTask(taskNum).toString());
        System.out.println(INDENT + LINE);
    }

    public void printUnmark(TaskList tasks, int taskNum) {
        System.out.println(INDENT + "Alright, I have marked this task as not completed yet:");
        System.out.println(INDENT + "  " + tasks.getTask(taskNum).toString());
        System.out.println(INDENT + LINE);
    }

    public void printInserted(TaskList tasks, int counter) {
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + "  " + tasks.getTask(counter - 1));
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(INDENT + LINE);
    }

    public void printDeleted(TaskList tasks, Task deletedTask) {
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + deletedTask);
        System.out.println(INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(INDENT + LINE);
    }

    public void printAresException(String message) {
        System.out.println("STOP RIGHT THERE!!! " + message);
        System.out.println(INDENT + LINE);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}