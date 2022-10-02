import org.jetbrains.annotations.NotNull;

import java.util.*;

class TextAnalyserService {

    // TODO Util class for file input / output

    final String VOWELS = "aeiou";
    String[] words;

    public TextAnalyserService(@NotNull String textInput) {
        if (textInput == null || textInput.isEmpty()) {
            throw new IllegalArgumentException("Unable to analyse text - input is null or empty.");
        }
        this.words = textInput.trim().toLowerCase().split("\\W+");
    }

    public String getStringOfVowelsWithAverageCountPerWordLength() {
        Map<Integer, Set<Character>> vowelsPerWordLength = calculateVowelsPerWordLength();
        Map<Integer, Double> averageVowelCountPerWordLength = calculateAverageVowelCountPerWordLength();
        StringBuilder resultString = new StringBuilder();

        for (Map.Entry<Integer, Set<Character>> set : vowelsPerWordLength.entrySet()) {
            double average = averageVowelCountPerWordLength.get(set.getKey());
            resultString.append(set);
            resultString.append(" - ");
            resultString.append(average);
            resultString.append(System.lineSeparator());
        }
        return resultString.toString();
    }

    public Map<Integer, Set<Character>> calculateVowelsPerWordLength() {
        Map<Integer, Set<Character>> vowelsPerLengthMap = new HashMap<>();
        for (String s : this.words) {
            Set<Character> vowelsInWord = new HashSet<>();
            char[] letters = s.toCharArray();
            for (char letter : letters) {
                if (VOWELS.indexOf(letter) != -1) {
                    vowelsInWord.add(letter);
                }
            }
            vowelsPerLengthMap.put(s.length(), vowelsInWord);
        }
        return vowelsPerLengthMap;
    }

    public Map<Integer, Double> calculateAverageVowelCountPerWordLength() {
        Map <Integer, Double> averageVowelCountPerWordLength = new HashMap<>();
        Map<Integer, Integer> vowelTotalCountByLength = new HashMap<>();
        Map<Integer, Integer> wordCountByLength = new HashMap<>();
        for (String s : words) {
            getVowelCount(s, vowelTotalCountByLength);
            getWordCountByLength(s, wordCountByLength);
        }
        // Average Vowel Per Word Length Calc
        for (Map.Entry<Integer,Integer> set : vowelTotalCountByLength.entrySet()) {
            int wordLength = set.getKey();
            int vowelCount = set.getValue();
            int wordCount = wordCountByLength.get(wordLength);
            double vowelCountAverage = (double)vowelCount / wordCount;
            averageVowelCountPerWordLength.put(wordLength, vowelCountAverage);
        }
        return averageVowelCountPerWordLength;
    }

    private void getWordCountByLength(String s, Map<Integer, Integer> wordCountByLength) {
        if (!wordCountByLength.containsKey(s.length())) {
            wordCountByLength.put(s.length(), 1);
        } else {
            int currentWordCount = wordCountByLength.get(s.length());
            wordCountByLength.put(s.length(), currentWordCount+1);
        }
    }

    private void getVowelCount(String s, Map<Integer, Integer> vowelCounts) {
        int numOfVowelsInWord = 0;
        for (char letter : s.toCharArray()) {
            if (VOWELS.indexOf(letter) != -1) {
                numOfVowelsInWord++;
            }
        }
        if (!vowelCounts.containsKey(s.length())) {
            vowelCounts.put(s.length(), numOfVowelsInWord);
        } else {
            int currentVowelCount = vowelCounts.get(s.length());
            vowelCounts.put(s.length(), currentVowelCount + numOfVowelsInWord);
        }
    }
}
