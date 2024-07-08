import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grafo grafoBooks = new Grafo();
        NavigationHistory navigationHistory = new NavigationHistory();
        Waitlist waitlist = new Waitlist();

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

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu de Seleção:");
            System.out.println("1. Imprimir livros");
            System.out.println("2. Imprimir Grafo Como Árvore");
            System.out.println("3. Imprimir Ramo da Árvore");
            System.out.println("4. Imprimir Recomendações por Distância");
            System.out.println("5. Exibir Histórico de Navegação");
            System.out.println("6. Adicionar à Lista de Espera");
            System.out.println("7. Imprimir Lista de Espera");
            System.out.println("0. Sair");
            System.out.print("Selecione uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    grafoBooks.imprimirGrafo();
                    break;
                case 2:
                    grafoBooks.imprimirGrafoComoArvore();
                    break;
                case 3:
                    System.out.print("Digite o título inicial: ");
                    String tituloInicial = scanner.nextLine();
                    Book initialBook = grafoBooks.findBookByTitle(tituloInicial);
                    if (initialBook != null) {
                        navigationHistory.push(initialBook);
                        grafoBooks.imprimirRamoDaArvore(tituloInicial);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o título inicial: ");
                    tituloInicial = scanner.nextLine();
                    initialBook = grafoBooks.findBookByTitle(tituloInicial);
                    if (initialBook != null) {
                        navigationHistory.push(initialBook);
                        grafoBooks.imprimirRecomendacoesPorDistancia(tituloInicial);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 5:
                    navigationHistory.printHistory();
                    break;
                case 6:
                    System.out.print("Digite o nome do usuário: ");
                    String userName = scanner.nextLine();
                    System.out.print("Digite o título do livro: ");
                    String bookTitle = scanner.nextLine();
                    Book book = grafoBooks.findBookByTitle(bookTitle);
                    if (book != null) {
                        waitlist.addToWaitlist(userName, book);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 7:
                    waitlist.printWaitlist();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }
}