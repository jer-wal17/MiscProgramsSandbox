import java.util.Scanner;

public class TuringComp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string to check for \u03C9c\u03C9: ");
        String input = in.nextLine();
        String[] inputs = input.split("c");
        if (inputs[0].equals(inputs[1])) {
            System.out.println("String matches form");
        } else {
            System.out.println("String does not match form");
        }

        in.close();
    }
}
