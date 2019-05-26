package graph2.structure;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge<T> {
    private boolean isDirected;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private double weight;

}
