package Models.Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private String name;
    private List<Edge> adjacentVertices;

    //region Constructors
    public Vertex(String name) {
        this.name = name;
        this.adjacentVertices = new ArrayList<>();
    }
    //endregion

    //region Getters and Setters
    public int getDegree() {
        return this.adjacentVertices.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getAdjacents() {
        return adjacentVertices;
    }

    public void setAdjacents(List<Edge> adjacents) {
        this.adjacentVertices = adjacents;
    }

    public void setAdjacent(Edge adjacent) {
        this.adjacentVertices.add(adjacent);
    }
    //endregion
}
