import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rebel {
    private static String input = "It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil Galactic Empire. During the battle, Rebel spies managed to steal secret plans to the Empire's ultimate weapon, the DEATH STAR, an armored space station with enough power to destroy an entire planet.  Pursued by the Empire's sinister agents, Princess Leia races home aboard her starship, custodian of the stolen plans that can save her people and restore freedom to the galaxy…";

    /**
     * Runs the selected regex matching task based on the command-line argument.
     *
     * @param args command-line arguments; args[0] selects the part to run (0, 1, 2, or 3)
     */
    public static void main(String[] args) {
        Matcher matcher = null;

        // Part 1
        if (args[0].equals("0")) {
            // Define our pattern and matches
            matcher = matchRebel(input);
        }

        // Part 2
        if (args[0].equals("1")) {
            // Define our pattern and matches
            matcher = matchEmpire(input);
        }

        // Part 3
        if (args[0].equals("2")) {
            // Define our pattern and matches
            matcher = matchLeia(input);

            // Loop through our matches
        }

        // Part 4,5
        if (args[0].equals("3")) {
            // Define our pattern and matches
            matcher = matchUpper(input);
        }

        while (matcher.find()) {
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println(input.substring(matcher.start(), matcher.end()));
        }
    }

    /**
     * Matches all occurrences of "Rebel" in the given input string.
     *
     * @param input the string to search
     * @return a Matcher for the "Rebel" pattern against the input
     */
    public static Matcher matchRebel(String input) {
        Pattern pattern = Pattern.compile("Rebel");
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }

    /**
     * Matches all occurrences of "Rebel" or "Empire" in the given input string.
     *
     * @param input the string to search
     * @return a Matcher for the "Rebel|Empire" pattern against the input
     */
    public static Matcher matchEmpire(String input) {
        Pattern pattern = Pattern.compile("Rebel|Empire");
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }

    /**
     * Matches all occurrences of "Rebel", "Empire", or "Princess Leia"
     * in the given input string.
     *
     * @param input the string to search
     * @return a Matcher for the combined phrase pattern against the input
     */
    public static Matcher matchLeia(String input) {
        Pattern pattern = Pattern.compile("Rebel|Empire|Princess Leia");
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }

    /**
     * Matches all occurrences of "Rebel", "Empire", "Princess", "Leia",
     * and any fully capitalised words in the given input string.
     *
     * @param input the string to search
     * @return a Matcher for the combined capitalised-word pattern against the input
     */
    public static Matcher matchUpper(String input) {
        Pattern pattern = Pattern.compile("Rebel|Empire|Princess|Leia|\\b[A-Z]+\\b");
        Matcher matcher = pattern.matcher(input);

        return matcher;
    }
}
