package graph2.structure;

import lombok.Data;

import java.util.*;

@Data
public class Graph<T> {
    private boolean isDirected;
    private Set<Edge<T>> edges;
    private Map<Long, Vertex<T>> vertices;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        edges = new LinkedHashSet<>();
        vertices = new HashMap<>();
    }


    public void addEdge(long id1, long id2) {
        addEdge(id1, id2, 0.0);
    }

    public void addEdge(long id1, long id2, double weight) {
        vertices.putIfAbsent(id1, new Vertex<>(id1, null));
        vertices.putIfAbsent(id2, new Vertex<>(id2, null));

        Edge<T> edge = new Edge<>(false, vertices.get(id1), vertices.get(id2), weight);
        edges.add(edge);
        vertices.get(id1).addAdjacantVertices(edge, vertices.get(id2));
        if (!isDirected) {
            vertices.get(id2).addAdjacantVertices(edge, vertices.get(id1));

        }


    }

    public Set<Vertex<T>> dfs(long id) {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Stack<Vertex<T>> stack = new Stack<>();
        stack.push(vertices.get(id));
        while (!stack.isEmpty()) {
            Vertex<T> vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                vertex.getAdjacentVertices().forEach(stack::push);
            }
        }
        return visited;
    }

    public Set<Vertex<T>> dfsRec(long id) {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        return dfsRecUtil(vertices.get(id), visited);
    }

    private Set<Vertex<T>> dfsRecUtil(Vertex<T> vertex, Set<Vertex<T>> visited) {
        visited.add(vertex);
        vertex.getAdjacentVertices().forEach(v -> {
            if (!visited.contains(v)) {
                dfsRecUtil(v, visited);
            }
        });
        return visited;
    }

    public Set<Vertex<T>> bfs(long id) {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Vertex<T> vertex = vertices.get(id);
        queue.add(vertex);
        visited.add(vertex);
        while (!queue.isEmpty()) {
            Vertex<T> polledVertex = queue.poll();
            if (!queue.contains(polledVertex)) {
                polledVertex.getAdjacentVertices().forEach(v -> {
                    queue.add(v);
                    visited.add(v);
                });
            }
        }
        return visited;
    }


}
