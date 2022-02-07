package Models.Graph;

import java.util.ArrayList;
import java.util.List;

public class MyPathResult {

    private List<MyVertex> invertedPath;
    private double length;
    private long runtime;

    public MyPathResult() {
        this.invertedPath = new ArrayList<>();
        this.length = 0;
        this.runtime = 0;
    }

    public List<MyVertex> getInvertedPath() {
        return invertedPath;
    }

    public void setInvertedPath(List<MyVertex> invertedPath) {
        this.invertedPath = invertedPath;
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

    public static List<String> getEdgesName(List<MyVertex> vertices) {
        List<String> res= new ArrayList<>();
        String startingVertex = "";
        String endingVertex = "";
        for (int i = vertices.size() - 1; i > 0; i-- ) {
            startingVertex = vertices.get(i).getName().toString();
            endingVertex = vertices.get(i-1).getName().toString();
            res.add(startingVertex + "-" + endingVertex);
        }
        return res;
    }

    public static List<String> getNodesName(List<MyVertex> vertices) {
        List<String> res = new ArrayList<>();
        for (int i = vertices.size() - 1; i > 0; i-- ) {
            res.add(vertices.get(i).getName().toString());
        }
        return res;
    }
}
