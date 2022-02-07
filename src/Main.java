import Models.Graph.MyGraph;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        /*
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
        MySquareMatrix adjacencyMatrix = myGraph.adjacencyMatrix();
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
        System.out.println();

         */

        Map<String, String[]> graphValues = new LinkedHashMap<>();
        graphValues.put("Annecy",               new String[]{"La Roche-sur-Foron"});
        graphValues.put("Annemasse",            new String[]{"Genève", "La Roche-sur-Foron"});
        graphValues.put("Bellegarde",           new String[]{"Meyrin", "Seyssel"});
        graphValues.put("Coppet",               new String[]{"Les Tuileries", "Nyon"});
        graphValues.put("Evian-les-Bains",      new String[]{"Thonon-les-Bains"});
        graphValues.put("Genève",               new String[]{"Annemasse", "Les Tuileries", "Meyrin"});
        graphValues.put("La Plaine",            new String[]{"Meyrin"});
        graphValues.put("La Roche-sur-Foron",   new String[]{"Annecy", "Annemasse", "St Gervais-les-Bains", "Thonon-les-Bains"});
        graphValues.put("Les Tuileries",        new String[]{"Coppet", "Genève"});
        graphValues.put("Meyrin",               new String[]{"Bellegarde", "Genève"});
        graphValues.put("Nyon",                 new String[]{});
        graphValues.put("Seyssel",              new String[]{});
        graphValues.put("St Gervais-les-Bains", new String[]{"Annecy", "La Roche-sur-Foron"});
        graphValues.put("Thonon-les-Bains",     new String[]{"Bellegarde", "La Roche-sur-Foron"});

        MyGraph myGraph = new MyGraph();
        myGraph.setVertices(graphValues);
    }


}