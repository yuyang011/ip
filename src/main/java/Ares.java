import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Ares {
    private static final String FILE_PATH = "./data/ares.txt";
    public static void main(String[] args) {
        String line = "--------------------------------------------------------------";
        String indent = "   ";
        System.out.println(indent + line);
        System.out.println(indent + "Hello! I'm Ares");
        System.out.println(indent + "Its been awhile\n" + indent + "What can I do for you today?");
        System.out.println(indent + line);

        ArrayList<Task> tasks = new ArrayList<>();
        int counter = 0;

        loadTasks(tasks);

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
                    for (int i = 0; i < tasks.size(); i++){
                        System.out.println(indent + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println(indent + line);
                } else if (input.startsWith("mark")) {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNum < 0) {
                        throw new AresException("You have entered an invalid number");
                    }
                    if (tasks.get(taskNum).status()) {
                        throw new AresException("You have already completed this task");
                    }
                    tasks.get(taskNum).markAsDone();
                    System.out.println(indent + "NICE, I have marked this task as completed:");
                    System.out.println(indent + "  " + tasks.get(taskNum).toString());
                    System.out.println(indent + line);
                    saveTasks(tasks);
                } else if (input.startsWith("unmark")) {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNum < 0) {
                        throw new AresException("You have entered an invalid number");
                    }
                    if (!tasks.get(taskNum).status()) {
                        throw new AresException("You have not completed this task in the first place");
                    }
                    tasks.get(taskNum).markAsNotDone();
                    System.out.println(indent + "Alright, I have marked this task as not completed yet:");
                    System.out.println(indent + "  " + tasks.get(taskNum).toString());
                    System.out.println(indent + line);
                    saveTasks(tasks);
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    if (input.startsWith("todo")) {
                        if (input.trim().equals("todo")) {
                            throw new AresException("STOP RIGHT THERE!!! You forgotten to write a description");
                        }
                        tasks.add(new Todo(input.substring(5)));
                    }
                    if (input.startsWith("deadline")) {
                        if (!input.trim().contains("/by")) {
                            throw new AresException("STOP RIGHT THERE!!! You forgotten to write a deadline");
                        }
                        String[] parts = input.split(" /by ");
                        if (parts.length < 2 || parts[1].isEmpty()) {
                            throw new AresException("STOP RIGHT THERE!!! You forgotten to write a deadline timing");
                        }
                        tasks.add(new Deadline(parts[0].substring(9), parts[1]));
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
                        tasks.add(new Event(parts[0].substring(6), subParts[0], subParts[1]));
                    }
                    counter++;
                    System.out.println(indent + "Got it. I've added this task:");
                    System.out.println(indent + "  " + tasks.get(counter - 1));
                    System.out.println(indent + "Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(indent + line);
                    saveTasks(tasks);
                } else if (input.startsWith("delete")) {
                    int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNum < 0) {
                        throw new AresException("You have entered an invalid number");
                    }
                    Task deletedTask = tasks.remove(taskNum);
                    System.out.println(indent + "Noted. I've removed this task:");
                    System.out.println(indent + "  " + deletedTask);
                    System.out.println(indent + "Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(indent + line);
                    saveTasks(tasks);
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
    private static void loadTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 2) {
                    break;
                }
                String type = parts[0];
                boolean status = parts[1].equals("1");
                String description = parts[2];
                if (type.equals("T")) {
                    Task newTask = new Todo(description);
                    if (parts[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    tasks.add(newTask);
                } else if (type.equals("D")) {
                    Task newTask = new Deadline(description, parts[3]);
                    if (parts[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    tasks.add(newTask);
                } else if (type.equals("E")) {
                    String timings[] = parts[3].split("-");
                    Task newTask = new Event(description, timings[0], timings[1]);
                    if (parts[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    tasks.add(newTask);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        File file = new File("./data");
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Task task : tasks) {
                writer.write(task.toFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
