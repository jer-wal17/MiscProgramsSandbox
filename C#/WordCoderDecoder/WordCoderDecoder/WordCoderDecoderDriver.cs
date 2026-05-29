namespace WordCoderDecoder.WordCoderDecoder
{
    public class WordCoderDecoderDriver
    {
        public Dictionary<char, string> LetterBank { get; set; } = [];

        public static Dictionary<char, int> LettersToNumbers { get; } = new() {
                {'A', 1}, {'B', 2}, {'C', 3}, {'D', 4}, {'E', 5},
                {'F', 6}, {'G', 7}, {'H', 8}, {'I', 9}, {'J', 10},
                {'K', 11}, {'L', 12}, {'M', 13}, {'N', 14}, {'O', 15},
                {'P', 16}, {'Q', 17}, {'R', 18}, {'S', 19}, {'T', 20},
                {'U', 21}, {'V', 22}, {'W', 23}, {'X', 24}, {'Y', 25}, {'Z', 26},
            };

        public bool TryAddLetterAssociations(string givenWord, string givenCode)
        {
            if (givenWord.Length == givenCode.Length)
            {
                List<char> wordLetters = [.. givenWord.ToCharArray()];
                List<string> codeLetters = TurnWordIntoStrings(givenCode);

                for (int i = 0; i < wordLetters.Count; i++)
                {
                    LetterBank.TryAdd(wordLetters[i], codeLetters[i]);
                }
                return true;
            }
            else
            {
                return false;
            }
        }

        public string DecodeWord(string wordToDecode)
        {
            List<string> decodedWordLetters = [];
            foreach (char character in wordToDecode.ToCharArray())
            {
                if (LetterBank.TryGetValue(character, out string? stringChar))
                {
                    if (!string.IsNullOrEmpty(stringChar))
                    {
                        decodedWordLetters.Add(stringChar);
                    }
                }
                else
                {
                    decodedWordLetters.Add("?");
                }
            }

            return string.Join("", decodedWordLetters);
        }

        public static List<string> TurnWordIntoStrings(string word)
        {
            return [.. word.ToCharArray().Select(character => character.ToString())];
        }
    }
}
