import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {

    public static void main(String[] args) throws Exception {
        try {
            String fileAsString = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\INPUT.txt")));
            System.out.println("InputString: " + fileAsString);
            System.out.println("Analysing...");
            String vowelAnalysisOutput = new TextAnalyserService(fileAsString).getStringOfVowelsWithAverageCountPerWordLength();
            System.out.println("OutputString: \n" + vowelAnalysisOutput);
            Path path = Paths.get("src\\main\\resources\\OUTPUT.txt");
            byte[] outputArray = vowelAnalysisOutput.getBytes();
            Files.write(path, outputArray);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
