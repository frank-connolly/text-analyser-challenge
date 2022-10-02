import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextAnalyserServiceTest {

    final String sampleInput = "Platon made bamboo boats. ";

    @Test
    public void testConstructorWordParsing_givenValidText_expectCorrectParsedWords() {
        // Assemble
        String[] actualParsedWords;
        String[] expectedParsedWords = {"platon", "made", "bamboo", "boats"};

        // Act
        actualParsedWords = new TextAnalyserService(sampleInput).words;

        // Assert
        assertEquals(Arrays.toString(expectedParsedWords), Arrays.toString(actualParsedWords));
    }

    @Test
    public void testGetResultString_givenValidText_expectCorrectStringOutput() {
        // Assemble
        String actualOutput;
        String expectedOutput = new StringBuilder()
                .append("4=[a, e] - 2.0").append(System.lineSeparator())
                .append("5=[a, o] - 2.0").append(System.lineSeparator())
                .append("6=[a, o] - 2.5").append(System.lineSeparator())
                .toString();

        // Act
        actualOutput = new TextAnalyserService(sampleInput).getStringOfVowelsWithAverageCountPerWordLength();

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCalcAverageVowelCountPerWordLength_givenValidWords_expectCorrectAverage() {
        // Assemble
        Map<Integer, Double> actualVowelCountAveragePerWordLengthMap;
        Map<Integer, Double> expectedVowelCountAveragePerWordLengthMap = new HashMap<>(); {
            expectedVowelCountAveragePerWordLengthMap.put(4, 2.0);
            expectedVowelCountAveragePerWordLengthMap.put(5, 2.0);
            expectedVowelCountAveragePerWordLengthMap.put(6, 2.5);
        }

        // Act
        actualVowelCountAveragePerWordLengthMap = new TextAnalyserService(sampleInput)
                                                        .calculateAverageVowelCountPerWordLength();
        // Assert
        assertEquals(expectedVowelCountAveragePerWordLengthMap, actualVowelCountAveragePerWordLengthMap);
    }

    @Test
    public void testCalcVowelsPerWordLength_givenValidString_expectCorrectMap() {
        // Assemble
        Map<Integer, Set<Character>> actualVowelsPerWordLengthMap;
        Map<Integer, Set<Character>> expectedVowelsPerWordLengthMap = new HashMap<>(); {
            expectedVowelsPerWordLengthMap.put(6, new HashSet<>(List.of('a', 'o')));
            expectedVowelsPerWordLengthMap.put(5, new HashSet<>(List.of('a', 'o')));
            expectedVowelsPerWordLengthMap.put(4, new HashSet<>(List.of('a', 'e')));
        }

        // Act
        actualVowelsPerWordLengthMap = new TextAnalyserService(sampleInput).calculateVowelsPerWordLength();

        // Assert
        assertEquals(expectedVowelsPerWordLengthMap, actualVowelsPerWordLengthMap);
    }
}
