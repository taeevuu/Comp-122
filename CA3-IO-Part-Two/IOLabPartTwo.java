import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class IOLabPartTwo {

    /**
     * Default constructor.
     */
    public IOLabPartTwo() {
        //Don't touch
    }

    /**
     * Constructor that runs CSV parsing and writing tests.
     * @param args command line arguments
     * @throws IOException if a file operation fails
     */
    public IOLabPartTwo(String[] args) throws IOException {
        //uncomment once parseCSVFile is working
        /*
        File file = new File("programs.csv");
        ArrayList<OurData> data = parseCSVFile(file.toPath());
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Program Description " + (i+1));
            data.get(i).printData();
            System.out.println();
        }
        */

        //uncomment once printCSVFile is working
        /*
        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(file.toPath());
        File newFile = new File("newprograms.csv");
        String[] headers = lines.get(0).split(",");
        printCSVFile(headers, data, newFile.toPath());
        */
    }

    /**
     * Main method.
     * @param args command line arguments
     * @throws IOException if a file operation fails
     */
    public static void main(String[] args) throws IOException {
        new IOLabPartTwo(args);
    }

    /**
     * Parses a single CSV line into an OurData object.
     * Each token is stored as an int, boolean, or String depending on its value.
     * @param scan a Scanner over a single CSV line with comma delimiter already set
     * @param headers the field names in order, matching the token positions
     * @return an OurData object with fields populated from the scanned tokens
     */
    public OurData parseCSVLine(Scanner scan, String[] headers) {

        OurData od = new OurData(headers);
        int index = 0;
        // Keep scanning until stream is empty i.e. scan.hasNext() returns false.
        // Check if each element is a boolean or int, and if so, scan this value directly.
        // Call od.setField(fieldName, value), where value is the value you scanned and
        // fieldName is headers[index], where index is incremented after each value scanned.
        // Note: You can use programs.csv as an example, but it
        // should work with any CSV file we provide. These files will be composed exclusively of
        // String, int, and boolean values.
        while (scan.hasNext()) {
            if (scan.hasNextInt()) {
                od.setField(headers[index], scan.nextInt());
            } else if (scan.hasNextBoolean()) {
                od.setField(headers[index], scan.nextBoolean());
            } else {
                od.setField(headers[index], scan.next());
            }
            index++;
        }
        return od;
    }

    /**
     * Parses a CSV file into a list of OurData objects.
     * The first line of the file is treated as the header row.
     * @param path the path to the CSV file
     * @return a list of OurData objects, one per data row
     * @throws IOException if the file cannot be read
     */
    public ArrayList<OurData> parseCSVFile(Path path) throws IOException {
        //Don't Touch
        ArrayList<OurData> ourDataObjects = new ArrayList<>();
        //Every line of the file is read in for you into lines
        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path);

        /*Populate ourDataObjects from any given csv file using Scanner scan to assist.
        Remember, the first line contains the headers, so we grab
        this and transform it into an array of Strings.
        */

        String[] headers = lines.get(0).split(",");

        for (int i = 1; i < lines.size(); i++) {
            try (Scanner scan = new Scanner(lines.get(i))) {
                scan.useDelimiter(",");
                ourDataObjects.add(parseCSVLine(scan, headers));
            }
        }

        return ourDataObjects;
    }

    /**
     * Writes a list of OurData objects to a CSV file at the given path.
     * The first line contains the headers and each subsequent line contains
     * the field values for one OurData object, in header order.
     * @param headers the field names to use as column headers
     * @param ourDataObjects the list of OurData objects to write
     * @param outPath the path of the output CSV file
     * @throws IOException if the file cannot be written
     */
    public void printCSVFile(String[] headers, ArrayList<OurData> ourDataObjects, Path outPath) throws IOException {
        /*
         * Given the headers, ourDataObjects, and outPath, print out a new csv file "outPath.csv".
         * String.join(",",aStringArray) takes an array of strings and then combines them into
         * one string delimited by commas. Consequently, this is very useful for this part.
         * You can use OurData.getFieldAsString(String header) to get a value associated to a header in
         * OurData.
         */
        //this will automatically close the resource when done. If you don't close, or flush,
        //the write operation may not actually write to file.
        try (BufferedWriter bw = Files.newBufferedWriter(outPath)) {

            //build and write your csv to file here
            bw.write(String.join(",", headers) + "\n");
            for (OurData od : ourDataObjects) {
                String[] values = new String[headers.length];
                for (int i = 0; i < headers.length; i++) {
                    values[i] = od.getFieldAsString(headers[i]);
                }
                bw.write(String.join(",", values) + "\n");
            }
        }

    }
}
