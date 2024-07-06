import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class ReadingList {
    private ArrayList<Book> readingList;

    public ReadingList() {
        readingList = new ArrayList<>();
    }

    public void addBook(Book book) {
        readingList.add(book);
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Book book : readingList) {
                writer.write(book.toString() + "\n");
            }
            System.out.println("Lista de leitura salva em " + filename);
        } catch (IOException e) {
            System.out.println("Erro ao salvar a lista de leitura: " + e.getMessage());
        }
    }

    public void printReadingList() {
        System.out.println("Lista de Leitura:");
        for (Book book : readingList) {
            System.out.println(book);
        }
    }
}
