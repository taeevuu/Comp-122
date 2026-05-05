import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.nio.file.Files;

public class Spam {

    /**
     * Runs the selected spam analysis task based on the command-line argument.
     *
     * @param args command-line arguments; args[0] selects the part to run (0, 1, or 2)
     * @throws IOException if the spam file cannot be read
     */
    public static void main(String[] args) throws IOException {
        String spam = getSpam();

        Matcher matcher = null;

        // Part 6
        if (args[0].equals("0")) {
            // Define our pattern and matches
            matcher = matchFrom(spam);
            while (matcher.find()) {
                System.out.println(spam.substring(matcher.start(), matcher.end()));
            }
        }

        // Part 7
        if (args[0].equals("1")) {
            // Define our pattern and matches
            matcher = matchEmails(spam);

            // Loop through our matches
            while (matcher.find()) {
                System.out.println(spam.substring(matcher.start(), matcher.end()));
            }
        }

        // Part 8
        if (args[0].equals("2")) {
            // Define our pattern and matches
            matcher = matchSenders(spam);

            // Loop through our matches, collecting unique sender addresses
            Set<String> senders = new TreeSet<>();
            while (matcher.find()) {
                senders.add(matcher.group(1));
            }
            for (String email : senders) {
                System.out.println(email);
            }
        }
    }

    /**
     * Reads and returns the entire contents of Spam.txt as a string.
     *
     * @return the full text of the spam file
     * @throws IOException if the file cannot be found or read
     */
    public static String getSpam() throws IOException {
        File file = new File("./Spam.txt");
        String fileContent = Files.readString(file.toPath());

        return fileContent;
    }

    /**
     * Matches all "From:..." header lines in the given input.
     *
     * @param input the text to search
     * @return a Matcher for the From-line pattern against the input
     */
    public static Matcher matchFrom(String input) {
        Pattern pattern = Pattern.compile("From:.*");

        // Instantiate our pattern matcher object
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }

    /**
     * Matches all email addresses found anywhere in the given input.
     *
     * @param input the text to search
     * @return a Matcher for the email address pattern against the input
     */
    public static Matcher matchEmails(String input) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");

        // Instantiate our pattern matcher object
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }

    /**
     * Matches "From:" header lines containing an email address in angle brackets,
     * capturing the email address in group 1.
     *
     * @param input the text to search
     * @return a Matcher for the sender pattern against the input
     */
    public static Matcher matchSenders(String input) {
        Pattern pattern = Pattern.compile("From:.*<([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})>");

        // Instantiate our pattern matcher object
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }
}
