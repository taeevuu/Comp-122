public class Vigenere extends Substitution {
    // Signature to verify student work
    private final char comp122262201915483 = 'X';
    
    private Caesar[] ciphers;
    private int encryptPos = 0;
    private int decryptPos = 0;

    public Vigenere() {
        ciphers = new Caesar[1];
        ciphers[0] = new Caesar(); // identity
    }

    public Vigenere(String key) {
        if (key == null || key.equals("")) {
            ciphers = new Caesar[1];
            ciphers[0] = new Caesar(); 
        } else {
            ciphers = new Caesar[key.length()];
            for (int i = 0; i < key.length(); i++) {
                // A is 0, B is 1, C is 2 etc.
                int shiftAmount = key.charAt(i) - 'A';
                
                // Because our Caesar class automatically adds 12225 to everything, 
                // we have to subtract it here so they cancel each other out and give us the exact shift we want!
                ciphers[i] = new Caesar(shiftAmount);
            }
        }
    }

    @Override
    public char encrypt(char c) {
        char result = ciphers[encryptPos].encrypt(c);
        // Move to the next cipher in the array, wrapping around to 0 if we hit the end
        encryptPos = (encryptPos + 1) % ciphers.length;
        return result;
    }

    @Override
    public char decrypt(char c) {
        char result = ciphers[decryptPos].decrypt(c);
        // Move to the next cipher in the array, wrapping around to 0 if we hit the end
        decryptPos = (decryptPos + 1) % ciphers.length;
        return result;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
            return;
        }
        if (args.length > 3) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
            return;
        }

        String mode = args[0];
        String key = args[1];
        String text = args[2];

        if (!mode.equals("encrypt") && !mode.equals("decrypt")) {
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
            System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
            return;
        }

        Vigenere cipher = new Vigenere(key);
        if (mode.equals("encrypt")) {
            System.out.println(cipher.encrypt(text));
        } else {
            System.out.println(cipher.decrypt(text));
        }
    }
}