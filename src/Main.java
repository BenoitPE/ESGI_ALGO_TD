import Models.Graph.Graph;

import java.util.*;

public class Main {
    public static void main(String[] args) {

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

        Graph graph = new Graph();
        graph.setVertices(graphValues);
    }


}