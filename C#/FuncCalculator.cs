namespace FuncCalc
{
    internal class Program
    {
        static void Main(string[] args)
        {
            List<int> array1 = [1, 2, 3];
            List<int> array2 = [2, 4, 6];

            Console.WriteLine("Addition: " + string.Join(", ", PerformOperation(array1, array2, Add)));
            Console.WriteLine("Subtraction: " + string.Join(", ", PerformOperation(array1, array2, Subtract)));
            Console.WriteLine("Multiplication: " + string.Join(", ", PerformOperation(array1, array2, Multiply)));
            Console.WriteLine("Division: " + string.Join(", ", PerformOperation(array1, array2, Divide)));
        }

        static List<int> PerformOperation(List<int> a, List<int> b, Func<int, int, int> operation)
        {
            List<int> results = [];

            for (int i = 0; i < a.Count; i++)
            {
                results.Add(operation(b[i], a[i]));
            }

            return results;
        }

        static Func<int, int, int> Add = (a, b) => a + b;

        static Func<int, int, int> Subtract = (a, b) => a - b;

        static Func<int, int, int> Multiply = (a, b) => a * b;

        static Func<int, int, int> Divide = (a, b) => a / b;
    }
}
