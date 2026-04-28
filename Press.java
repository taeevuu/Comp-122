import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Press {
    private Map<String, Integer> edition;
    private Map<String, List<Book>> shelf;
    private int shelfSize;
    private String bookDir; // path to the folder containing the book text files

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

    public List<String> getCatalogue() {
        return new ArrayList<>(shelf.keySet());
    }

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
