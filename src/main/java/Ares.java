import java.util.Scanner;

public class Ares {
    public static void main(String[] args) {
        String line = "-------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Ares");
        System.out.println("Its been awhile\nWhat can I do for you today?\n");
        System.out.println(line);

        String[] tasks = new String[100];
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
                for (int i = 0; i < tasksSize; i++){
                    System.out.println(i + 1 + "." + " " + tasks[i]);
                }
                System.out.println(line);
            } else {
                tasks[tasksSize] = input;
                tasksSize++;
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
    }
}