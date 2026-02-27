public class Credit {
  public static void main(String[] args) {
    // This reads some number from the terminal.
    // Don't worry about this for now and just use the variable
    System.out.print("Number:");
    java.util.Scanner scanner = new java.util.Scanner(System.in);
    long number = scanner.nextLong();

    // determine card type using Luhn's algorithm
    if (isValid(number)) {
      String type = cardType(number);
      System.out.println(type);
    } else {
      System.out.println("INVALID");
    }
  }

  // returns true if number passes Luhn's checksum
  private static boolean isValid(long number) {
    String s = Long.toString(number);
    int sum = 0;
    boolean alternate = false;
    for (int i = s.length() - 1; i >= 0; i--) {
      int digit = s.charAt(i) - '0';
      if (alternate) {
        digit *= 2;
        if (digit > 9) {
          digit -= 9;
        }
      }
      sum += digit;
      alternate = !alternate;
    }
    return sum % 10 == 0;
  }

  // determines the card issuer based on length and prefix
  private static String cardType(long number) {
    String s = Long.toString(number);
    int len = s.length();
    if (len == 15 && (s.startsWith("34") || s.startsWith("37"))) {
      return "AMEX";
    }
    if (len == 16) {
      String prefix2 = s.substring(0, 2);
      String prefix4 = s.substring(0, 4);
      int p2 = Integer.parseInt(prefix2);
      int p4 = Integer.parseInt(prefix4);
      // Mastercard ranges
      if ((p2 >= 51 && p2 <= 55) || (p4 >= 2221 && p4 <= 2720)) {
        return "MASTERCARD";
      }
    }
    if ((len == 13 || len == 16) && s.startsWith("4")) {
      return "VISA";
    }
    return "INVALID"; // fallback
  }
}
