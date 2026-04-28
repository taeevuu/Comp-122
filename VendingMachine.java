import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private List<Book> shelf;
    private double locationFactor;
    private int cassette;
    private int safe;
    private String password;

    public VendingMachine(double locationFactor, String password) {
        this.locationFactor = locationFactor;
        this.password = password;
        cassette = 0;
        safe = 0;
        shelf = new ArrayList<>();
    }

    public int getCassette() {
        return cassette;
    }

    // only valid UK coin denominations (in pence) are accepted
    public void insertCoin(int coin) {
        if (coin != 1 && coin != 2 && coin != 5 && coin != 10
                && coin != 20 && coin != 50 && coin != 100 && coin != 200) {
            throw new IllegalArgumentException("Not a valid coin: " + coin);
        }
        cassette += coin;
    }

    public int cancel() {
        int total = cassette;
        cassette = 0;
        return total;
    }

    public void restock(List<Book> books, String password) {
        if (!this.password.equals(password)) {
            throw new InvalidPasswordException("Incorrect password");
        }
        shelf.addAll(books);
    }

    public int emptySafe(String password) {
        if (!this.password.equals(password)) {
            throw new InvalidPasswordException("Incorrect password");
        }
        int total = safe;
        safe = 0;
        return total;
    }

    public List<String> getCatalogue() {
        List<String> catalogue = new ArrayList<>();
        for (Book book : shelf) {
            catalogue.add(book.toString());
        }
        return catalogue;
    }

    public int getPrice(int index) {
        if (index < 0 || index >= shelf.size()) {
            throw new IndexOutOfBoundsException("No book at index " + index);
        }
        // price in pence = pages * locationFactor, rounded up
        return (int) Math.ceil(shelf.get(index).getPages() * locationFactor);
    }

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
