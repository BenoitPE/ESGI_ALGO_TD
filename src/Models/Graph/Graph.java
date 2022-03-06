package Models.Graph;
import java.util.*;

public class Graph {
    private List<Vertex> vertices;
    private final Map<String, Integer> mapVerticesToIndex;
    private int nbVertices;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.mapVerticesToIndex = new HashMap<>();
        this.nbVertices = 0;
    }

    public void setVertices(Map<String, Map<String, Double>> vertices) {
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
            for (String to : vertices.get(from).keySet()) {
                listVertices.get(mapVerticesToIndex.get(from)).setAdjacent(
                        new Edge(
                        listVertices.get(mapVerticesToIndex.get(to)),
                        vertices.get(from).get(to))
                );
            }
        }

        this.vertices = listVertices;
    }

    public Result getPathModifiedDepthCourse(Vertex from, Vertex to) {
        Result res = new Result("Modified depth course");

        long startTimer = System.nanoTime();
        List<Vertex> list = new LinkedList<>();
        modifiedDepthCourse(from, to, list, new LinkedList<>());
        res.setRuntime(System.nanoTime() - startTimer);
        res.getInvertedPath().addAll(list);

        double pathLength = 0;
        for(int i=res.getInvertedPath().size() -1; i >0 ; i--) {
            Vertex v = res.getInvertedPath().get(i);

            for(Edge adj : v.getAdjacents()) {
                int temp = i - 1 ;
                if(adj.getDest() == res.getInvertedPath().get(temp)) {
                    pathLength += adj.getWeight();
                }
            }
        }
        res.setComplexity("""
                <html><body>
                |V| : number of vertices<br>
                |E| : number of edges<br><br>
                O(|V| + |E|)""");
        res.setLength(pathLength);
        res.setPathWeight(list.size());

        return res;
    }

    public static boolean modifiedDepthCourse(Vertex T, Vertex search, List<Vertex> invertedPath, List<Vertex> visitedVertices) {
        if (Objects.equals(T.getName(), search.getName())) {
            invertedPath.add(T);
            return true;
        } else if (!visitedVertices.contains(T)) {
            visitedVertices.add(T);
            for (int i = 0; i < T.getAdjacents().size(); i++) {
                if (modifiedDepthCourse(T.getAdjacents().get(i).getDest(), search, invertedPath, visitedVertices)) {
                    invertedPath.add(T);
                    return true;
                }
            }
        }
        return false;
    }

    //region Dijkstra's algorithm
    public Result getShortestPathDijkstra(Vertex from, Vertex to) {
        Result res = new Result("Dijkstra");
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
        res.setComplexity("""
                <html><body>
                |V| : number of vertices<br>
                |E| : number of edges<br><br>
                O(|V| log |V| + |E|)""");
        res.getInvertedPath().add(from);
        res.setPathWeight(res.getInvertedPath().size());

        return res;
    }

    public List<VertexDijkstra> dijkstra(Vertex start, Map<String, Integer> vertexToIndex) {
        List<VertexDijkstra> dList = new ArrayList<>();
        VertexDijkstra currentNode = new VertexDijkstra(new Vertex(""));

        //Initialization
        for (int i = 0; i < vertices.size(); i++) {
            VertexDijkstra dijkstraVertex = new VertexDijkstra(vertices.get(i));
            if (Objects.equals(dijkstraVertex.getVertex().getName(), start.getName())) {
                dijkstraVertex.setDistFromSource(0);
                currentNode = dijkstraVertex;
            }
            vertexToIndex.put(dijkstraVertex.getVertex().getName(), i);
            dList.add(dijkstraVertex);
        }

        while (currentNode != null) {
            currentNode.setVisited(true);
            for (int i = 0; i < currentNode.getVertex().getAdjacents().size(); i++) {
                Edge branch = currentNode.getVertex().getAdjacents().get(i);
                VertexDijkstra branchNode = dList.get(vertexToIndex.get(currentNode.getVertex().getAdjacents().get(i).getDest().getName()));

                if (branchNode.getDistFromSource() > currentNode.getDistFromSource() + branch.getWeight()) {
                    branchNode.setDistFromSource(currentNode.getDistFromSource() + branch.getWeight());
                    branchNode.setBestParent(currentNode.getVertex());
                    dList.set(vertexToIndex.get(currentNode.getVertex().getAdjacents().get(i).getDest().getName()), branchNode);
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
        Result res = new Result("Bellman-Ford");

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
        res.setComplexity("""
                <html><body>
                |V| : number of vertices<br>
                |E| : number of edges<br><br>
                O(|V| * |E|)""");
        res.getInvertedPath().add(from);
        res.setPathWeight(res.getInvertedPath().size());

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

                for (Edge e : U.getVertex().getAdjacents()) {
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

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(String name) {
        return this.vertices.get(mapVerticesToIndex.get(name));
    }

}
