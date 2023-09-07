package graph;

/*
 * THIS METHOD IS THE CONSTRUCTOR OF THE EDGES
 * @param label: label associated with a pair of edges
 * @param weight: generic weight of the edge
 *
 */

public class Edge<S> {

    private S label;
    private double weight = 0;

    public Edge(S label) {
    	this.label = label;
    }
    
    public Edge(double weight) {
    	this.weight = weight;
    }
    
    public Edge(S label, double weight) {
    	this.label = label;
    	this.weight = weight;
    }

    public void setWeight(double weight) {
    	this.weight = weight;
    }

    public double getWeight() {
    	return weight;
    }

    public void setLabel(S label) {
    	this.label = label;
    }
    
    public S getLabel() {
    	return label;
    }

    @Override
    public String toString(){return "" + this.weight;}
    
}
