package graph;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Graph<T extends Comparable<T>, S extends Comparable<S>>  {
    private Hashtable<T, Vertex<T, S>> hash;
    public boolean direct;
    public boolean undirect;
    
    public Graph(){hash = new Hashtable<>();}

/*
 * CREATION OF AN EMPTY GRAPH
 * @param hash: the constructor of the new Hashtable
 * @param direct: used to check if the graph is direct
 * 
 */   

public Graph(boolean direct) {
	hash = new Hashtable<>();
    this.direct = direct;
}

/*
 * ADDITION OF A VERTEX
 * @param v: is TRUE if the parameter v is added to the graph, FALSE otherwise
 * 
 */

public boolean addVertex(T v) {
    if(!hash.containsKey(v)){
		Vertex<T, S> adjList = new Vertex<>(v);
		hash.put(v,adjList);
	    return true;
	} else
	    return false;
}

/*
 * ADDITION OF A EDGE
 * @param source: source node of the edge
 * @param destination: destination node of the edge
 * @param label
 * 
 */

public boolean addEdge(T source, T destination, S label) throws GraphException {
	boolean flag = hash.containsKey(source) && hash.containsKey(destination);
    if(!hash.containsKey(source))
        throw new GraphException("There is no source:" + source + " in the Graph");

    if(!hash.containsKey(destination))
        throw new GraphException("There is no destination" + destination + " in the Graph");
    
    Vertex<T, S> fromVertex = hash.get(source);

    fromVertex.addAdjacent(destination, label);

    if(!isDirect()) {
        Vertex<T, S> toVertex = hash.get(destination);
        toVertex.addAdjacent(source, label);
        flag = true;
    }
    
    return flag;
}

/*
 * CHECK IF GRAPH IS DIRECTED
 * @return TRUE if the graph is Direct, FALSE otherwise
 * 
 */

public boolean isDirect() {
    return direct;
}

/*
 * CHECK IF GRAPH IS UNDIRECTED
 * @return TRUE if the graph is Undirect, FALSE otherwise
 * 
 */

public boolean isUndirect() {
    return undirect;
}


/*
 * CHECK IF THE GRAPH CONTAINS A GIVEN VERTEX
 * @param vertexToFind: return TRUE if the element is contained in the graph, FALSE otherwise
 * 
 */

public boolean containsVertex(T vertexToFind) {
    return hash.containsKey(vertexToFind);
}

/*
 * CHECK IF THE GRAPH CONTAINS A GIVEN EDGE
 * RETURN TRUE if the edge contains source and destination is contained in the graph
 * FALSE otherwise
 * 
 */

public boolean containsEdge(T source, T destination) throws GraphException {
    if(source == null || destination == null) {
        throw new GraphException("containsEdge: source and destination parameters cannot be null");
    }

    if(!containsVertex(source) || !containsVertex(destination)){return false;}

    return true;
}

/*
 * DELETION OF A VERTEX
 * @param v: v is the element to delete
 * 
 */

public void removeVertex(T v) throws GraphException { 
    if(v == null) throw new GraphException("removeVertex: a null vertex cannot be removed");
    if(hash.containsKey(v)) {
        for(T k : hash.keySet()) { 
            if(containsEdge(k, v)) {
                removeEdge(v, k);
            }
        }
        hash.remove(v);
    } else throw new GraphException("removeVertex: vertex not found. Can't remove this vertex: " + v);
}

/*
 * DELETION OF A EDGE
 * @param source: source of the edge to be deleted
 * @param destination: destination of the edge to be deleted
 * 
 */

public void removeEdge(T source, T destination) throws GraphException {
    if(source == null || destination == null)
        throw new GraphException("Error: a null arch cannot be removed");

    if(!hash.containsKey(source) || !hash.containsKey(destination))
        throw new GraphException("Error: cannot remove an arch with a null boundary");
    
    if(containsEdge(source, destination)) {
    	hash.get(source).remove(destination);
    }
    
    if(!isDirect()) {
    	hash.get(destination).remove(source);
    }
}

/*
* DETERMINE THE NUMBER OF VERTEX
* 
*/

public int getNumberVertex() {
    return hash.size();
}

/*
* DETERMINE THE NUMBER OF EDGE
* 
*/

public int getNumberEdges() {
	int count = 0;
	for (T node : hash.keySet()) {
		Vertex<T, S> array = hash.get(node);
		count = count + array.size();
	}
	
    if(!isDirect()){count /= 2;}
    
    return count;
}

/*
 * RETRIEVING OF GRAPH VERTEX
 * @return one vertex
 * 
 */

public Vertex<T, S> getVertex(T label) {
    return hash.get(label);
}

/*
 * RETRIEVING OF GRAPH VERTEX
 * @return list of all the vertex
 * 
 */

public Set<Vertex<T, S>> getAllVertex() {
    Set<Vertex<T, S>> vertex = new HashSet<>();
    for(T label : getVertexLabel()){vertex.add(getVertex(label));}
    return vertex;
}

/*
* RETRIEVING OF GRAPH EDGE
* @return one edge
* 
*/

public Edge<S> getEdge(T vertexFrom, T vertexTo) { 
    Vertex<T, S> vertex = hash.get(vertexFrom);
    if(vertex == null){return null;}
    return vertex.getEdge(vertexTo);
}

/* 
* RETRIEVING OF GRAPH EDGE
* @return list of all the edges
* 
*/

public Set<Edge<S>> getEdges() { 
    Set<Edge<S>> edges = new HashSet<>();

    for(T label : hash.keySet()) {
        for(Edge<S> b : getVertex(label).getEdges()) {
            edges.add(b);
         }
     }

     return edges;
}

/*
* RETRIEVING ADJACENT VERTEX OF A GIVEN VERTEX V
* @return list of vertex to which the element is connected
* 
*/

public Set<Vertex<T, S>> getAdjacentVertex(T vertex) throws GraphException {
    boolean value = !(isDirect());
    if(vertex == null)
        throw new GraphException("getAdjacentVertex: node parameter cannot be null");
    if(!(containsVertex(vertex)))
        throw new GraphException("getAdjacentVertex: node parameter must exist");

    Set<T> adjVertexLabel = getAdjacentVertexLabel(vertex);

    Set<Vertex<T, S>> adjVertex = new HashSet<>();
    for(T adjlab : adjVertexLabel){
    	adjVertex.add(getVertex(adjlab));
	    if(value) {
	    	value = !(value);
	        continue;
	    }
    }
    return adjVertex;
    
}

public Set<T> getVertexLabel() {
    Set<T> vertex = new HashSet<>();
    for(T a : hash.keySet()){vertex.add(a);}
    return vertex;
}

public Set<T> getAdjacentVertexLabel(T vert) throws GraphException {
	if(!hash.containsKey(vert)){return null;}
    Vertex<T, S> vertex = hash.get(vert);
    return vertex.getAdjacentVerticesLabel();
}


}


