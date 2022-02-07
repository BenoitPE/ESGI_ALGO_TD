package Models.Graph;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private final List<Vertex> invertedPath;
    private double length;
    private long runtime;

    public Result() {
        this.invertedPath = new ArrayList<>();
        this.length = 0;
        this.runtime = 0;
    }

    public List<Vertex> getInvertedPath() {
        return invertedPath;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public static List<String> getEdgesName(List<Vertex> vertices) {
        List<String> res= new ArrayList<>();
        String startingVertex;
        String endingVertex;
        for (int i = vertices.size() - 1; i > 0; i-- ) {
            startingVertex = vertices.get(i).getName();
            endingVertex = vertices.get(i-1).getName();
            res.add(startingVertex + "-" + endingVertex);
        }
        return res;
    }
}
