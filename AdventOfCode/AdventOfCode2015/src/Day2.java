import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Day2 {
    public static void main(String[] args) {
        List<String> dimensionStr = new ArrayList<>();

        try {
            dimensionStr = Files.readAllLines(Path.of(
                    "/Users/jeromewaldron/Documents/GitHub/MiscProgramsSandbox/AdventOfCode/AdventOfCode2015/InputFiles/AdventOfCode2015Day2InputFile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int wrappingPaperNumber = 0;
        int totalFeetOfRibbon = 0;

        for (String dimension : dimensionStr) {
            String[] dimensionsStr = dimension.split("x");

            List<Integer> dimensions = new ArrayList<>();

            for (int i = 0; i < dimensionsStr.length; i++) {
                dimensions.add(Integer.parseInt(dimensionsStr[i]));
            }

            List<Integer> sideAreas = new ArrayList<>();

            for (int i = 0; i < dimensions.size() - 1; i++) {
                sideAreas.add(dimensions.get(i) * dimensions.get(i + 1));
            }
            sideAreas.add(dimensions.get(0) * dimensions.get(dimensions.size() - 1));

            int thisPresentSurfaceArea = 2 * sideAreas.stream().mapToInt(Integer::intValue).sum();
            int paperNeededForThisPresent = thisPresentSurfaceArea
                    + sideAreas.stream().mapToInt(Integer::intValue).min().getAsInt();
            wrappingPaperNumber += paperNeededForThisPresent;

            var sortedDimensions = dimensions.stream().sorted().toList();

            int feetOfRibbon = 2 * sortedDimensions.get(0) + 2 * sortedDimensions.get(1)
                    + sortedDimensions.stream().reduce(1, (a, b) -> a * b);

            totalFeetOfRibbon += feetOfRibbon;
        }

        System.out.println("Total wrapping paper needed (sq ft): %s".formatted(wrappingPaperNumber));
        System.out.println("Total feet of ribbon needed: %s".formatted(totalFeetOfRibbon));
    }
}
