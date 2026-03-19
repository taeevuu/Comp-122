public class Vigenere extends Substitution {
    private final char comp122262201915483 = 'X';
    
    // Setting up the array as suggested in the hints
    private Caesar[] ciphers;

    public Vigenere() {
        ciphers = new Caesar[1];
        ciphers[0] = new Caesar(); 
    }

    public Vigenere(String key) {
        // TODO: Write logic to turn string characters into Caesar shifts
        ciphers = new Caesar[1];
        ciphers[0] = new Caesar();
    }

    @Override
    public char encrypt(char c) {
        // TODO: Add logic to track position in the ciphers array
        return c;
    }

    @Override
    public char decrypt(char c) {
        return c;
    }

    public static void main(String[] args) {
        System.out.println("Vigenere class draft compiled.");
    }
}