import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer[][] matrix = {
                {0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0}
        };

        Graph<Integer> graph = new Graph();
        graph.setVerticesByMatrix(matrix);
        graph.adjacenciesList();
    }
}