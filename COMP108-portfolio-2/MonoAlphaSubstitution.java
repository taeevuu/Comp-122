import java.util.HashMap;

public class MonoAlphaSubstitution extends Substitution {
    // Signature to verify student work
    private final char comp122262201915483 = 'X';

    // Storing the mappings
    private HashMap<Character, Character> encryptMap;
    private HashMap<Character, Character> decryptMap;

    // Default constructor (identity mapping)
    public MonoAlphaSubstitution() {
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();
    }

    // Constructor that takes a mapping string
    public MonoAlphaSubstitution(String mapping) {
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();

        // Loop through the string jumping by 2 (pairs of letters)
        for (int i = 0; i < mapping.length(); i += 2) {
            char plain = mapping.charAt(i);
            char cipher = mapping.charAt(i + 1);
            
            encryptMap.put(plain, cipher);
            decryptMap.put(cipher, plain); // Reverse it for decryption
        }
    }

    @Override
    public char encrypt(char c) {
        // If the character is in our map, change it. Otherwise, leave it alone.
        if (encryptMap.containsKey(c)) {
            return encryptMap.get(c);
        }
        return c; 
    }

    @Override
    public char decrypt(char c) {
        if (decryptMap.containsKey(c)) {
            return decryptMap.get(c);
        }
        return c;
    }

    public static void main(String[] args) {
        // Check for correct number of arguments
        if (args.length < 3) {
            System.out.println("Too few parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        }
        if (args.length > 3) {
            System.out.println("Too many parameters!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        }

        String mode = args[0];
        String key = args[1];
        String text = args[2];

        if (!mode.equals("encrypt") && !mode.equals("decrypt")) {
            System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
            System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
            return;
        }

        MonoAlphaSubstitution cipher = new MonoAlphaSubstitution(key);
        if (mode.equals("encrypt")) {
            System.out.println(cipher.encrypt(text));
        } else {
            System.out.println(cipher.decrypt(text));
        }
    }
}