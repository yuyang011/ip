import java.util.Scanner;

public class Ares {
    public static void main(String[] args) {
        String line = "-------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Ares");
        System.out.println("Its been awhile\nWhat can I do for you today?\n");
        System.out.println(line);

        Task[] tasks = new Task[100];
        int tasksSize = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);
            if (input.equals("bye")) {
                System.out.println("Nice chatting with you today\nSee you again next time.\n");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasksSize; i++){
                    System.out.println(i + 1 + "." + tasks[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNum].markAsDone();
                System.out.println("NICE, I have marked this task as completed:");
                System.out.println(" " + tasks[taskNum].toString());
                System.out.println(line);
            } else if (input.startsWith("unmark")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNum].markAsNotDone();
                System.out.println("Alright, I have marked this task as not completed yet:");
                System.out.println(" " + tasks[taskNum].toString());
                System.out.println(line);
            } else {
                if (input.startsWith("todo")) {
                    tasks[tasksSize] = new Todo(input.substring(5));
                }
                if (input.startsWith("deadline")) {
                    String[] parts = input.split(" /by ");
                    tasks[tasksSize]= new Deadline(parts[0].substring(9), parts[1]);
                }
                if (input.startsWith("event")) {
                    String[] parts = input.split(" /from ");
                    String[] subParts = parts[1].split(" /to ");
                    tasks[tasksSize]= new Event(parts[0].substring(6), subParts[0], subParts[1]);
                }
                tasksSize++;
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + tasks[tasksSize - 1]);
                System.out.println("Now you have " + tasksSize + " tasks in the list.");
                System.out.println(line);
            }
        }
    }
}