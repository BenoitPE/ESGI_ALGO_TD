package Models.Graph;

import java.util.*;

public class MyGraph<T> {
    private List<MyVertex> vertices;
    private Map<String, Integer> mapVerticesToIndex;

    public MyGraph() {
        this.vertices = new ArrayList<>();
        this.mapVerticesToIndex = new HashMap<>();
    }

    public void setVerticesByMap(Map<String, String[]> vertices) {
        List<MyVertex> listVertices = new ArrayList<>();
        //Initialize all vertices
        int i=0;
        for (String vertexName : vertices.keySet()) {
            listVertices.add(new MyVertex<>(vertexName));
            mapVerticesToIndex.put(vertexName, i);
            i++;
        }

        //Adds adjacent vertices
        for (String from : vertices.keySet()) {
            System.out.println(from + ":");

            for (String to : vertices.get(from)) {
                System.out.println("\t" + to);
                listVertices.get(mapVerticesToIndex.get(from)).setAdjacentVertex(new MyOrientedEdge(listVertices.get(mapVerticesToIndex.get(to))));
            }
        }

        this.vertices = listVertices;
    }

    public void setVerticesByMatrix(Integer[][] matrix) {
        List<MyVertex> listVertices = new ArrayList<>();
        //Initialize all vertices
        for (int i = 0; i < matrix.length; i++) {
            listVertices.add(new MyVertex<>(i));
        }

        //Adds adjacent vertices
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    listVertices.get(i).setAdjacentVertex(new MyOrientedEdge(listVertices.get(j)));
                }
            }
        }
        this.vertices = listVertices;
    }

    public void setVerticesByMatrix(String[] verticesNames, Integer[][] matrix) {
        List<MyVertex> listVertices = new ArrayList<>();
        //Initialize all vertices
        for (int i = 0; i < matrix.length; i++) {
            listVertices.add(new MyVertex<>(verticesNames[i]));
            mapVerticesToIndex.put(verticesNames[i], i);
        }

        //Adds adjacent vertices
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    listVertices.get(i).setAdjacentVertex(new MyOrientedEdge(listVertices.get(j)));
                }
            }
        }
        this.vertices = listVertices;
    }

    //region Width and Depth Courses
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
                    queue.add(((MyOrientedEdge) currentVertex.getAdjacentVertices().get(i)).getDestination());
                }
            }
        }
        return resultQueue;
    }


    public static List<MyVertex> depthCourse(MyVertex T, List<MyVertex> result, List<MyVertex> visitedVertices) {
        if (!visitedVertices.contains(T)) {
            result.add(T);
            visitedVertices.add(T);
            for (int i = 0; i < T.getAdjacentVertices().size(); i++) {
                depthCourse(((MyOrientedEdge) T.getAdjacentVertices().get(i)).getDestination(), result, visitedVertices);
            }
        }
        return result;
    }

    public static boolean depthCoursePoint(MyVertex T, MyVertex search, List<MyVertex> invertedPath, List<MyVertex> visitedVertices) {
        if (T.getName() == search.getName()) {
            return true;
        } else if (!visitedVertices.contains(T)) {
            visitedVertices.add(T);
            for (int i = 0; i < T.getAdjacentVertices().size(); i++) {
                if (depthCoursePoint(((MyOrientedEdge) T.getAdjacentVertices().get(i)).getDestination(), search, invertedPath, visitedVertices) == true) {
                    invertedPath.add(T);
                    return true;
                }
            }
        }
        return false;
    }
    //endregion

    public void adjacenciesList() {
        for (int i = 0; i < this.vertices.size(); i++) {
            System.out.print(this.vertices.get(i).getName() + ": ");
            for (int j = 0; j < this.vertices.get(i).getDegree(); j++) {
                MyVertex myVertex = ((MyOrientedEdge) this.vertices.get(i).getAdjacentVertices().get(j)).getDestination();
                System.out.print(myVertex.getName() + ", ");
            }
            System.out.println();
        }
    }

    public MySquareMatrix adjacencyMatrix() {
        MySquareMatrix res = new MySquareMatrix(vertices.size());
        for (int i = 0; i < vertices.size(); i++) {
            MyVertex myVertex = vertices.get(i);
            for (int j = 0; j < myVertex.getAdjacentVertices().size(); j++) {
                res.setValue(i, mapVerticesToIndex.get(((MyOrientedEdge) myVertex.getAdjacentVertices().get(j)).getDestination().getName()), 1);
            }
        }
        return res;
    }

    public static Queue<MyVertex> listToQueue(List<MyOrientedEdge> listVertices) {
        Queue<MyVertex> q = new LinkedList<>();
        for (int i = 0; i < listVertices.size(); i++) {
            q.add(listVertices.get(i).getDestination());
        }
        return q;
    }


    //region Dijkstra
    public MyPathResult getShortestPathDijkstra(MyVertex from, MyVertex to) {
        MyPathResult res = new MyPathResult();
        Map<String, Integer> mapNameToIndex = new HashMap<>();

        long startTimer = System.nanoTime();
        List<MyDijkstraVertex> allVertices = dijkstra(from, mapNameToIndex);
        // Sets the runtime for the Dijkstra algorithm
        res.setRuntime(System.nanoTime() - startTimer);

        MyDijkstraVertex current = allVertices.get(mapNameToIndex.get(to.getName()));
        // Defines the length of the path from the start vertex to the end vertex
        res.setLength(current.getDistanceFromSource());

        // Makes the inverse way (the start vertex towards the end vertex)
        while (current.getBestParentFromSource() != to && current.getBestParentFromSource() != null) {
            res.getInvertedPath().add(current.getVertex());
            current = allVertices.get(mapNameToIndex.get(current.getBestParentFromSource().getName()));
        }
        res.getInvertedPath().add(from);

        return res;
    }

    public List<MyDijkstraVertex> dijkstra(MyVertex start, Map<String, Integer> mapNameToIndex) {
        List<MyDijkstraVertex> dijkstraList = new ArrayList<>();
        MyDijkstraVertex currentNode = new MyDijkstraVertex(new MyVertex(""));

        //Initialization
        for (int i = 0; i < vertices.size(); i++) {
            MyDijkstraVertex dijkstraVertex = new MyDijkstraVertex(vertices.get(i));
            if (dijkstraVertex.getVertex().getName() == start.getName()) {
                dijkstraVertex.setDistanceFromSource(0);
                currentNode = dijkstraVertex;
            }
            mapNameToIndex.put(dijkstraVertex.getVertex().getName().toString(), i);
            dijkstraList.add(dijkstraVertex);
        }

        while (currentNode != null) {
            currentNode.setVisited(true);
            for (int i = 0; i < currentNode.getVertex().getAdjacentVertices().size(); i++) {
                MyOrientedEdge branch = (MyOrientedEdge) currentNode.getVertex().getAdjacentVertices().get(i);
                MyDijkstraVertex branchNode = dijkstraList.get(mapNameToIndex.get(((MyOrientedEdge) currentNode.getVertex().getAdjacentVertices().get(i)).getDestination().getName()));

                if (branchNode.getDistanceFromSource() > currentNode.getDistanceFromSource() + branch.getWeight()) {
                    // We found a more optimal way, we save it
                    branchNode.setDistanceFromSource(currentNode.getDistanceFromSource() + branch.getWeight());
                    branchNode.setBestParentFromSource(currentNode.getVertex());
                    dijkstraList.set(mapNameToIndex.get(((MyOrientedEdge) currentNode.getVertex().getAdjacentVertices().get(i)).getDestination().getName()), branchNode);
                }
            }

            // Assign to currentNode the instance with the smallest value to the distanceFromSource property
            MyDijkstraVertex tmpNode = new MyDijkstraVertex(null, true, Double.POSITIVE_INFINITY, null);
            for (int i = 0; i < dijkstraList.size(); i++) {
                //System.out.println(tmpNode.getDistanceFromSource() + " > " + dijkstraList.get(i).getDistanceFromSource() + " && " + dijkstraList.get(i).isVisited());
                if (tmpNode.getDistanceFromSource() > dijkstraList.get(i).getDistanceFromSource() && dijkstraList.get(i).isVisited() == false) {
                    tmpNode = dijkstraList.get(i);
                }
            }
            if (tmpNode.getDistanceFromSource() != Double.POSITIVE_INFINITY) {
                currentNode = tmpNode;
            } else {
                currentNode = null;
            }
        }

        return dijkstraList;
    }
    //endregion

    //region Getters and Setters
    public List<MyVertex> getVertices() {
        return vertices;
    }

    public MyVertex getVertex(int i) {
        return this.vertices.get(i);
    }

    public MyVertex getVertex(String name) {
        return this.vertices.get(mapVerticesToIndex.get(name));
    }

    public void setVertices(List<MyVertex> vertices) {
        this.vertices = vertices;
    }

    public void setVertex(MyVertex myVertex) {
        this.vertices.add(myVertex);
    }

    public Map<String, Integer> getMapVerticesToIndex() {
        return mapVerticesToIndex;
    }

    public void setMapVerticesToIndex(Map<String, Integer> mapVerticesToIndex) {
        this.mapVerticesToIndex = mapVerticesToIndex;
    }
    //endregion

}
