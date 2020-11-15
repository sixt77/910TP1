import java.util.Scanner;

public class Main {

    static final long occurrences = 95675200;
    static Probabilities oc;

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Menu menu = new Menu();
        int choice = 0;
        while (choice != 7) {
            menu.displayMenu();
            System.out.println();
            choice = new Scanner(System.in).nextInt();
            menu.processChoice(choice);
        }
    }
}