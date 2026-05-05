import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vending machine that stores and sells books.
 */
public class VendingMachine {
    private List<Book> shelf;
    private double locationFactor;
    private int cassette;
    private int safe;
    private String password;

    /**
     * Creates a new VendingMachine with the given location factor and password.
     * The cassette and safe start at zero and the shelf starts empty.
     *
     * @param locationFactor a multiplier used to calculate book prices
     * @param password the password needed to restock or empty the safe
     */
    public VendingMachine(double locationFactor, String password) {
        this.locationFactor = locationFactor;
        this.password = password;
        cassette = 0;
        safe = 0;
        shelf = new ArrayList<>();
    }

    /**
     * Returns the current amount of money in the cassette.
     *
     * @return the cassette value in pence
     */
    public int getCassette() {
        return cassette;
    }

    /**
     * Adds a coin to the cassette.
     * Only valid UK denominations are accepted: 1, 2, 5, 10, 20, 50, 100, 200.
     *
     * @param coin the coin value in pence
     * @throws IllegalArgumentException if the coin is not a valid denomination
     */
    public void insertCoin(int coin) {
        if (coin != 1 && coin != 2 && coin != 5 && coin != 10
                && coin != 20 && coin != 50 && coin != 100 && coin != 200) {
            throw new IllegalArgumentException("Not a valid coin: " + coin);
        }
        cassette += coin;
    }

    /**
     * Cancels the current transaction by resetting the cassette to zero.
     *
     * @return the amount that was in the cassette before cancelling
     */
    public int cancel() {
        int total = cassette;
        cassette = 0;
        return total;
    }

    /**
     * Adds books to the shelf if the correct password is given.
     *
     * @param books the list of books to add to the shelf
     * @param password the password to authorise the restock
     * @throws InvalidPasswordException if the password is incorrect
     */
    public void restock(List<Book> books, String password) {
        if (!this.password.equals(password)) {
            throw new InvalidPasswordException("Incorrect password");
        }
        shelf.addAll(books);
    }

    /**
     * Empties the safe and returns its contents if the correct password is given.
     *
     * @param password the password to authorise the operation
     * @return the amount that was in the safe
     * @throws InvalidPasswordException if the password is incorrect
     */
    public int emptySafe(String password) {
        if (!this.password.equals(password)) {
            throw new InvalidPasswordException("Incorrect password");
        }
        int total = safe;
        safe = 0;
        return total;
    }

    /**
     * Returns a list of strings describing each book currently on the shelf.
     * Each string is the toString() output of the corresponding book.
     *
     * @return a list of book descriptions
     */
    public List<String> getCatalogue() {
        List<String> catalogue = new ArrayList<>();
        for (Book book : shelf) {
            catalogue.add(book.toString());
        }
        return catalogue;
    }

    /**
     * Returns the price of the book at the given shelf index.
     * Price is pages multiplied by the location factor, rounded up to the nearest pence.
     *
     * @param index the position of the book on the shelf
     * @return the price in pence
     * @throws IndexOutOfBoundsException if there is no book at the given index
     */
    public int getPrice(int index) {
        if (index < 0 || index >= shelf.size()) {
            throw new IndexOutOfBoundsException("No book at index " + index);
        }
        return (int) Math.ceil(shelf.get(index).getPages() * locationFactor);
    }

    /**
     * Sells the book at the given shelf index if enough money has been inserted.
     * The book is removed from the shelf, the price is deducted from the cassette,
     * and the price is added to the safe.
     *
     * @param index the position of the book on the shelf
     * @return the purchased book
     * @throws IndexOutOfBoundsException if there is no book at the given index
     * @throws CassetteException if the cassette does not contain enough money
     */
    public Book buyBook(int index) {
        if (index < 0 || index >= shelf.size()) {
            throw new IndexOutOfBoundsException("No book at index " + index);
        }
        int price = getPrice(index);
        if (cassette < price) {
            throw new CassetteException("Not enough money inserted");
        }
        Book book = shelf.remove(index);
        cassette -= price;
        safe += price;
        return book;
    }
}
