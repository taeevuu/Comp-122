public import java.util.HashMap;

public class MonoAlphaSubstitution extends Substitution {
    private final char comp122262201915483 = 'X';

    // Setting up the variables but not fully using them yet
    private HashMap<Character, Character> encryptMap;
    private HashMap<Character, Character> decryptMap;

    public MonoAlphaSubstitution() {
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();
    }

    public MonoAlphaSubstitution(String mapping) {
        encryptMap = new HashMap<>();
        decryptMap = new HashMap<>();
        // TODO: Figure out how to pair up letters from the mapping string
    }

    @Override
    public char encrypt(char c) {
        // Draft: Just return the character unchanged for now
        return c; 
    }

    @Override
    public char decrypt(char c) {
        return c;
    }

    public static void main(String[] args) {
        // Draft: Basic main method so it compiles
        System.out.println("MonoAlphaSubstitution ready to be built.");
    }
} {
    
}
