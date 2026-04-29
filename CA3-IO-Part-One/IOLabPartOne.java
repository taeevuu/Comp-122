import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Lab class for I/O Part One. Parses structured text files into OurData objects.
 */
public class IOLabPartOne {

    /**
     * Default constructor used for testing by CodeGrade. Do not touch.
     */
    public IOLabPartOne() {
        //Don't touch, used for testing your code
    }

    /**
     * Constructor that runs a quick test by parsing programs.txt and printing the results.
     *
     * @param args command line arguments (not used)
     * @throws IOException if the file cannot be read
     */
    public IOLabPartOne(String[] args) throws IOException {
        //You can uncomment the code below to test if your function is correct
        //Just run the code and then compare the output in the terminal to programs.txt
        /*
        File file = new File("programs.txt");
        ArrayList<OurData> data = parseStructuredTextFile(file.toPath());
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Program Description " + (i+1));
            data.get(i).printData();
            System.out.println();
        }
        */
    }

    /**
     * Main entry point.
     *
     * @param args command line arguments
     * @throws IOException if the file cannot be read
     */
    public static void main(String[] args) throws IOException {
        new IOLabPartOne(args);
    }

    /**
     * Reads a structured text file and returns a list of OurData objects.
     * Each entry in the file starts with a "Program_Description N" header,
     * followed by 6 lines of "fieldName-value" data, then a blank line.
     *
     * @param path the path to the structured text file
     * @return an ArrayList of OurData objects parsed from the file
     * @throws IOException if the file cannot be read
     */
    public ArrayList<OurData> parseStructuredTextFile(Path path) throws IOException {
        ArrayList<OurData> ourDataObjects = new ArrayList<>();
        String[] fieldNames = {"problem", "program", "program_length", "passed_tests", "total_tests", "correct"};

        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);

        OurData current = null;
        for (String line : lines) {
            if (line.isBlank()) {
                // blank line marks the end of one entry
                if (current != null) {
                    ourDataObjects.add(current);
                    current = null;
                }
            } else if (line.startsWith("Program_Description")) {
                // header line — start a fresh OurData object
                current = new OurData(fieldNames);
            } else {
                // data line in format "fieldName-value"
                // use indexOf to split on first dash only, in case value contains dashes
                int dashIndex = line.indexOf("-");
                String fieldName = line.substring(0, dashIndex);
                String value = line.substring(dashIndex + 1);

                if (fieldName.equals("program_length") || fieldName.equals("passed_tests")
                        || fieldName.equals("total_tests")) {
                    current.setField(fieldName, Integer.parseInt(value));
                } else if (fieldName.equals("correct")) {
                    current.setField(fieldName, Boolean.parseBoolean(value));
                } else {
                    current.setField(fieldName, value);
                }
            }
        }

        // handle last entry if the file doesn't end with a blank line
        if (current != null) {
            ourDataObjects.add(current);
        }

        return ourDataObjects;
    }
}
