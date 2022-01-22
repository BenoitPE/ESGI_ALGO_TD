package Models;

import java.util.*;

public class MyGraph<T> {
    private List<MyVertex> vertices;

    public MyGraph() {
        this.vertices = new ArrayList<>();
    }

    public void setVerticesByMatrix(Integer[][] matrix) {
        List<MyVertex> listVertices = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            listVertices.add(new MyVertex<>(i));
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
        List<MyVertex> listVertices = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            listVertices.add(new MyVertex<>(verticesNames.get(i)));
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

    public List<MyVertex> getVertices() {
        return vertices;
    }

    public MyVertex getVertex(int i) {
        return this.vertices.get(i);
    }

    public void setVertices(List<MyVertex> vertices) {
        this.vertices = vertices;
    }

    public void setVertex(MyVertex myVertex) {
        this.vertices.add(myVertex);
    }

    public void adjacenciesList() {
        for (int i = 0; i < this.vertices.size(); i++) {
            System.out.print(this.vertices.get(i).getName() + ": ");
            for (int j = 0; j < this.vertices.get(i).getDegree(); j++) {
                MyVertex myVertex = (MyVertex) this.vertices.get(i).getAdjacentVertices().get(j);
                System.out.print(myVertex.getName() + ", ");
            }
            System.out.println();
        }
    }

    public static Queue<MyVertex> widthCourse(MyVertex startingVertex) {
        Queue<MyVertex> resultQueue = new LinkedList<>();
        Queue<MyVertex> queue = listToQueue(startingVertex.getAdjacentVertices());
        List<MyVertex> visitedVerticesList = new ArrayList<>();
        visitedVerticesList.add(startingVertex);
        resultQueue.add(startingVertex);

        while (!queue.isEmpty()) {
            MyVertex currentVertex = queue.remove();
            if (!visitedVerticesList.contains(currentVertex)) {
                visitedVerticesList.add(currentVertex);
                resultQueue.add(currentVertex);

                for (int i = 0; i < currentVertex.getAdjacentVertices().size(); i++) {
                    queue.add((MyVertex) currentVertex.getAdjacentVertices().get(i));
                }
            }
        }
        return resultQueue;
    }


    public static List<MyVertex> depthCourse(MyVertex T, List<MyVertex> result, List<MyVertex> visitedVertices) {
        if(!visitedVertices.contains(T)) {
            result.add(T);
            visitedVertices.add(T);
            for(int i=0; i < T.getAdjacentVertices().size(); i++) {
                depthCourse((MyVertex) T.getAdjacentVertices().get(i), result, visitedVertices);
            }
        }
        return result;
    }

    public static boolean depthCoursePoint(MyVertex T, MyVertex search, List<MyVertex> invertedPath, List<MyVertex> visitedVertices)
    {
        if(T.getName() == search.getName()) {
            return true;
        }
        else if(!visitedVertices.contains(T))
        {
            visitedVertices.add(T);
            for (int i=0; i< T.getAdjacentVertices().size(); i++) {
                if(depthCoursePoint((MyVertex) T.getAdjacentVertices().get(i), search, invertedPath, visitedVertices) == true) {
                    invertedPath.add(T);
                    return true;
                }
            }
        }
        return false;
    }

    public static Queue<MyVertex> listToQueue(List<MyVertex> listVertices)
    {
        Queue<MyVertex> q = new LinkedList<>();
        for (int i = 0; i < listVertices.size(); i++) {
            q.add(listVertices.get(i));
        }
        return q;
    }

    public static MySquareMatrix adjacencyMatrix(MyGraph g)
    {
        MySquareMatrix res = new MySquareMatrix(g.vertices.size());

        for (int i=0; i < g.vertices.size(); i++) {
            MyVertex myVertex = (MyVertex) g.vertices.get(i);

            for(int j = 0; j < myVertex.getAdjacentVertices().size(); j++) {
                res.setValue(i, (int) ((MyVertex) myVertex.getAdjacentVertices().get(j)).getName(), 1);
            }
        }
        return res;
    }




}
