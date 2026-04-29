import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a printing press that prints and distributes books.
 */
public class Press {
    private Map<String, Integer> edition;
    private Map<String, List<Book>> shelf;
    private int shelfSize;
    private String bookDir;

    /**
     * Creates a new Press that reads book files from the given directory.
     * Each file in the directory is registered as a book the press can produce.
     * The shelf starts empty and the edition count starts at zero for each book.
     *
     * @param pathToBookDir path to the directory containing book text files
     * @param shelfSize the maximum number of copies to keep on the shelf per book
     */
    public Press(String pathToBookDir, int shelfSize) {
        this.shelfSize = shelfSize;
        this.bookDir = pathToBookDir;
        this.edition = new HashMap<>();
        this.shelf = new HashMap<>();

        try {
            File dir = new File(pathToBookDir);
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    String bookID = file.getName();
                    edition.put(bookID, 0);
                    shelf.put(bookID, new ArrayList<>());
                }
            }
        } catch (Exception e) {
            // if the directory can't be read, leave the maps empty
        }
    }

    /**
     * Prints a single copy of the book identified by bookID with the given edition number.
     * Reads the book's title, author and content from the corresponding text file.
     *
     * @param bookID the file name identifying the book
     * @param edition the edition number to assign to the printed copy
     * @return a new Book object with the parsed details
     * @throws IllegalArgumentException if the bookID does not match any known book
     * @throws IOException if the file cannot be read or is missing required fields
     */
    protected Book print(String bookID, int edition) throws IOException {
        if (!shelf.containsKey(bookID)) {
            throw new IllegalArgumentException("Unknown book: " + bookID);
        }

        File file = new File(bookDir + File.separator + bookID);
        String text = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

        // find the title
        int titlePos = text.indexOf("Title: ");
        if (titlePos == -1) {
            throw new IOException("No title found in " + bookID);
        }
        int titleEnd = text.indexOf("\n", titlePos);
        String title = text.substring(titlePos + 7, titleEnd).trim();

        // find the author
        int authorPos = text.indexOf("Author: ");
        if (authorPos == -1) {
            throw new IOException("No author found in " + bookID);
        }
        int authorEnd = text.indexOf("\n", authorPos);
        String author = text.substring(authorPos + 8, authorEnd).trim();

        // everything after the *** START OF line is the book content
        int startPos = text.indexOf("*** START OF");
        if (startPos == -1) {
            throw new IOException("No start marker found in " + bookID);
        }
        int contentStart = text.indexOf("\n", startPos) + 1;
        String content = text.substring(contentStart);

        return new Book(title, author, content, edition);
    }

    /**
     * Returns a list of book ID strings for all books this press can produce.
     *
     * @return a list of valid book IDs
     */
    public List<String> getCatalogue() {
        return new ArrayList<>(shelf.keySet());
    }

    /**
     * Returns the requested number of copies of the given book.
     * Books are taken from the shelf first. If more are needed, a new batch
     * is printed and the shelf is restocked to its maximum size.
     *
     * @param bookID the ID of the book to request
     * @param amount the number of copies to return
     * @return a list of books of the requested length, or an empty list if printing fails
     * @throws IllegalArgumentException if the bookID is not in the catalogue
     */
    public List<Book> request(String bookID, int amount) {
        if (!shelf.containsKey(bookID)) {
            throw new IllegalArgumentException("Unknown book: " + bookID);
        }

        List<Book> result = new ArrayList<>();
        List<Book> bookShelf = shelf.get(bookID);

        // take books from the shelf first
        while (!bookShelf.isEmpty() && result.size() < amount) {
            result.add(bookShelf.remove(0));
        }

        // if we still don't have enough, print a new batch
        if (result.size() < amount) {
            int needed = amount - result.size();
            edition.put(bookID, edition.get(bookID) + 1);
            int newEdition = edition.get(bookID);

            // print enough to fulfill the request and fully restock the shelf
            int toPrint = needed + shelfSize;
            List<Book> printed = new ArrayList<>();
            for (int i = 0; i < toPrint; i++) {
                try {
                    printed.add(print(bookID, newEdition));
                } catch (IOException e) {
                    return new ArrayList<>();
                }
            }

            // the first `needed` books go to the requester
            for (int i = 0; i < needed; i++) {
                result.add(printed.get(i));
            }

            // the rest go back onto the shelf
            for (int i = needed; i < printed.size(); i++) {
                bookShelf.add(printed.get(i));
            }
        }

        return result;
    }
}
