namespace WordCoderDecoder.WordCoderDecoder
{
    public class WordCoderDecoderDriver
    {
        public Dictionary<char, string> LetterBank { get; set; } = [];

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
