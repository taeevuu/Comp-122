/**
 * Represents a book with a title, author, content and edition number.
 */
public class Book {
    private String title;
    private String author;
    private String content;
    private int edition;

    /**
     * Creates a new Book with the given details.
     *
     * @param t the title of the book
     * @param a the author of the book
     * @param c the full text content of the book
     * @param e the edition number
     */
    public Book(String t, String a, String c, int e) {
        title = t;
        author = a;
        content = c;
        edition = e;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the full text content of the book.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the edition number of the book.
     *
     * @return the edition
     */
    public int getEdition() {
        return edition;
    }

    /**
     * Returns the number of pages in the book.
     * Each page holds 666 characters; partial pages are rounded up.
     *
     * @return the number of pages
     */
    public int getPages() {
        return (int) Math.ceil(content.length() / 666.0);
    }

    /**
     * Returns a formatted string with the title, author and edition,
     * each on its own line.
     *
     * @return a string representation of the book
     */
    @Override
    public String toString() {
        return "Title: " + title + "\n"
             + "Author: " + author + "\n"
             + "Edition: " + edition + "\n";
    }
}
