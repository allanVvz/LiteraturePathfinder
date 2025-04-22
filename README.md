https://www.youtube.com/watch?v=E9IbCjQKhM4

# LiteraturePathfinder

> **BookQueueManager** – Um sistema Java para construir e navegar em grafos de literatura, sugerindo sequências de leitura.

---

## 📖 Visão Geral

O **LiteraturePathfinder** modela livros como nós e relações (gênero, citações, autoria, etc.) como arestas de um grafo, e utiliza algoritmos de caminho mínimo para gerar sugestões de leitura. Ele mantém histórico de navegação e uma “waitlist” de livros a explorar.

---

## 🚀 Funcionalidades

- **Modelagem de Grafo**  
  Representa cada livro como um nó em um grafo e as conexões (semelhança temático, sequências, citações) como arestas.

- **Algoritmos de Pathfinding**  
  Implementa busca em largura (BFS) e Dijkstra para encontrar o “melhor caminho” de leitura.

- **Histórico de Navegação**  
  Rastreia o percurso de livros já visitados.

- **Gestão de Waitlist**  
  Mantém uma fila de espera de livros pendentes para leitura futura.

- **Arquivos de Dados Simples**  
  - `visualized_books.txt`: nó x lista de adjacências  
  - `waitlist.txt`: livros a serem adicionados ao grafo

---

## 📁 Estrutura do Repositório

LiteraturePathfinder/ ├── Book.java ├── Grafo.java ├── Main.java ├── NavigationHistory.java ├── Waitlist.java ├── visualized_books.txt └── waitlist.txt


- **Book.java**  
  Classe que representa os atributos de um livro (ID, título, autor, etc.).

- **Grafo.java**  
  Implementação básica de grafo (lista de adjacência) com métodos para adicionar nós/arestas e executar buscas.

- **Main.java**  
  Ponto de entrada: carrega dados, constrói o grafo, executa algoritmos e exibe resultados.

- **NavigationHistory.java**  
  Gerencia o histórico de nós visitados durante a busca.

- **Waitlist.java**  
  Gerencia a fila de espera de novos livros a serem inseridos no grafo.

---

## 💻 Pré‑requisitos

- Java Development Kit (JDK) 8 ou superior
- (Opcional) IDE como IntelliJ IDEA, Eclipse ou VS Code com extensão Java

---

🎯 Próximos Passos
▶️ Peso nas Arestas: adicionar pesos (ex.: relevância, distância temática) e usar Dijkstra.

▶️ Interface Gráfica: implementar front‑end web ou desktop para visualização interativa.

▶️ Machine Learning: analisar padrões de leitura para refinamento automático de pesos.

