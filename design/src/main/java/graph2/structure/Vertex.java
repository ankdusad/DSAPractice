package graph2.structure;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(of = {"id"})
public class Vertex<T> {
    private long id;
    private T data;
    private List<Edge<T>> edges = new ArrayList<>();
    private List<Vertex<T>> adjacentVertices = new ArrayList<>();

    Vertex(long id, T data) {
        this.id = id;
        this.data = data;
    }

    public void addAdjacantVertices(Edge<T> edge, Vertex<T> vertex) {
        edges.add(edge);
        adjacentVertices.add(vertex);
    }

    public int getDegree() {
        return adjacentVertices.size();
    }

}
