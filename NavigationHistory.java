import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.io.FileWriter;
import java.lang.Integer;

public class NavigationHistory {
    private Stack<Book> stack;
    private static final String FILE_PATH = "visualized_books.txt";


    public NavigationHistory() {
        this.stack = new Stack<>();
        loadVisualizedBooks();
    }

    public void push(Book book) {
        stack.push(book);
        addVisualizedBook(book);
    }

    public Book pop() {
        return stack.pop();
    }

    public void printHistory() {
        for (Book book : stack) {
            System.out.println(book);
        }
    }

    
    public static void addVisualizedBook(Book book) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(String.format("%s, %s, %d%n", book.getTitle(), book.getAuthor(), book.getRelease()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadVisualizedBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader("visualized_books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    String title = parts[0];
                    String author = parts[1];
                    int release = Integer.parseInt(parts[2]);
                    Book book = new Book(title, author, release);
                    stack.push(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
