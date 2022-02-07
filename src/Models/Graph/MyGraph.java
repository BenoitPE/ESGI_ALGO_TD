package Models.Graph;

import java.util.*;

public class MyGraph {
    private List<MyVertex> vertices;
    private Map<String, Integer> mapVerticesToIndex;
    private int nbEdges;
    private int nbVertices;

    public MyGraph() {
        this.vertices = new ArrayList<>();
        this.mapVerticesToIndex = new HashMap<>();
        this.nbEdges = 0;
        this.nbVertices = 0;
    }

    public void setVertices(Map<String, String[]> vertices) {
        List<MyVertex> listVertices = new ArrayList<>();

        //Initialize all vertices
        int i = 0;
        for (String vertexName : vertices.keySet()) {
            listVertices.add(new MyVertex<>(vertexName));
            nbVertices++;
            mapVerticesToIndex.put(vertexName, i);
            i++;
        }

        //Adds adjacent vertices
        for (String from : vertices.keySet()) {
            for (String to : vertices.get(from)) {
                listVertices.get(mapVerticesToIndex.get(from)).setAdjacentVertex(new MyEdge(listVertices.get(mapVerticesToIndex.get(to))));
                nbEdges++;
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
                    queue.add(((MyEdge) currentVertex.getAdjacentVertices().get(i)).getDestination());
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
                depthCourse(((MyEdge) T.getAdjacentVertices().get(i)).getDestination(), result, visitedVertices);
            }
        }
        return result;
    }

    public static boolean depthCoursePoint(MyVertex T, MyVertex search, List<MyVertex> invertedPath, List<MyVertex> visitedVertices) {
        if (T.getName() == search.getName()) {
            invertedPath.add(T);
            return true;
        } else if (!visitedVertices.contains(T)) {
            visitedVertices.add(T);
            for (int i = 0; i < T.getAdjacentVertices().size(); i++) {
                if (depthCoursePoint(((MyEdge) T.getAdjacentVertices().get(i)).getDestination(), search, invertedPath, visitedVertices) == true) {
                    invertedPath.add(T);
                    return true;
                }
            }
        }
        return false;
    }
    //endregion

    //region Dijkstra's algorithm
    public MyPathResult getShortestPathDijkstra(MyVertex from, MyVertex to) {
        MyPathResult res = new MyPathResult();
        Map<String, Integer> vertexToIndex = new HashMap<>();

        // Sets the runtime for the Dijkstra algorithm
        long startTimer = System.nanoTime();
        List<MyDijkstraVertex> list = dijkstra(from, vertexToIndex);
        res.setRuntime(System.nanoTime() - startTimer);

        // Defines the length of the path from the start vertex to the end vertex
        MyDijkstraVertex current = list.get(vertexToIndex.get(to.getName()));
        res.setLength(current.getDistFromSource());

        // Makes the inverse way (the start vertex towards the end vertex)
        while (current.getBestParent() != to && current.getBestParent() != null) {
            res.getInvertedPath().add(current.getVertex());
            current = list.get(vertexToIndex.get(current.getBestParent().getName()));
        }
        res.getInvertedPath().add(from);

        return res;
    }

    public List<MyDijkstraVertex> dijkstra(MyVertex start, Map<String, Integer> vertexToIndex) {
        List<MyDijkstraVertex> dList = new ArrayList<>();
        MyDijkstraVertex currentNode = new MyDijkstraVertex(new MyVertex(""));

        //Initialization
        for (int i = 0; i < vertices.size(); i++) {
            MyDijkstraVertex dijkstraVertex = new MyDijkstraVertex(vertices.get(i));
            if (dijkstraVertex.getVertex().getName() == start.getName()) {
                dijkstraVertex.setDistFromSource(0);
                currentNode = dijkstraVertex;
            }
            vertexToIndex.put(dijkstraVertex.getVertex().getName().toString(), i);
            dList.add(dijkstraVertex);
        }

        while (currentNode != null) {
            currentNode.setVisited(true);
            for (int i = 0; i < currentNode.getVertex().getAdjacentVertices().size(); i++) {
                MyEdge branch = (MyEdge) currentNode.getVertex().getAdjacentVertices().get(i);
                MyDijkstraVertex branchNode = dList.get(vertexToIndex.get(((MyEdge) currentNode.getVertex().getAdjacentVertices().get(i)).getDestination().getName()));

                if (branchNode.getDistFromSource() > currentNode.getDistFromSource() + branch.getWeight()) {
                    branchNode.setDistFromSource(currentNode.getDistFromSource() + branch.getWeight());
                    branchNode.setBestParent(currentNode.getVertex());
                    dList.set(vertexToIndex.get(((MyEdge) currentNode.getVertex().getAdjacentVertices().get(i)).getDestination().getName()), branchNode);
                }
            }

            MyDijkstraVertex init = new MyDijkstraVertex(null, true, Double.POSITIVE_INFINITY, null);
            for (int i = 0; i < dList.size(); i++) {
                if (init.getDistFromSource() > dList.get(i).getDistFromSource() && !dList.get(i).isVisited()) {
                    init = dList.get(i);
                }
            }
            if (init.getDistFromSource() != Double.POSITIVE_INFINITY) {
                currentNode = init;
            } else {
                currentNode = null;
            }
        }

        return dList;
    }
    //endregion

    //region Bellman-Ford's algorithm
    public MyPathResult getShortestPathBellmanFord(MyVertex from, MyVertex to) {
        MyPathResult res = new MyPathResult();

        // Sets the runtime for the Dijkstra algorithm
        long startTimer = System.nanoTime();
        List<MyBFVertex> list = bellmanFord(from);
        res.setRuntime(System.nanoTime() - startTimer);

        // Defines the length of the path from the start vertex to the end vertex
        MyBFVertex current = list.get(mapVerticesToIndex.get(to.getName()));
        res.setLength(current.getDistFromSource());

        // Makes the inverse path (the start vertex towards the end vertex)
        while (current.getBestParent() != to && current.getBestParent() != null) {
            res.getInvertedPath().add(current.getVertex());
            current = list.get(mapVerticesToIndex.get(current.getBestParent().getName()));
        }
        res.getInvertedPath().add(from);

        return res;
    }

    public List<MyBFVertex> bellmanFord(MyVertex start) {
        String src = start.getName().toString();
        List<MyBFVertex> list = new ArrayList<>();

        for (int i = 0; i < this.nbVertices; i++) {
            list.add(new MyBFVertex(vertices.get(i)));
        }
        list.get(mapVerticesToIndex.get(src)).setDistFromSource(0);

        for (int i = 1; i <= this.nbVertices - 1; i++) {
            for (int j = 0; j < this.nbVertices; j++) {
                MyBFVertex U = list.get(j);
                int u = mapVerticesToIndex.get(U.getVertex().getName());
                for (MyEdge e : (List<MyEdge>) U.getVertex().getAdjacentVertices()) {
                    int v = mapVerticesToIndex.get(e.getDestination().getName());
                    double w = e.getWeight();
                    if (list.get(u).getDistFromSource() + w < list.get(v).getDistFromSource()) {
                        list.get(v).setDistFromSource(list.get(u).getDistFromSource() + w);
                        ;
                        list.get(v).setBestParent(vertices.get(u));
                    }
                }
            }
        }
        return list;
    }
    //endregion

    //region Floyd-Warshall's algorithm
    public MySquareMatrix floydWarshall() {
        MySquareMatrix W = this.getAdjacencyMatrix();
        int N = W.getValues().length;
        MySquareMatrix P = new MySquareMatrix(N);

        //Set the matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                P.setValue(i,j,Integer.MAX_VALUE);
                if (W.getValue(i, j) == 0 && i != j) {
                    W.setValue(i, j, Integer.MAX_VALUE);
                }
                else if(i == j) {
                    P.setValue(i,i,i);
                }
            }
        }

        //Execute Floyd-Warshall's algorithm
        for (int k = 0; k < W.getValues().length; k++) {
            for (int i = 0; i < W.getValues().length; i++) {
                for (int j = 0; j < W.getValues().length; j++) {
                    if (W.getValue(i, k) == Integer.MAX_VALUE || W.getValue(k, j) == Integer.MAX_VALUE) {
                        continue;
                    }

                    W.setValue(i, j, Math.min(W.getValue(i, j), W.getValue(i, k) + W.getValue(k, j)));
                    P.setValue(i,j, P.getValue(k,j));
                }
            }
        }
        //MySquareMatrix.print(P);
        return W;
    }
    //endregion

    //region Getters and Setters
    public MySquareMatrix getAdjacencyMatrix() {
        MySquareMatrix res = new MySquareMatrix(vertices.size());
        for (int i = 0; i < vertices.size(); i++) {
            MyVertex myVertex = vertices.get(i);
            for (int j = 0; j < myVertex.getAdjacentVertices().size(); j++) {
                res.setValue(i, mapVerticesToIndex.get(((MyEdge) myVertex.getAdjacentVertices().get(j)).getDestination().getName()), 1);
            }
        }
        return res;
    }

    public static Queue<MyVertex> listToQueue(List<MyEdge> listVertices) {
        Queue<MyVertex> q = new LinkedList<>();
        for (int i = 0; i < listVertices.size(); i++) {
            q.add(listVertices.get(i).getDestination());
        }
        return q;
    }

    public List<MyVertex> getVertices() {
        return vertices;
    }

    public MyVertex getVertex(int i) {
        return this.vertices.get(i);
    }

    public MyVertex getVertex(String name) {
        return this.vertices.get(mapVerticesToIndex.get(name));
    }

    //endregion

}
