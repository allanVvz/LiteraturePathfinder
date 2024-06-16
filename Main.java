import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileWriter;

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
}

class Grafo {
    private ArrayList<Book> books;
    private HashMap<Book, LinkedList<Book>> recommendations;

    public Grafo() {
        books = new ArrayList<>();
        recommendations = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        recommendations.put(book, new LinkedList<>());
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void addRecommendation(String targetTitle, String recommendationOneTitle, String recommendationTwoTitle) {
        Book target = findBookByTitle(targetTitle);
        Book recommendationOne = findBookByTitle(recommendationOneTitle);
        Book recommendationTwo = findBookByTitle(recommendationTwoTitle);

        if (target != null && recommendationOne != null && recommendationTwo != null) {
            LinkedList<Book> targetRecommendations = recommendations.getOrDefault(target, new LinkedList<>());
            targetRecommendations.add(recommendationOne);
            targetRecommendations.add(recommendationTwo);
            recommendations.put(target, targetRecommendations);

            LinkedList<Book> recommendationOneList = recommendations.getOrDefault(recommendationOne, new LinkedList<>());
            recommendationOneList.add(target);
            recommendations.put(recommendationOne, recommendationOneList);

            LinkedList<Book> recommendationTwoList = recommendations.getOrDefault(recommendationTwo, new LinkedList<>());
            recommendationTwoList.add(target);
            recommendations.put(recommendationTwo, recommendationTwoList);
        }
    }

    public void imprimirGrafo() {
        for (Book book : books) {
            System.out.println("\nLivro: " + book);
            System.out.print("Recomendações: ");

            LinkedList<Book> recommendationList = recommendations.getOrDefault(book, new LinkedList<>());
            for (int j = 0; j < recommendationList.size(); j++) {
                System.out.print(recommendationList.get(j));
                if (j < recommendationList.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public void imprimirGrafoComoArvore() {
        for (Book book : books) {
            imprimirLivroComRecomendacoes(book, 0, new ArrayList<>());
        }
    }

    private void imprimirLivroComRecomendacoes(Book book, int nivel, ArrayList<Book> visitados) {
        if (visitados.contains(book)) {
            return;
        }
        visitados.add(book);

        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.println(book);

        LinkedList<Book> recommendationList = recommendations.getOrDefault(book, new LinkedList<>());
        for (Book recommendation : recommendationList) {
            imprimirLivroComRecomendacoes(recommendation, nivel + 1, visitados);
        }
    }

    public void imprimirRamoDaArvore(String tituloInicial) {
        Book livroInicial = findBookByTitle(tituloInicial);
        if (livroInicial != null) {
            imprimirLivroComRecomendacoes(livroInicial, 0, new ArrayList<>());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Grafo grafoBooks = new Grafo();
        grafoBooks.addBook(new Book("Uma Trilha Sonora para o Final dos Tempos", "Anthony Marra", 2018));
        grafoBooks.addBook(new Book("Sapiens - Uma Breve História da Humanidade", "Yuval Harari", 2011));
        grafoBooks.addBook(new Book("Gula - O Clube dos Anjos", "Luis Fernando Verissimo", 1998));
        grafoBooks.addBook(new Book("Crash uma Breve História da Economia", "Alexandre Versignassi", 2019));
        grafoBooks.addBook(new Book("Sherlock Holmes: Um Estudo em Vermelho", "Arthur Conan Doyle", 1887));
        grafoBooks.addBook(new Book("Ansiedade O Mal do Século", "Augusto Cury", 1999));
        grafoBooks.addBook(new Book("Assassinato no Expresso Oriente", "Agatha Christie", 1934));
        grafoBooks.addBook(new Book("Antifrágil", "Nassim Nicholas Taleb", 2012));
        grafoBooks.addBook(new Book("O Homem Mais Rico da Babilônia", "George S. Clason", 1926));
        grafoBooks.addBook(new Book("A Revolta de Atlas", "Ayn Rand", 1957));
        grafoBooks.addBook(new Book("Macunaíma", "Mário de Andrade", 1928));
        grafoBooks.addBook(new Book("Poesias de Fernando Pessoa", "Fernando Pessoa", 1934));
        grafoBooks.addBook(new Book("Hagakure", "Yamamoto Tsunetomo", 1716));
        grafoBooks.addBook(new Book("A Arte da Guerra", "Sun Tzu", -500));
        grafoBooks.addBook(new Book("História do Brasil para Quem Tem Pressa", "Marco Antonio Villa", 2016));
        grafoBooks.addBook(new Book("Filosofia", "Globo Livros", 2016));
        grafoBooks.addBook(new Book("Negócios", "Globo Livros", 2016));
        grafoBooks.addBook(new Book("O Guia do Mochileiro das Galáxias", "Douglas Adams", 1979));
        grafoBooks.addBook(new Book("O Poder do Hábito", "Charles Duhigg", 2012));
        grafoBooks.addBook(new Book("O Andar do Bêbado", "Leonard Mlodinow", 2008));
        grafoBooks.addBook(new Book("Quincas Borba", "Machado de Assis", 1891));
        grafoBooks.addBook(new Book("Homo Deus: Uma Breve História do Amanhã", "Yuval Harari", 2015));
        grafoBooks.addBook(new Book("21 Lições para o Século 21", "Yuval Harari", 2018));
        grafoBooks.addBook(new Book("Rebelde (Vol. 1 As crônicas de Starbuck)", " Bernard Cornwell", 2014));


        grafoBooks.addRecommendation("Sapiens - Uma Breve História da Humanidade", "Crash uma Breve História da Economia", "Ansiedade O Mal do Século");
        grafoBooks.addRecommendation("Uma Trilha Sonora para o Final dos Tempos", "Assassinato no Expresso Oriente", "Sherlock Holmes: Um Estudo em Vermelho");
        grafoBooks.addRecommendation("Ansiedade O Mal do Século", "Antifrágil", "O Poder do Hábito");
        grafoBooks.addRecommendation("O Homem Mais Rico da Babilônia", "Hagakure", "A Arte da Guerra");
        grafoBooks.addRecommendation("Filosofia", "Negócios", "O Poder do Hábito");
        grafoBooks.addRecommendation("O Andar do Bêbado", "Antifrágil", "Hagakure");
        grafoBooks.addRecommendation("A Revolta de Atlas", "Antifrágil", "Negócios");
        grafoBooks.addRecommendation("Quincas Borba", "Macunaíma", "Gula - O Clube dos Anjos");    
        grafoBooks.addRecommendation("O Guia do Mochileiro das Galáxias", "Quincas Borba", "Rebelde (Vol. 1 As crônicas de Starbuck)"); 
        grafoBooks.addRecommendation("Homo Deus: Uma Breve História do Amanhã", "21 Lições para o Século 21", "Antifrágil");
        grafoBooks.addRecommendation("Poesias de Fernando Pessoa", "História do Brasil para Quem Tem Pressa", "Macunaíma");
        grafoBooks.addRecommendation("Assassinato no Expresso Oriente", "Rebelde (Vol. 1 As crônicas de Starbuck)", "Gula - O Clube dos Anjos");
        grafoBooks.addRecommendation("Rebelde (Vol. 1 As crônicas de Starbuck)", "A Revolta de Atlas", "Sapiens - Uma Breve História da Humanidade");
        
        System.out.println("SISTEMA DE RECOMENDAÇÃO DE LITERATURAS");

        grafoBooks.imprimirGrafo(); 
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o título do livro para iniciar a leitura:");
        String tituloInicial = scanner.nextLine();

        grafoBooks.imprimirRamoDaArvore(tituloInicial);

        scanner.close();

    }
}
