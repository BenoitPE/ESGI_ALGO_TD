import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer[][] matrix = {
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 1, 1, 0}
        };

        Graph<Integer> graph = new Graph();
        graph.setVerticesByMatrix(matrix);

        System.out.print("Parcours en largeur:      ");
        Queue<Vertex> qWidthCourse = Graph.widthCourse(graph.getVertex(1));
        qWidthCourse.forEach(v -> System.out.print(v.getName() + ", "));
        System.out.println();

        System.out.print("Parcours en profondeur:   ");
        List<Vertex> qDepthCourse = new ArrayList<>();
        Graph.depthCourse(graph.getVertex(1), qDepthCourse, new ArrayList<>());
        qDepthCourse.forEach(v -> System.out.print(v.getName() + ", "));
        System.out.println();

        System.out.println("Matrice d'adjacence:    ");
        SquareMatrix adjacencyMatrix = Graph.adjacencyMatrix(graph);
        SquareMatrix.print(adjacencyMatrix);

        System.out.println("Matrice transposée");
        SquareMatrix transposeSquareMatrix = SquareMatrix.transposeSquareMatrix(adjacencyMatrix);
        SquareMatrix.print(transposeSquareMatrix);

        System.out.println("Matrice d'adjacence de la fermeture symétrique");
        SquareMatrix.print(SquareMatrix.transitiveMatrixFromAdjancyMatrix(adjacencyMatrix));
        System.out.println();

        List<Vertex> invertedPath = new LinkedList<>();
        System.out.println(Graph.depthCoursePoint(graph.getVertex(1), graph.getVertex(5), invertedPath, new LinkedList<>()));
        System.out.print("Chemin inversé: ");
        invertedPath.forEach(v -> System.out.print(v.getName() + ", "));
    }


}