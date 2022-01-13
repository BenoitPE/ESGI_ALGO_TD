import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer[][] matrix = {
                {0, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 1, 1, 0, 1},
                {0, 0, 0, 0, 1, 0}
        };

        Graph<Integer> graph = new Graph();
        graph.setVerticesByMatrix(matrix);

        System.out.print("Parcours en largeur:      ");
        Queue<Vertex> qWidthCourse = Graph.widthCourse(graph.getVertex(1));
        qWidthCourse.forEach(v -> System.out.print(v.getName() + ", "));
        System.out.println();

        /*System.out.print("Parcours en profondeur:      ");
        List<Vertex> qDepthCourse = new ArrayList<>();
        graph.depthCourse(graph.getVertex(1), qDepthCourse);
        qDepthCourse.forEach(v -> System.out.print(v.getName() + ", "));
        System.out.println();*/


    }
}