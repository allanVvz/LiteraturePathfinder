import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Waitlist {
    private Queue<String> waitlist;
    private static final String FILENAME = "waitlist.txt";

    public Waitlist() {
        this.waitlist = new LinkedList<>();
        loadWaitlist();
    }

    public void addToWaitlist(String userName, Book book) {
        String entry = userName + " - " + book;
        waitlist.add(entry);
        saveWaitlist();
    }

    public void printWaitlist() {
        for (String entry : waitlist) {
            System.out.println(entry);
        }
    }

    private void saveWaitlist() {
        try (FileWriter writer = new FileWriter(FILENAME)) {
            for (String entry : waitlist) {
                writer.write(entry + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWaitlist() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                waitlist.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
