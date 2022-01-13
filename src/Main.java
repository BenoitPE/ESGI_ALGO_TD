import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer[][] matrix = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 0}
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
        int[][] adjacencyMatrix = Graph.adjacencyMatrix(graph);
        printMatrix(adjacencyMatrix);

        System.out.println("Matrice transposée");
        int[][] transposeSquareMatrix = Graph.transposeSquareMatrix(adjacencyMatrix);
        printMatrix(transposeSquareMatrix);

        System.out.println("Matrice d'adjacence de la fermeture symétrique");
        printMatrix(Graph.transitiveMatrixFromAdjancyMatrix(adjacencyMatrix));

    }

    private static void printMatrix(int[][] matrix) {
        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}