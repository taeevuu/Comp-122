public abstract class Substitution implements Cipher {
    // Signature to verify student work
    private final char comp122262201915483 = 'X';

    // Abstract methods that subclasses will have to write
    public abstract char encrypt(char c);
    public abstract char decrypt(char c);

    @Override
    public String encrypt(String plaintext) {
        String result = "";
        // Loop through the string and encrypt one character at a time
        for (int i = 0; i < plaintext.length(); i++) {
            result += encrypt(plaintext.charAt(i));
        }
        return result;
    }

    @Override
    public String decrypt(String cryptotext) {
        String result = "";
        // Loop through the string and decrypt one character at a time
        for (int i = 0; i < cryptotext.length(); i++) {
            result += decrypt(cryptotext.charAt(i));
        }
        return result;
    }
}