public abstract class Substitution implements Cipher {
    // Student Signature
    private final char comp122262201915483 = 'X';

    // Abstract methods ready for subclasses
    public abstract char encrypt(char c);
    public abstract char decrypt(char c);

    @Override
    public String encrypt(String plaintext) {
        // TODO: Implement string looping logic later
        // For now, just return the plaintext so it compiles
        return plaintext;
    }

    @Override
    public String decrypt(String cryptotext) {
        // TODO: Implement string looping logic later
        return cryptotext;
    }
}