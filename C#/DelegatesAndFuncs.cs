// With implicit "using"s

namespace delegates
{
    public delegate int NumberDelegate(int x);
    internal class Program
    {
        static void Main(string[] args)
        {
            var original = new List<int> { 1, 2, 3, 4, 5 };

            var addition = ChangeArrayElements(original, AddX);
            Console.WriteLine("Addition:");
            foreach (var a in addition)
                Console.Write($"{a.ToString()}, ");
            Console.WriteLine("\n");

            var subtraction = ChangeArrayElements(original, SubtractX);
            Console.WriteLine("Subtraction:");
            foreach (var s in subtraction)
                Console.Write(s.ToString() + ", ");
            Console.WriteLine("\n");

            var funcAddition = FuncChangeArrayElements(original, FuncAddX);
            Console.WriteLine("Func Addition:");
            foreach (var a in funcAddition)
                Console.Write($"{a.ToString()}, ");
            Console.WriteLine("\n");

            var funcSubtraction = FuncChangeArrayElements(original, FuncSubtractX);
            Console.WriteLine("Func Subtraction:");
            foreach (var s in funcSubtraction)
                Console.Write(s.ToString() + ", ");
            Console.WriteLine();
        }

        static List<int> ChangeArrayElements(List<int> numbers, NumberDelegate operation)
        {
            List<int> results = new List<int>();
            foreach (var n in numbers)
                results.Add(operation(n));
            return results;
        }

        static List<int> FuncChangeArrayElements(List<int> numbers, Func<int, int> operation)
        {
            List<int> results = new();
            foreach (int n in numbers)
                results.Add(operation(n));
            return results;
        }

        static Func<int, int> FuncAddX = (num) => num + 1;

        static Func<int, int> FuncSubtractX = (num) => num - 1;

        static int AddX(int num)
        {
            return num + 1;
        }

        static int SubtractX(int num)
        {
            return num - 1;
        }
    }
}