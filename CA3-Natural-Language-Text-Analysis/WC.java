import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * A text analysis program that counts words, characters and lines,
 * and can compute lexical diversity, bag of words vectors and Euclidean distance.
 */
public class WC {

    /**
     * Main entry point. Takes a flag and one or two strings from the command line.
     * Flags: -w (words), -m (characters), -l (lines), -s (lexical diversity),
     *        -b (bag of words), -v (joint bag of words), -d (Euclidean distance).
     *
     * @param args command line arguments: flag followed by one or two input strings
     */
    public static void main(String[] args) {
        if (args[0].equals("-w")) {
            System.out.println(wordCount(args[1]));
        }

        if (args[0].equals("-m")) {
            System.out.println(charCount(args[1]));
        }

        if (args[0].equals("-l")) {
            System.out.println(lineCount(args[1]));
        }

        if (args[0].equals("-s")) {
            System.out.println(lexicalDiversity(args[1]));
        }

        if (args[0].equals("-b")) {
            System.out.println(bagOfWords(args[1]));
        }

        if (args[0].equals("-v")) {
            List<List<Integer>> vectors = jointBagOfWords(args[1], args[2]);
            System.out.println(vectors.get(0));
            System.out.println(vectors.get(1));
        }

        if (args[0].equals("-d")) {
            System.out.println(euclideanDistance(args[1], args[2]));
        }
    }

    /**
     * Counts the number of words in the input string.
     * Words are separated by whitespace.
     *
     * @param input the string to count words in
     * @return the number of words
     */
    private static int wordCount(String input) {
        String[] words = input.split("\\s+");
        return words.length;
    }

    /**
     * Returns the number of characters in the input string.
     *
     * @param input the string to measure
     * @return the character count
     */
    private static int charCount(String input) {
        return input.length();
    }

    /**
     * Counts the number of lines in the input string.
     * A string with no newlines is still considered to have one line.
     *
     * @param input the string to count lines in
     * @return the number of lines
     */
    private static int lineCount(String input) {
        return input.split("\n").length;
    }

    /**
     * Computes the lexical diversity of the input string.
     * This is the number of distinct words divided by the total number of words.
     * The calculation is case-sensitive.
     *
     * @param input the string to analyse
     * @return the lexical diversity as a double
     */
    private static double lexicalDiversity(String input) {
        String[] words = input.split("\\s+");
        HashSet<String> unique = new HashSet<>();
        for (String word : words) {
            unique.add(word);
        }
        return (double) unique.size() / words.length;
    }

    /**
     * Builds a bag of words vector for the input string.
     * Words are converted to lowercase and counted, then the counts are
     * returned in lexicographical order of the words.
     *
     * @param input the string to analyse
     * @return a list of word counts in lexicographical order
     */
    private static List<Integer> bagOfWords(String input) {
        String[] words = input.toLowerCase().split("\\s+");
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        List<String> sorted = new ArrayList<>(counts.keySet());
        Collections.sort(sorted);
        List<Integer> result = new ArrayList<>();
        for (String word : sorted) {
            result.add(counts.get(word));
        }
        return result;
    }

    /**
     * Builds joint bag of words vectors for two input strings.
     * The vocabulary is the combined set of unique words from both strings,
     * sorted lexicographically. Each vector contains the word count for
     * every word in that shared vocabulary.
     *
     * @param input1 the first string
     * @param input2 the second string
     * @return a list containing two vectors (each a list of integers)
     */
    private static List<List<Integer>> jointBagOfWords(String input1, String input2) {
        String[] words1 = input1.toLowerCase().split("\\s+");
        String[] words2 = input2.toLowerCase().split("\\s+");

        Map<String, Integer> counts1 = new HashMap<>();
        for (String word : words1) {
            counts1.put(word, counts1.getOrDefault(word, 0) + 1);
        }

        Map<String, Integer> counts2 = new HashMap<>();
        for (String word : words2) {
            counts2.put(word, counts2.getOrDefault(word, 0) + 1);
        }

        // combine unique words from both strings into one sorted vocabulary
        HashSet<String> allWords = new HashSet<>();
        allWords.addAll(counts1.keySet());
        allWords.addAll(counts2.keySet());
        List<String> vocab = new ArrayList<>(allWords);
        Collections.sort(vocab);

        List<Integer> vec1 = new ArrayList<>();
        List<Integer> vec2 = new ArrayList<>();
        for (String word : vocab) {
            vec1.add(counts1.getOrDefault(word, 0));
            vec2.add(counts2.getOrDefault(word, 0));
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(vec1);
        result.add(vec2);
        return result;
    }

    /**
     * Computes the Euclidean distance between the bag of words vectors
     * of two input strings using their joint vocabulary.
     *
     * @param input1 the first string
     * @param input2 the second string
     * @return the Euclidean distance as a double
     */
    private static double euclideanDistance(String input1, String input2) {
        List<List<Integer>> vectors = jointBagOfWords(input1, input2);
        List<Integer> vec1 = vectors.get(0);
        List<Integer> vec2 = vectors.get(1);

        double sum = 0;
        for (int i = 0; i < vec1.size(); i++) {
            double diff = vec1.get(i) - vec2.get(i);
            sum += diff * diff;
        }
        return Math.sqrt(sum);
    }
}
