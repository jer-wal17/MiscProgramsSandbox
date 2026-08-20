import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day1 {
    public static void main(String[] args) {
        int floorLevel = 0;

        String parentheses = "";

        try {
            parentheses = Files.readString(Path.of(
                    "/Users/jeromewaldron/Documents/GitHub/MiscProgramsSandbox/AdventOfCode/AdventOfCode2015/InputFiles/AdventOfCode2015Day1InputFile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int counter = 1;
        boolean firstBasementFound = false;

        for (char parenthese : parentheses.toCharArray()) {
            if (parenthese == '(') {
                floorLevel++;
            } else if (parenthese == ')') {
                floorLevel--;
            }
            if (!firstBasementFound && floorLevel < 0) {
                System.out.println("First basement char: %s".formatted(counter));
                firstBasementFound = true;
            }
            counter++;
        }

        System.out.println("Floor Level: %s".formatted(floorLevel));
    }
}
