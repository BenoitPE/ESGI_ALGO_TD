import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
    private List<Vertex> vertices;

    public Graph(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Graph() {
        this.vertices = new ArrayList<>();
    }

    public void setVerticesByMatrix(Integer[][] matrix) {
        for(int i=0; i < matrix.length; i++) {
            Vertex<Integer> vertex = new Vertex<>(i);
            for(int j=0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 1) {
                    vertex.setAdjacentVertex(new Vertex<Integer>(j));
                }
            }
            this.vertices.add(vertex);
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int i) {
        return this.vertices.get(i);
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void setVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }

    public void adjacenciesList() {
        for (int i =0; i< this.vertices.size(); i++) {
            System.out.print(this.vertices.get(i).getName() + ": ");
            for (int j=0; j < this.vertices.get(i).getDegree(); j++) {
                Vertex vertex = (Vertex) this.vertices.get(i).getAdjacentVertices().get(j);
                System.out.print(vertex.getName() + ", ");
            }
            System.out.println();
        }
    }
}
