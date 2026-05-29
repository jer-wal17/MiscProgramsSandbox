namespace WordCoderDecoder.WordCoderDecoder
{
    public class Program
    {
        public static void Main()
        {
            WordCoderDecoderDriver wordCoderDecoder = new();

            string? givenWord;
            do
            {
                // Get the word
                Console.Write("Enter a regular word (press Enter to finish entering codes): ");
                givenWord = Console.ReadLine();

                if (!string.IsNullOrEmpty(givenWord))
                {
                    // Get the word's code
                    Console.Write("Enter the code corresponding to the entered regular word: ");
                    string? givenCode = Console.ReadLine();

                    if (!string.IsNullOrEmpty(givenCode))
                    {
                        if (!wordCoderDecoder.TryAddLetterAssociations(givenWord, givenCode))
                        {
                            Console.WriteLine("The entered word and code were not the same length." + Environment.NewLine);
                        }
                    }
                }

            } while (!string.IsNullOrEmpty(givenWord));

            string? wordToDecode;

            do
            {
                // Get and decode word
                Console.Write("Enter a word to decode (press Enter to stop decoding): ");
                wordToDecode = Console.ReadLine();

                if (!string.IsNullOrEmpty(wordToDecode))
                {
                    string decodedWord = wordCoderDecoder.DecodeWord(wordToDecode);

                    Console.WriteLine($"Here is the decoded word: {string.Join("", decodedWord)}");
                }

            } while (!string.IsNullOrEmpty(wordToDecode));
        }
    }
}
