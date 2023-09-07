package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

import graph.Graph;
import graph.GraphException;
import graph.Vertex;
import java.io.InputStreamReader;
public class DijkstraMain {

    public static void main(String[] args) throws IOException, GraphException, DijkstraException {
        try {
        	Graph<String, String> graph = new Graph<>();
        	String path = "italian_dist_graph.csv";
		      
            loadGraph(path, graph);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the source : ");
            String source = br.readLine();
            System.out.println("Enter the destination : ");
            String destination = br.readLine();
            
            long timestampBeforeExecution = System.currentTimeMillis();

            System.out.println("Dijkstra algorithm is working..");
            Set<Vertex<String, String>> ris = Dijkstra.dijkstra(graph, source);
          
            long timestampAfterExecution = System.currentTimeMillis();
            
            System.out.println("\nExecution ended in " + 
          		  (timestampAfterExecution - timestampBeforeExecution) + " milliseconds.\n");
            findDistance(ris, source, destination);
            
        } catch (Exception e) {
            System.err.println("CSV file loading error: " + e.getMessage());
        }
    }
    
    private static void loadGraph(String path, Graph<String, String> graph) throws IOException, GraphException, DijkstraException {
        Path input = Paths.get(path);
        System.out.println("LOADING DATA FROM FILE");
        try(BufferedReader fileInputReader = Files.newBufferedReader(input)) {
            String line = null;
            while((line = fileInputReader.readLine()) != null) {
                String[] lineElements = line.split(",");
                graph.addVertex(lineElements[0]);
                graph.addVertex(lineElements[1]);
                graph.addEdge(lineElements[0], lineElements[1], lineElements[0] + "-" + lineElements[1]);
                graph.addEdge(lineElements[1], lineElements[0], lineElements[1] + "-" + lineElements[0]);
                graph.getEdge(lineElements[0], lineElements[1]).setWeight(Double.parseDouble(lineElements[2]));
                graph.getEdge(lineElements[1], lineElements[0]).setWeight(Double.parseDouble(lineElements[2]));
            }
            fileInputReader.close();
        } catch (Exception e) {
        	System.err.println(e.toString());
        }
        
        System.out.println("\nDATA LOADED\n");
    }



    private static void findDistance(Set<Vertex<String, String>> ris, String srcLabel, String dstLabel) throws IOException, GraphException, DijkstraException {
        try {
        	Iterator<Vertex<String, String>> i = ris.iterator();
            while (i.hasNext()) {
                Vertex<String, String> v = i.next();
                if (v.getLabel().equals(dstLabel)) {
                    System.out.format("Distance between %s and %s ~%.2f Km\n\n", srcLabel, dstLabel, (v.getDistance() / 1000));

                    return;
                }
            }
        } catch (Exception e) {
        	System.err.println(e.toString());
        }
        
    }

}
