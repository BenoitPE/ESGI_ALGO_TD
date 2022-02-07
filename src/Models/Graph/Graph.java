package Models.Graph;

import java.util.*;

public class Graph {
    private List<Vertex> vertices;
    private final Map<String, Integer> mapVerticesToIndex;
    private int nbEdges;
    private int nbVertices;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.mapVerticesToIndex = new HashMap<>();
        this.nbEdges = 0;
        this.nbVertices = 0;
    }

    public void setVertices(Map<String, String[]> vertices) {
        List<Vertex> listVertices = new ArrayList<>();

        //Initialize all vertices
        int i = 0;
        for (String vertexName : vertices.keySet()) {
            listVertices.add(new Vertex(vertexName));
            nbVertices++;
            mapVerticesToIndex.put(vertexName, i);
            i++;
        }

        //Adds adjacent vertices
        for (String from : vertices.keySet()) {
            for (String to : vertices.get(from)) {
                listVertices.get(mapVerticesToIndex.get(from)).setAdjacent(new Edge(listVertices.get(mapVerticesToIndex.get(to))));
                nbEdges++;
            }
        }

        this.vertices = listVertices;
    }

    //region Width and Depth Courses
    public static Queue<Vertex> widthCourse(Vertex startingVertex) {
        Queue<Vertex> resultQueue = new LinkedList<>();
        Queue<Vertex> queue = listToQueue(startingVertex.getAdjacents());
        List<Vertex> visitedVerticesList = new ArrayList<>();
        visitedVerticesList.add(startingVertex);
        resultQueue.add(startingVertex);

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.remove();
            if (!visitedVerticesList.contains(currentVertex)) {
                visitedVerticesList.add(currentVertex);
                resultQueue.add(currentVertex);

                for (int i = 0; i < currentVertex.getAdjacents().size(); i++) {
                    queue.add(((Edge) currentVertex.getAdjacents().get(i)).getDest());
                }
            }
        }
        return resultQueue;
    }

    public static List<Vertex> depthCourse(Vertex T, List<Vertex> result, List<Vertex> visitedVertices) {
        if (!visitedVertices.contains(T)) {
            result.add(T);
            visitedVertices.add(T);
            for (int i = 0; i < T.getAdjacents().size(); i++) {
                depthCourse(((Edge) T.getAdjacents().get(i)).getDest(), result, visitedVertices);
            }
        }
        return result;
    }

    public static boolean depthCoursePoint(Vertex T, Vertex search, List<Vertex> invertedPath, List<Vertex> visitedVertices) {
        if (T.getName() == search.getName()) {
            invertedPath.add(T);
            return true;
        } else if (!visitedVertices.contains(T)) {
            visitedVertices.add(T);
            for (int i = 0; i < T.getAdjacents().size(); i++) {
                if (depthCoursePoint(((Edge) T.getAdjacents().get(i)).getDest(), search, invertedPath, visitedVertices)) {
                    invertedPath.add(T);
                    return true;
                }
            }
        }
        return false;
    }
    //endregion

    //region Dijkstra's algorithm
    public Result getShortestPathDijkstra(Vertex from, Vertex to) {
        Result res = new Result();
        Map<String, Integer> vertexToIndex = new HashMap<>();

        // Sets the runtime for the Dijkstra algorithm
        long startTimer = System.nanoTime();
        List<VertexDijkstra> list = dijkstra(from, vertexToIndex);
        res.setRuntime(System.nanoTime() - startTimer);

        // Defines the length of the path from the start vertex to the end vertex
        VertexDijkstra current = list.get(vertexToIndex.get(to.getName()));
        res.setLength(current.getDistFromSource());

        // Makes the inverse way (the start vertex towards the end vertex)
        while (current.getBestParent() != to && current.getBestParent() != null) {
            res.getInvertedPath().add(current.getVertex());
            current = list.get(vertexToIndex.get(current.getBestParent().getName()));
        }
        res.getInvertedPath().add(from);

        return res;
    }

    public List<VertexDijkstra> dijkstra(Vertex start, Map<String, Integer> vertexToIndex) {
        List<VertexDijkstra> dList = new ArrayList<>();
        VertexDijkstra currentNode = new VertexDijkstra(new Vertex(""));

        //Initialization
        for (int i = 0; i < vertices.size(); i++) {
            VertexDijkstra dijkstraVertex = new VertexDijkstra(vertices.get(i));
            if (dijkstraVertex.getVertex().getName() == start.getName()) {
                dijkstraVertex.setDistFromSource(0);
                currentNode = dijkstraVertex;
            }
            vertexToIndex.put(dijkstraVertex.getVertex().getName().toString(), i);
            dList.add(dijkstraVertex);
        }

        while (currentNode != null) {
            currentNode.setVisited(true);
            for (int i = 0; i < currentNode.getVertex().getAdjacents().size(); i++) {
                Edge branch = (Edge) currentNode.getVertex().getAdjacents().get(i);
                VertexDijkstra branchNode = dList.get(vertexToIndex.get(((Edge) currentNode.getVertex().getAdjacents().get(i)).getDest().getName()));

                if (branchNode.getDistFromSource() > currentNode.getDistFromSource() + branch.getWeight()) {
                    branchNode.setDistFromSource(currentNode.getDistFromSource() + branch.getWeight());
                    branchNode.setBestParent(currentNode.getVertex());
                    dList.set(vertexToIndex.get(((Edge) currentNode.getVertex().getAdjacents().get(i)).getDest().getName()), branchNode);
                }
            }

            VertexDijkstra init = new VertexDijkstra(null, true, Double.POSITIVE_INFINITY, null);
            for (VertexDijkstra v : dList) {
                if (init.getDistFromSource() > v.getDistFromSource() && !v.isVisited()) {
                    init = v;
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
    public Result getShortestPathBellmanFord(Vertex from, Vertex to) {
        Result res = new Result();

        // Sets the runtime for the Dijkstra algorithm
        long startTimer = System.nanoTime();
        List<VertexBF> list = bellmanFord(from);
        res.setRuntime(System.nanoTime() - startTimer);

        // Defines the length of the path from the start vertex to the end vertex
        VertexBF current = list.get(mapVerticesToIndex.get(to.getName()));
        res.setLength(current.getDistFromSource());

        // Makes the inverse path (the start vertex towards the end vertex)
        while (current.getBestParent() != to && current.getBestParent() != null) {
            res.getInvertedPath().add(current.getVertex());
            current = list.get(mapVerticesToIndex.get(current.getBestParent().getName()));
        }
        res.getInvertedPath().add(from);

        return res;
    }

    public List<VertexBF> bellmanFord(Vertex start) {
        String src = start.getName();
        List<VertexBF> list = new ArrayList<>();

        for (int i = 0; i < this.nbVertices; i++) {
            list.add(new VertexBF(vertices.get(i)));
        }
        list.get(mapVerticesToIndex.get(src)).setDistFromSource(0);

        for (int i = 1; i <= this.nbVertices - 1; i++) {
            for (int j = 0; j < this.nbVertices; j++) {
                VertexBF U = list.get(j);
                int u = mapVerticesToIndex.get(U.getVertex().getName());
                for (Edge e : (List<Edge>) U.getVertex().getAdjacents()) {
                    int v = mapVerticesToIndex.get(e.getDest().getName());
                    double w = e.getWeight();
                    if (list.get(u).getDistFromSource() + w < list.get(v).getDistFromSource()) {
                        list.get(v).setDistFromSource(list.get(u).getDistFromSource() + w);
                        list.get(v).setBestParent(vertices.get(u));
                    }
                }
            }
        }
        return list;
    }
    //endregion

    //region Floyd-Warshall's algorithm
    public Matrix floydWarshall() {
        Matrix W = this.getAdjacencyMatrix();
        int N = W.getValues().length;
        Matrix P = new Matrix(N);

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
        //Matrix.print(P);
        return W;
    }
    //endregion

    //region Getters and Setters
    public Matrix getAdjacencyMatrix() {
        Matrix res = new Matrix(vertices.size());
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            for (int j = 0; j < vertex.getAdjacents().size(); j++) {
                res.setValue(i, mapVerticesToIndex.get((vertex.getAdjacents().get(j)).getDest().getName()), 1);
            }
        }
        return res;
    }

    public static Queue<Vertex> listToQueue(List<Edge> listVertices) {
        Queue<Vertex> q = new LinkedList<>();
        for (Edge listVertex : listVertices) {
            q.add(listVertex.getDest());
        }
        return q;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(int i) {
        return this.vertices.get(i);
    }

    public Vertex getVertex(String name) {
        return this.vertices.get(mapVerticesToIndex.get(name));
    }

    //endregion

}
