/**
 * A bunch of utility functions for Strings.
 *
 * @author Patrick Totzke
 * @version 1.0
 */
public class StringTools {

  /**
   * Computes the length of a string.
   *
   * <p>This is done by first turning it into an Array of characters, then iteratively incrementing
   * an integer variable for every character.
   *
   * <p>This is of course are really silly solution because String.length or Array.size can be used
   * instead. In fact, the latter is implicitly used in the termination criterion of the for loop.
   *
   * @param str the string to consider
   * @return the length of the given string.
   */
  public static int length(String str) {
    char[] len = str.toCharArray();
    int a = 0;
    for (char ch : len) {
      a++;
    }
    return a;
  }

  /**
   * Reverses the characters in a given string.
   * * <p>This method iterates through the input string backwards, appending each character 
   * to a new string to reverse its order.
   *
   * @param s the string to be reversed
   * @return a new string with the characters of the input string in reverse order
   */
  public static String swap(String s) {
    String rev = "";

    for (int j = s.length(); j > 0; --j) {
      rev = rev + (s.charAt(j - 1));
    }
    return rev;
  }

  /**
   * The main entry point for the application.
   * * <p>Prompts the user to enter a string via standard input, and then prints out
   * the string's length and its reversed ("swapped") version using the utility methods.
   *
   * @param arg the command line arguments (not used in this program)
   */
  public static void main(String[] arg) {
    System.out.print("Enter a string: ");
    java.util.Scanner scanner = new java.util.Scanner(System.in);
    String str = scanner.nextLine();
    System.out.println("It's length is " + length(str));
    System.out.println("It's swap is " + swap(str));
  }
}