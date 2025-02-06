package ares.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ares.task.Task;
import ares.task.TaskList;
import ares.task.Todo;
import ares.task.Event;
import ares.task.Deadline;
import ares.exception.AresException;

public class Storage {
    private final String filePath;
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws AresException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
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

    public void save(TaskList tasks) throws AresException {
        File file = new File("./data");
        if (!file.exists()) {
            file.mkdir();
        }
        try {
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