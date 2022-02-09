package Models.Graph;

import scala.Int;
import scala.util.parsing.combinator.testing.Str;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private String name;
    private final List<Vertex> invertedPath;
    private double length;
    private long runtime;
    private String complexity;
    private String realComplexity;
    private Integer nbVertices;
    private Integer pathWeight;
    private String details;

    public Result(String name) {
        this.name = name;
        this.invertedPath = new ArrayList<>();
        this.length = 0;
        this.runtime = 0;
        this.complexity = "Not implemented";
        this.realComplexity = "Not implemented";
        this.nbVertices = 0;
        this.pathWeight = 0;
        this.details = "";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getRealComplexity() {
        return realComplexity;
    }

    public void setRealComplexity(String realComplexity) {
        this.realComplexity = realComplexity;
    }

    public Integer getNbVertices() {
        return nbVertices;
    }

    public void setNbVertices(Integer nbVertices) {
        this.nbVertices = nbVertices;
    }

    public Integer getPathWeight() {
        return pathWeight;
    }

    public void setPathWeight(Integer pathWeight) {
        this.pathWeight = pathWeight;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public static List<String> getEdgesName(List<Vertex> vertices) {
        List<String> res = new ArrayList<>();
        String startingVertex;
        String endingVertex;
        for (int i = vertices.size() - 1; i > 0; i--) {
            startingVertex = vertices.get(i).getName();
            endingVertex = vertices.get(i - 1).getName();
            res.add(startingVertex + "-" + endingVertex);
        }
        return res;
    }
}
