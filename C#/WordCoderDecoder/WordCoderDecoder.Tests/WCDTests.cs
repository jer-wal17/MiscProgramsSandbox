using WordCoderDecoder.WordCoderDecoder;

namespace WordCoderDecoder.Tests
{
    public class WCDTests
    {
        [Theory]
        [InlineData("abc", "zyx")]
        [InlineData("abc", "123")]
        public void TestMultipleStringsAllCorrect(string givenWord, string givenCode)
        {
            // Arrange
            WordCoderDecoderDriver wordCoderDecoder = new();

            // Act
            bool result = wordCoderDecoder.TryAddLetterAssociations(givenWord, givenCode);

            // Assert
            Assert.True(result);
        }

        [Theory]
        [InlineData("abc", "")]
        [InlineData("abc", "z")]
        [InlineData("abc", "zy")]
        [InlineData("abc", "zyxw")]
        [InlineData("", "zyx")]
        [InlineData("a", "zyx")]
        [InlineData("ab", "zyx")]
        [InlineData("abcd", "zyx")]
        public void TestLengthMismatches(string givenWord, string givenCode)
        {
            // Arrange
            WordCoderDecoderDriver wordCoderDecoder = new();

            // Act
            bool result = wordCoderDecoder.TryAddLetterAssociations(givenWord, givenCode);

            // Assert
            Assert.False(result);
        }

        [Theory]
        [MemberData(nameof(CorrectDecodeData))]
        public void TestWordDecoding(string chars, string strings, string wordToDecode, string expected)
        {
            // Arrange
            var charList = chars.ToCharArray();
            var stringList = WordCoderDecoderDriver.TurnWordIntoStrings(strings);

            Dictionary<char, string> testDictionary = charList.Zip(stringList, (character, stringLetter) => new {character, stringLetter})
                                                              .ToDictionary(pair => pair.character, pair => pair.stringLetter);

            WordCoderDecoderDriver wordCoderDecoderDriver = new()
            {
                LetterBank = testDictionary
            };

            // Act
            string result = wordCoderDecoderDriver.DecodeWord(wordToDecode);

            // Assert
            Assert.Equal(expected, result);
        }

        public static TheoryData<string, string, string, string> CorrectDecodeData
        {
            get
            {
                var data = new TheoryData<string, string, string, string>
                {
                    { "AB", "12", "AB", "12" }
                };

                return data;
            }
        }
    }
}
