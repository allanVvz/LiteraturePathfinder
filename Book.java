class Book {
    private String title;
    private String author;
    private int release;

    public Book(String title, String author, int release) {
        this.title = title;
        this.author = author;
        this.release = release;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("%s, por %s (%d)", title, author, release);
    }

    public String getAuthor() {
        return author;
    }

    public int getRelease() {
        return release;
    }
}