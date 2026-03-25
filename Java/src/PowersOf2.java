import java.util.Scanner;

public class PowersOf2 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a number to check for power of 2: ");
        int userNum = in.nextInt();
        in.nextLine();
        int currPowerOfTwo = 1;
        while (currPowerOfTwo < userNum) {
            currPowerOfTwo *= 2;
        }
        if (userNum == currPowerOfTwo) {
            System.out.println("Your number is a power of 2");
        } else {
            System.out.println("Your number is not a power of 2");
        }

        in.close();
    }
}
