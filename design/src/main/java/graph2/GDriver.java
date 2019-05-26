package graph2;

import graph2.structure.Graph;

public class GDriver {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 11);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 3);
        graph.addEdge(3, 8);
        graph.addEdge(8, 11);

        System.out.println(graph.dfs(1));
        System.out.println(graph.dfsRec(1));
        System.out.println(graph.bfs(1));

        System.out.println("Finished");
    }
}
