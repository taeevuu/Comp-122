public public class Caesar extends MonoAlphaSubstitution {
    private final char comp122262201915483 = 'X';
    
    private int shift;

    public Caesar() {
        super();
        this.shift = 0; 
    }

    public Caesar(int shift) {
        super();
        // TODO: Add the math to add 12225 and modulo 26
        this.shift = shift; 
    }

    @Override
    public char encrypt(char c) {
        // TODO: Add alphabet wrapping logic
        return c; 
    }

    @Override
    public char decrypt(char c) {
        // TODO: Add reverse alphabet wrapping logic
        return c; 
    }

    public static void main(String[] args) {
        System.out.println("Caesar class draft compiled.");
    }
} {
    
}
