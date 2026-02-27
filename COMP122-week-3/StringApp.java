import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StringApp {

  /**
   * Reads the contents of a file and returns it as a String.
   *
   * @param path the path to the file
   * @return the contents of the file as a String, or "file not found!" if an IOException occurs
   */
  public static String readFileAsString(String path) {
    try {
      return Files.readString(Path.of(path));
    } catch (IOException e) {
      return "file not found!";
    }
  }

  // Part 1: Powers!
  public static String pow(String s, int n) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < n; i++) {
      result.append(s);
    }
    return result.toString();
  }

  // Part 2: Factors? (Default case-sensitive version)
  public static int factorCount(String a, String f) {
    // We can reuse the overloaded method below by defaulting caseSensitive to true
    return factorCount(a, f, true);
  }

  // Part 2: Case-insensitive factors?
  public static int factorCount(String a, String f, boolean caseSensitive) {
    if (a == null || f == null || f.isEmpty()) {
      return 0;
    }

    // Convert both strings to lowercase if we are ignoring case sensitivity
    if (!caseSensitive) {
      a = a.toLowerCase();
      f = f.toLowerCase();
    }

    int count = 0;
    int index = a.indexOf(f);

    // Keep searching and chopping off the processed part of the string
    while (index != -1) {
      count++;
      a = a.substring(index + f.length());
      index = a.indexOf(f);
    }

    return count;
  }

  // Part 3: Letter counts
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please provide an input string as a command line argument.");
      return;
    }

    String input = args[0];
    input = input.toLowerCase(); // Convert to lowercase to count 'A' and 'a' together

    // Array to hold the counts of the 26 letters (indexes 0 to 25)
    int[] letterCounts = new int[26];

    // Iterate through the string exactly once (O(n) efficiency)
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      int diff = c - 'a'; // Character arithmetic to find the index

      // Check if it's a valid English letter
      if (diff >= 0 && diff <= 25) {
        letterCounts[diff]++;
      }
    }

    int currentIndex = 0;
    // Question: why does this work?
    // Answer: In Java, characters are represented by underlying integer values (ASCII/Unicode). 
    // You can increment a char value, moving from 'a' to 'b' to 'c' and so on.
    for (char letter = 'a'; letter <= 'z'; letter++) { 
        
      // Question: Can you explain what + means here and why?
      // Answer: The '+' symbol represents string concatenation. When at least one of the 
      // operands is a String, Java automatically converts the other primitives (like chars and ints) 
      // into Strings and glues them together into a new String.
      System.out.println(letter + ": " + letterCounts[currentIndex]);
      currentIndex++;
    }
  }
}