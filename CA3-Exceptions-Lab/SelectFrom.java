/**
 * Stores a list of integers parsed from String arguments
 * and allows access to them by index.
 */
public class SelectFrom {

    // made private so outside code can't mess with the array directly
    private int[] arrayOfNumbers;

    /**
     * Creates a SelectFrom object by parsing each String argument as an integer.
     *
     * @param args an array of Strings, each representing an integer
     */
    public SelectFrom(String[] args) {
        arrayOfNumbers = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            arrayOfNumbers[i] = Integer.parseInt(args[i]);
        }
    }

    /**
     * Returns the element at the given index, or -1 if the index is out of bounds.
     *
     * @param i the index to look up
     * @return the integer at that index, or -1 if out of bounds
     */
    public int selectFromArray(int i) {
        if (arrayOfNumbers.length <= i || i < 0) {
            return -1;
        }
        return arrayOfNumbers[i];
    }

    /**
     * Main method for quick testing.
     *
     * @param args command line arguments representing integers
     */
    public static void main(String[] args) {
        SelectFrom n = new SelectFrom(args);
        System.out.println(n.selectFromArray(args.length - 1));
    }
}
