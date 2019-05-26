package graph1;

import lombok.Data;

import java.util.*;

import static java.util.Objects.nonNull;

public class Graph {

    private Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    void removeVertex(String label) {
        Vertex vertex = new Vertex(label);
        adjVertices.values().forEach(vertices -> vertices.remove(vertex));
        adjVertices.remove(vertex);
    }

    void addEdge(String label1, String label2) {
        Vertex vertex1 = new Vertex(label1);
        Vertex vertex2 = new Vertex(label2);
        adjVertices.get(vertex1).add(vertex2);
        adjVertices.get(vertex2).add(vertex1);
    }

    void removeEdge(String label1, String label2) {
        Vertex vertex1 = new Vertex(label1);
        Vertex vertex2 = new Vertex(label2);
        if (nonNull(adjVertices.get(vertex1)) && nonNull(adjVertices.get(vertex2)))
            adjVertices.get(vertex1).remove(vertex2);
        adjVertices.get(vertex2).remove(vertex1);
    }

    List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    Set<String> dfs(String root) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                getAdjVertices(vertex).forEach(v -> stack.push(v.getLabel()));
            }
        }
        return visited;
    }

    Set<String> dfsRecursive(String root) {
        Set<String> visited = new HashSet<>();
        return dfsRecUtil(root, visited);
    }

    private Set<String> dfsRecUtil(String root, Set<String> visited) {
        visited.add(root);
        getAdjVertices(root).forEach(vertex -> {
            if (!visited.contains(vertex.getLabel())) {
                dfsRecUtil(vertex.getLabel(), visited);
            }
        });
        return visited;
    }

    Set<String> bfs(String root) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            getAdjVertices(vertex).forEach(v -> {
                if (!visited.contains(v.getLabel())) {
                    queue.add(v.getLabel());
                    visited.add(v.getLabel());
                }
            });
        }
        return visited;
    }
}

@Data
class Vertex {
    private String label;

    Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
