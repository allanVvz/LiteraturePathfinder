import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

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

    public HashMap<Book, Integer> dijkstra(Book initialBook) {
        HashMap<Book, Integer> distances = new HashMap<>();
        PriorityQueue<BookDistancePair> pq = new PriorityQueue<>();
        for (Book book : books) {
            distances.put(book, Integer.MAX_VALUE);
        }
        distances.put(initialBook, 0);
        pq.add(new BookDistancePair(initialBook, 0));

        while (!pq.isEmpty()) {
            BookDistancePair currentPair = pq.poll();
            Book currentBook = currentPair.book;
            int currentDistance = currentPair.distance;

            if (currentDistance > distances.get(currentBook)) {
                continue;
            }

            LinkedList<Book> neighbors = recommendations.getOrDefault(currentBook, new LinkedList<>());
            for (Book neighbor : neighbors) {
                int newDist = currentDistance + 1; // Considering all edges have weight 1
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(new BookDistancePair(neighbor, newDist));
                }
            }
        }
        return distances;
    }

    public void imprimirRecomendacoesPorDistancia(String tituloInicial) {
        Book livroInicial = findBookByTitle(tituloInicial);
        if (livroInicial != null) {
            HashMap<Book, Integer> distances = dijkstra(livroInicial);
            System.out.println("\nRecomendações com base na menor distância a partir de \"" + livroInicial.getTitle() + "\":");
            for (Book book : distances.keySet()) {
                if (!book.equals(livroInicial)) {
                    System.out.println(book + " - Distância: " + distances.get(book));
                }
            }
        } else {
            System.out.println("Livro não encontrado.");
        }
    }
    
    private class BookDistancePair implements Comparable<BookDistancePair> {
        Book book;
        int distance;

        BookDistancePair(Book book, int distance) {
            this.book = book;
            this.distance = distance;
        }

        @Override
        public int compareTo(BookDistancePair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}

