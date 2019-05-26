package graph1;

public class GraphDriver {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
        System.out.println(graph.dfs("Bob"));
        System.out.println(graph.dfsRecursive("Bob"));
        System.out.println(graph.bfs("Bob"));
        System.out.println("finished...");
    }
}
