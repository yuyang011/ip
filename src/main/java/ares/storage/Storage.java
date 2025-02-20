package ares.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ares.exception.AresException;
import ares.task.Deadline;
import ares.task.Event;
import ares.task.Task;
import ares.task.TaskList;
import ares.task.Todo;

/**
 * Represents a class that handles saving and loading tasks to a file.
 */
public class Storage {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String filePath;

    /**
     * Constructs a new Storage with the specified filePath.
     *
     * @param filePath The path to the file where tasks are stored or loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file and returns them as a list.
     *
     * @return A list containing all tasks in the specified file.
     * @throws AresException If an error occurs when reading from the file with invalid format.
     */
    public ArrayList<Task> load() throws AresException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        //Solution below inspired by
        //https://www.geeksforgeeks.org/difference-between-bufferedreader-and-filereader-in-java/
        //Learnt how to read from a file using above website
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 2) {
                    throw new AresException("Invalid task format in file");
                }
                String type = parts[0];
                String description = parts[2];
                if (type.equals("T")) {
                    Task newTask = new Todo(description);
                    if (parts[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    tasks.add(newTask);
                } else if (type.equals("D")) {
                    LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMAT);
                    Task newTask = new Deadline(description, by);
                    if (parts[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    tasks.add(newTask);
                } else if (type.equals("E")) {
                    String[] timings = parts[3].split(" - ");
                    LocalDateTime from = LocalDateTime.parse(timings[0], DATE_TIME_FORMAT);
                    LocalDateTime to = LocalDateTime.parse(timings[1], DATE_TIME_FORMAT);
                    Task newTask = new Event(description, from, to);
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
        return tasks;
    }

    /**
     * Loads tasks from the specified file and returns them as a list.
     *
     * @param tasks The list of tasks to be saved into the specified file.
     * @throws AresException If an error occurs when writing to the file.
     */
    public void save(TaskList tasks) throws AresException {
        File file = new File("./data");
        if (!file.exists()) {
            boolean isCreated = file.mkdir();
            assert isCreated : "File not created!";
        }
        try {
            //Solution below inspired by https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
            //Learnt how to write to a file using above website
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
