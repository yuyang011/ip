import java.util.Scanner;

public class Ares {
    public static void main(String[] args) {
        String line = "-------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Ares");
        System.out.println("Its been awhile\nWhat can I do for you today?\n");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
            if (input.equals("bye")) {
                System.out.println("Nice chatting with you today\nSee you again next time.\n");
                System.out.println(line);
                break;
            }
        }
    }
}