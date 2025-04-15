/*
 * Code to run the FizzBuzz test better, in Tom Scott's method
 */

public class BetterFizzBuzz
{
    public static void main(String[] args)
    {
        for (int i = 1; i <= 100; i++)
        {
            String output = "";
            if (i % 3 == 0)
            {
                output += "Fizz";
            }

            if (i % 5 == 0)
            {
                output += "Buzz";
            }

            // Additional options
            if (i % 7 == 0)
            {
                output += "Fuzz";
            }

            if (i % 11 == 0)
            {
                output += "Bizz";
            }

            if (i % 13 == 0)
            {
                output += "Biff";
            }

            if (i % 17 == 0)
            {
                output += "Fubb";
            }

            // If all tests fail, output the number
            if (output.equals(""))
            {
                output += i;
            }

            System.out.println(output);
        }
    }
}
