package dijkstra;

import java.util.HashSet;
import java.util.Set;

import graph.Graph;
import graph.Vertex;
import graph.GraphException;
import heap.MinHeap;
import heap.MinHeapException;

public class Dijkstra {

    public static <T extends Comparable<T>, S extends Comparable<S>>Set<Vertex<T, S>> dijkstra(Graph<T, S> graph, T srcLabel)
            throws MinHeapException, GraphException {
    	
    	Set<Vertex<T, S>> S = new HashSet<>(); 
    	GenericComparator<T, S> comparator = new GenericComparator<>();
        MinHeap<Vertex<T, S>> heap = new MinHeap<>(comparator); 
        
        Vertex<T, S> src = graph.getVertex(srcLabel); 

        for(Vertex<T, S> v : graph.getAllVertex()) { 
            if(!v.equals(src)) {
                v.setDistance(Double.MAX_VALUE);
                v.setPi(null);
            }
            heap.insert(v); 
        }
        
        src.setDistance(0);
        S.add(src); 

        while(!heap.isEmpty()) {
            Vertex<T, S> u = heap.remove(); // EXTRACT-MIN
            S.add(u); 
            for(Vertex<T, S> v : graph.getAdjacentVertex(u.getLabel())) { 
                if(v.getDistance() > (u.getDistance() + u.getEdgeWeight(v.getLabel())) && u.getDistance() != Double.MAX_VALUE) { // RELAX
                    Vertex<T, S> tmp = v; 
                    v.setDistance(u.getDistance() + u.getEdgeWeight(v.getLabel()));
                    v.setPi(u);
                    heap.decrease(tmp, v); // DECREASE-KEY
                }
            }
        }

        return S;
    }

}

