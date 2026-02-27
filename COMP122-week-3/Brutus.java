public class Brutus {
    public static final double[] english = {
        0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    };

    // Returns a length-26 int array with counts of each letter (case-insensitive)
    public static int[] count(String input) {
        int[] counts = new int[26];
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                counts[ch - 'a']++;
            } else if (ch >= 'A' && ch <= 'Z') {
                counts[ch - 'A']++;
            }
        }
        return counts;
    }

    // Returns a length-26 double array with the frequency of each letter
    public static double[] frequency(String input) {
        int[] counts = count(input);
        double[] freq = new double[26];
        int total = 0;
        for (int c : counts) total += c;
        if (total == 0) return freq;
        for (int i = 0; i < 26; i++) {
            freq[i] = (double) counts[i] / total;
        }
        return freq;
    }

    // Returns the chi-squared score between two frequency arrays
    public static double chiSquared(double[] freq, double[] reference) {
        double score = 0.0;
        for (int i = 0; i < 26; i++) {
            double diff = freq[i] - reference[i];
            score += (diff * diff) / reference[i];
        }
        return score;
    }

    // Decodes a Caesar-shifted string by a given shift amount
    private static String decode(String cipher, int shift) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++) {
            char ch = cipher.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                sb.append((char) (((ch - 'a' - shift + 26) % 26) + 'a'));
            } else if (ch >= 'A' && ch <= 'Z') {
                sb.append((char) (((ch - 'A' - shift + 26) % 26) + 'A'));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Brutus \"cipher text\"");
            return;
        }
        if (args.length > 1) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Brutus \"cipher text\"");
            return;
        }

        String cipher = args[0];
        int bestShift = 0;
        double bestScore = Double.MAX_VALUE;

        for (int shift = 0; shift < 26; shift++) {
            String decoded = decode(cipher, shift);
            double[] freq = frequency(decoded);
            double score = chiSquared(freq, english);
            if (score < bestScore) {
                bestScore = score;
                bestShift = shift;
            }
        }

        System.out.println(decode(cipher, bestShift));
    }
}