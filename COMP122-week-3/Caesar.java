public class Caesar {

    // Rotates a single character
    public static char rotate(int shift, char ch) {

        // Handle lowercase letters
        if (ch >= 'a' && ch <= 'z') {
            int offset = ch - 'a';
            int newOffset = (offset + shift) % 26;

            if (newOffset < 0) {
                newOffset += 26;  // handle negative shifts
            }

            return (char) ('a' + newOffset);
        }

        // Handle uppercase letters
        if (ch >= 'A' && ch <= 'Z') {
            int offset = ch - 'A';
            int newOffset = (offset + shift) % 26;

            if (newOffset < 0) {
                newOffset += 26;  // handle negative shifts
            }

            return (char) ('A' + newOffset);
        }

        // Non-letters remain unchanged
        return ch;
    }

    // Rotates an entire string
    public static String rotate(int shift, String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            result += rotate(shift, text.charAt(i));
        }

        return result;
    }

    // Main method
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar n \"cipher text\"");
            return;
        }

        if (args.length > 2) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar n \"cipher text\"");
            return;
        }

        int shift = Integer.parseInt(args[0]);
        String text = args[1];

        System.out.println(rotate(shift, text));
    }
}