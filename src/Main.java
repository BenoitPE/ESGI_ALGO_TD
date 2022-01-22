import Models.MyGraph;
import Models.MySquareMatrix;
import Models.MyVertex;

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

        MyGraph<Integer> myGraph = new MyGraph();
        myGraph.setVerticesByMatrix(matrix);

        System.out.print("Parcours en largeur:      ");
        Queue<MyVertex> qWidthCourse = MyGraph.widthCourse(myGraph.getVertex(1));
        qWidthCourse.forEach(v -> System.out.print(v.getName() + ", "));
        System.out.println();

        System.out.print("Parcours en profondeur:   ");
        List<MyVertex> qDepthCourse = new ArrayList<>();
        MyGraph.depthCourse(myGraph.getVertex(1), qDepthCourse, new ArrayList<>());
        qDepthCourse.forEach(v -> System.out.print(v.getName() + ", "));
        System.out.println();

        System.out.println("Matrice d'adjacence:    ");
        MySquareMatrix adjacencyMatrix = MyGraph.adjacencyMatrix(myGraph);
        MySquareMatrix.print(adjacencyMatrix);

        System.out.println("Matrice transposée");
        MySquareMatrix transposeSquareMatrix = MySquareMatrix.transposeSquareMatrix(adjacencyMatrix);
        MySquareMatrix.print(transposeSquareMatrix);

        System.out.println("Matrice d'adjacence de la fermeture symétrique");
        MySquareMatrix.print(MySquareMatrix.transitiveMatrixFromAdjancyMatrix(adjacencyMatrix));
        System.out.println();

        List<MyVertex> invertedPath = new LinkedList<>();
        System.out.println(MyGraph.depthCoursePoint(myGraph.getVertex(1), myGraph.getVertex(5), invertedPath, new LinkedList<>()));
        System.out.print("Chemin inversé: ");
        invertedPath.forEach(v -> System.out.print(v.getName() + ", "));
    }


}