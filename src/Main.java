import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        final int YEARS = 10;
        final int NAMES_PER_YEAR = 1000;

        // Two-dimensional arrays to store names
        String[][] boyNames = new String[YEARS][NAMES_PER_YEAR];
        String[][] girlNames = new String[YEARS][NAMES_PER_YEAR];

        String absolutePathPath = "/Users/kenyi/IdeaProjects/BabyNamePop/baby_data";
        Path path = Paths.get(absolutePathPath);

        if (Files.isDirectory(path)) {
            System.out.println("Path is directory");
        } else {
            System.out.println("Path is not a directory");
        }
        for (int year = 0; year < YEARS; year++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(absolutePathPath + "/babynamesranking2001.txt"));
                String line;
                int index = 0;
                while ((line = reader.readLine()) != null) {
                   System.out.println(line);
                    String[] parts = line.split("\\s+");
                    for(int i=0; i<parts.length; i++){
                        System.out.println(parts[i] +" ");
                    }
                    System.out.println();
                    boyNames[year][index] = parts[1];
                    girlNames[year][index] = parts[3];
                    index++;

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int year, rank;
        String gender, name;
        try (BufferedReader userInput = new BufferedReader(new java.io.InputStreamReader(System.in))) {
            System.out.print("Enter the year: ");
            year = Integer.parseInt(userInput.readLine()) - 2001;

            System.out.print("Enter the gender (M/F, or leave blank if unsure): ");
            gender = userInput.readLine().toUpperCase();

            System.out.print("Enter the name: ");
            name = userInput.readLine();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        // Search for the name in the arrays
        if ((gender.isEmpty() || gender.equals("M")) && boyNames[year] != null) {
            rank = searchForName(boyNames[year], name);
            if (rank != -1) {
                System.out.println(name + " is ranked #" + (rank + 1) + " for boys in " + (2001 + year));
                return;
            }
        }

    }
    private static int searchForName(String[] names, String target) {
        if (names == null) {
            return -1; // Array is null, name not found
        }
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && names[i].equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1; // Name not found
    }
}