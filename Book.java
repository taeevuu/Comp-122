public class Book {
    private String title;
    private String author;
    private String content;
    private int edition;

    public Book(String t, String a, String c, int e) {
        title = t;
        author = a;
        content = c;
        edition = e;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public int getEdition() {
        return edition;
    }

    // 666 characters fit on one page, round up if there's a partial page
    public int getPages() {
        return (int) Math.ceil(content.length() / 666.0);
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n"
             + "Author: " + author + "\n"
             + "Edition: " + edition + "\n";
    }
}
