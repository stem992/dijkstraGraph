package graph;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Set;

public class Vertex<T extends Comparable<T>, S extends Comparable<S>>  {
    private T label;
    private Hashtable<T, Edge<S>> hashadj;
    private double distance = 0;
    private Vertex<T, S> pi; /* pi-greco to dijkstra */

    public void setPi(Vertex<T, S> pi){this.pi = pi;}
    public Vertex<T, S> getPi(){return pi;}

    public Vertex(T label) {
        hashadj = new Hashtable<>();
        this.label = label;
    }
    
    public Vertex(T label, double distance) {
        hashadj = new Hashtable<>();
        this.label = label;
        this.distance = distance;
    }

    public Vertex(T label, double distance, Vertex<T, S> pi) {
        hashadj = new Hashtable<>();
        this.label = label;
        this.distance = distance;
        this.pi = pi;
    }

    public T getLabel(){return label;}
    
    public void setDistance(double distance){this.distance = distance;}
    public double getDistance(){return distance;}
 
    public Edge<S> getEdge(T adj){return hashadj.get(adj);}

    public Set<Edge<S>> getEdges() {
        Set<Edge<S>> ris = new HashSet<>();
        Enumeration<Edge<S>> edge = hashadj.elements();

        while(edge.hasMoreElements()) {
            Edge<S> e = edge.nextElement();
            ris.add(e);
        }

        return ris;
    }

    public boolean addAdjacent(T adj, S label) {
        Edge<S> edge = new Edge<>(label);
        return (hashadj.putIfAbsent(adj, edge) == null);
    }

    public Set<T> getAdjacentVerticesLabel() {
        Set<T> adjacent = new HashSet<>();
        for(T a : hashadj.keySet()) {adjacent.add(a);}
        return adjacent;
    }

    public double getEdgeWeight(T adjacentLabel) {
        Edge<S> edge = getEdge(adjacentLabel);
        return edge.getWeight();
    }

    @Override
    public String toString(){return "{" + label + "}";}

    public void remove(T dest){}

    public int size(){return 0;}

    public String adjacentsToString(){return hashadj.toString();}

    public Object get(int i){return null;}

}
