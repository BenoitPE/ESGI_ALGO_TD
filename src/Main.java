import Models.Graph.Graph;

import java.util.*;

import static javax.swing.UIManager.put;

public class Main {
    public static void main(String[] args) {

        Map<String, String[]> graphValues = new LinkedHashMap<>();
        //graphValues.put("Annecy", new String[]{"La Roche-sur-Foron"});
        //graphValues.put("Annemasse", new String[]{"Genève", "La Roche-sur-Foron"});
        //graphValues.put("Bellegarde", new String[]{"Meyrin", "Seyssel"});
        //graphValues.put("Coppet", new String[]{"Les Tuileries", "Nyon"});
        //graphValues.put("Evian-les-Bains", new String[]{"Thonon-les-Bains"});
        //graphValues.put("Genève", new String[]{"Annemasse", "Les Tuileries", "Meyrin"});
        //graphValues.put("La Plaine", new String[]{"Meyrin"});
        //graphValues.put("La Roche-sur-Foron", new String[]{"Annecy", "Annemasse", "St Gervais-les-Bains", "Thonon-les-Bains"});
        //graphValues.put("Les Tuileries", new String[]{"Coppet", "Genève"});
        //graphValues.put("Meyrin", new String[]{"Bellegarde", "Genève"});
        //graphValues.put("Nyon", new String[]{});
        //graphValues.put("Seyssel", new String[]{});
        //graphValues.put("St Gervais-les-Bains", new String[]{"Annecy", "La Roche-sur-Foron"});
        //graphValues.put("Thonon-les-Bains", new String[]{"Bellegarde", "La Roche-sur-Foron"});
        //Graph graph = new Graph();
        //graph.setVertices(graphValues);

        Map<String, Map<String, Double>> map = new LinkedHashMap<>();
        map.put("Annecy", new HashMap<>() {{
                    put("La Roche-sur-Foron", 33.0);
                }});
        map.put("Annemasse",
                new HashMap<>() {{
                    put("Genève", 10.0);
                    put("La Roche-sur-Foron", 19.0);
                }});
        map.put("Bellegarde", new HashMap<>() {{
            put("Meyrin", 32.0);
            put("Seyssel", 23.0);
        }});
        map.put("Coppet", new HashMap<>() {{
            put("Les Tuileries", 9.0);
            put("Nyon", 9.0);
        }});
        map.put("Evian-les-Bains", new HashMap<>() {{
            put("Thonon-les-Bains", 10.0);
        }});
        map.put("Genève", new HashMap<>() {{
            put("Annemasse", 10.0);
            put("Les Tuileries", 6.0);
            put("Meyrin", 7.0);
        }});
        map.put("La Plaine", new HashMap<>() {{
            put("Meyrin", 10.0);
        }});
        map.put("La Roche-sur-Foron", new HashMap<>() {{
            put("Annecy", 34.0);
            put("Annemasse", 22.0);
            put("St Gervais-les-Bains", 48.0);
            put("Thonon-les-Bains", 43.0);
        }});
        map.put("Les Tuileries", new HashMap<>() {{
            put("Coppet", 9.0);
            put("Genève", 7.0);
        }});
        map.put("Meyrin", new HashMap<>() {{
            put("Bellegarde", 32.0);
            put("Genève", 7.0);
        }});
        map.put("Nyon", new HashMap<>() {
        });
        map.put("Seyssel", new HashMap<>() {
        });
        map.put("St Gervais-les-Bains", new HashMap<>() {{
            put("Annecy", 87.0);
            put("La Roche-sur-Foron", 49.0);
        }});
        map.put("Thonon-les-Bains", new HashMap<>() {{
            put("Bellegarde", 86.0);
            put("La Roche-sur-Foron", 43.0);
        }});

        Graph graph = new Graph();
        graph.setVertices(map);


        for (String from : map.keySet()) {
            for (String to : map.get(from).keySet()) {
                System.out.println(from + " -> " + to + ": " + map.get(from).get(to));

            }
        }

    }


}