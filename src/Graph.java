import java.util.*;

public class Graph<T> {
    private List<Vertex> vertices;

    public Graph() {
        this.vertices = new ArrayList<>();
    }

    public void setVerticesByMatrix(Integer[][] matrix) {
        List<Vertex> listVertices = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            listVertices.add(new Vertex<>(i));
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    listVertices.get(i).setAdjacentVertex(listVertices.get(j));
                }
            }
        }
        this.vertices = listVertices;
    }

    public void setVerticesByMatrix(List<String> verticesNames,Integer[][] matrix) {
        List<Vertex> listVertices = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            listVertices.add(new Vertex<>(verticesNames.get(i)));
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    listVertices.get(i).setAdjacentVertex(listVertices.get(j));
                }
            }
        }
        this.vertices = listVertices;
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
        for (int i = 0; i < this.vertices.size(); i++) {
            System.out.print(this.vertices.get(i).getName() + ": ");
            for (int j = 0; j < this.vertices.get(i).getDegree(); j++) {
                Vertex vertex = (Vertex) this.vertices.get(i).getAdjacentVertices().get(j);
                System.out.print(vertex.getName() + ", ");
            }
            System.out.println();
        }
    }

    public static Queue<Vertex> widthCourse(Vertex startingVertex) {
        Queue<Vertex> resultQueue = new LinkedList<>();
        Queue<Vertex> queue = listToQueue(startingVertex.getAdjacentVertices());
        List<Vertex> visitedVerticesList = new ArrayList<>();
        visitedVerticesList.add(startingVertex);
        resultQueue.add(startingVertex);

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.remove();
            if (!visitedVerticesList.contains(currentVertex)) {
                visitedVerticesList.add(currentVertex);
                resultQueue.add(currentVertex);

                for (int i = 0; i < currentVertex.getAdjacentVertices().size(); i++) {
                    queue.add((Vertex) currentVertex.getAdjacentVertices().get(i));
                }
            }
        }
        return resultQueue;
    }


    public static List<Vertex> depthCourse(Vertex T, List<Vertex> result, List<Vertex> visitedVertices) {
        if(!visitedVertices.contains(T)) {
            result.add(T);
            visitedVertices.add(T);
            for(int i=0; i < T.getAdjacentVertices().size(); i++) {
                depthCourse((Vertex) T.getAdjacentVertices().get(i), result, visitedVertices);
            }
        }
        return result;
    }

    public static boolean depthCoursePoint(Vertex T, Vertex search, List<Vertex> invertedPath, List<Vertex> visitedVertices)
    {
        if(T.getName() == search.getName()) {
            return true;
        }
        else if(!visitedVertices.contains(T))
        {
            visitedVertices.add(T);
            for (int i=0; i< T.getAdjacentVertices().size(); i++) {
                if(depthCoursePoint((Vertex) T.getAdjacentVertices().get(i), search, invertedPath, visitedVertices) == true) {
                    invertedPath.add(T);
                    return true;
                }
            }
        }
        return false;
    }

    public static Queue<Vertex> listToQueue(List<Vertex> listVertices)
    {
        Queue<Vertex> q = new LinkedList<>();
        for (int i = 0; i < listVertices.size(); i++) {
            q.add(listVertices.get(i));
        }
        return q;
    }

    public static SquareMatrix adjacencyMatrix(Graph g)
    {
        SquareMatrix res = new SquareMatrix(g.vertices.size());

        for (int i=0; i < g.vertices.size(); i++) {
            Vertex vertex = (Vertex) g.vertices.get(i);

            for(int j= 0; j < vertex.getAdjacentVertices().size(); j++) {
                res.setValue(i, (int) ((Vertex) vertex.getAdjacentVertices().get(j)).getName(), 1);
            }
        }
        return res;
    }




}
