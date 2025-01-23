import java.util.Scanner;

public class Ares {
    public static void main(String[] args) {
        String line = "--------------------------------------------------------------";
        String indent = "   ";
        System.out.println(indent + line);
        System.out.println(indent + "Hello! I'm Ares");
        System.out.println(indent + "Its been awhile\n" + indent + "What can I do for you today?");
        System.out.println(indent + line);

        Task[] tasks = new Task[100];
        int tasksSize = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                System.out.println(indent + line);
                if (input.equals("bye")) {
                    System.out.println(indent + "Nice chatting with you today\n" + indent + "See you again next time.");
                    System.out.println(indent + line);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(indent + "Here are the tasks in your list:");
                    for (int i = 0; i < tasksSize; i++){
                        System.out.println(indent + (i + 1) + "." + tasks[i]);
                    }
                    System.out.println(indent + line);
                } else if (input.startsWith("mark")) {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNum < 0 || taskNum > 100) {
                        throw new AresException("You have entered an invalid number");
                    }
                    if (tasks[taskNum].status()) {
                        throw new AresException("You have already completed this task");
                    }
                    tasks[taskNum].markAsDone();
                    System.out.println(indent + "NICE, I have marked this task as completed:");
                    System.out.println(indent + "  " + tasks[taskNum].toString());
                    System.out.println(indent + line);
                } else if (input.startsWith("unmark")) {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNum < 0 || taskNum > 100) {
                        throw new AresException("You have entered an invalid number");
                    }
                    if (!tasks[taskNum].status()) {
                        throw new AresException("You have not completed this task in the first place");
                    }
                    tasks[taskNum].markAsNotDone();
                    System.out.println(indent + "Alright, I have marked this task as not completed yet:");
                    System.out.println(indent + "  " + tasks[taskNum].toString());
                    System.out.println(indent + line);
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    if (input.startsWith("todo")) {
                        if (input.trim().equals("todo")) {
                            throw new AresException("STOP RIGHT THERE!!! You forgotten to write a description");
                        }
                        tasks[tasksSize] = new Todo(input.substring(5));
                    }
                    if (input.startsWith("deadline")) {
                        if (!input.trim().contains("/by")) {
                            throw new AresException("STOP RIGHT THERE!!! You forgotten to write a deadline");
                        }
                        String[] parts = input.split(" /by ");
                        if (parts.length < 2 || parts[1].isEmpty()) {
                            throw new AresException("STOP RIGHT THERE!!! You forgotten to write a deadline timing");
                        }
                        tasks[tasksSize] = new Deadline(parts[0].substring(9), parts[1]);
                    }
                    if (input.startsWith("event")) {
                        if ((!input.trim().contains("/from")) || (!input.trim().contains("/to"))) {
                            throw new AresException("STOP RIGHT THERE!!! You forgotten to specify a duration");
                        }
                        String[] parts = input.split(" /from ");
                        if (parts.length < 2) {
                            throw new AresException("STOP RIGHT THERE!!! You entered an incomplete event timing");
                        }
                        String[] subParts = parts[1].split(" /to ");
                        if (subParts.length < 2) {
                            throw new AresException("STOP RIGHT THERE!!! You entered an incomplete event timing");
                        }
                        tasks[tasksSize] = new Event(parts[0].substring(6), subParts[0], subParts[1]);
                    }
                    tasksSize++;
                    System.out.println(indent + "Got it. I've added this task:");
                    System.out.println(indent + "  " + tasks[tasksSize - 1]);
                    System.out.println(indent + "Now you have " + tasksSize + " tasks in the list.");
                    System.out.println(indent + line);
                } else {
                    throw new AresException("??? I do not understand what you entered");
                }
            } catch (AresException e) {
                System.out.println(indent + "WAIT A MINUTE!! " + e.getMessage());
                System.out.println(indent + line);
            } catch (Exception e) {
                System.out.println(indent + "STOP RIGHT THERE!! An unexpected error occurred: " + e.getMessage());
                System.out.println(indent + line);
            }
        }
    }
}