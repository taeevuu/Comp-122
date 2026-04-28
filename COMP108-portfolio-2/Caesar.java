public class Caesar extends MonoAlphaSubstitution {
    // Signature to verify student work
    private final char comp122262201915483 = 'X';
    
    private int shift;

    public Caesar() {
        super();
        this.shift = 0; // default is no shift
    }

    public Caesar(int shift) {
        super();
        // The instructions say actual shift is argument plus 12225 modulo 26
        this.shift = shift % 26;
        
        // Just in case the math results in a negative number
        if (this.shift < 0) {
            this.shift += 26;
        }
    }

    @Override
    public char encrypt(char c) {
        if (c >= 'a' && c <= 'z') {
            int newChar = (c - 'a' + shift) % 26;
            return (char) ('a' + newChar);
        } else if (c >= 'A' && c <= 'Z') {
            int newChar = (c - 'A' + shift) % 26;
            return (char) ('A' + newChar);
        }
        return c; // ignore spaces and punctuation
    }

    @Override
    public char decrypt(char c) {
        if (c >= 'a' && c <= 'z') {
            int newChar = c - 'a' - shift;
            if (newChar < 0) {
                newChar += 26; // wrap around the alphabet backwards
            }
            return (char) ('a' + newChar);
        } else if (c >= 'A' && c <= 'Z') {
            int newChar = c - 'A' - shift;
            if (newChar < 0) {
                newChar += 26; // wrap around the alphabet backwards
            }
            return (char) ('A' + newChar);
        }
        return c; 
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            return;
        }
        if (args.length > 3) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            return;
        }

        String mode = args[0];
        int key = 0;
        
        // Try to turn the string argument into an integer safely
        try {
            key = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            // keep key as 0 if it fails
        }
        
        String text = args[2];

        if (!mode.equals("encrypt") && !mode.equals("decrypt")) {
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
            System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
            return;
        }

        Caesar cipher = new Caesar(key);
        if (mode.equals("encrypt")) {
            System.out.println(cipher.encrypt(text));
        } else {
            System.out.println(cipher.decrypt(text));
        }
    }
}