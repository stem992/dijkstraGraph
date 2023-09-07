package graph;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GraphDirectTest {
    private Graph<Integer, Character> graphdirect;
    private Graph<Integer, Character> graphundirect;
    private int vertex1, vertex2, vertex3, vertex4, vertex5;
    
    @Before
    public void createGraph() {
    	graphdirect = new Graph<>(true);
    	graphundirect = new Graph<>(false);
        vertex1 = 7;
        vertex2 = 2;
        vertex3 = 9;
        vertex4 = 22;
        vertex5 = 10;
    }

    @Test
    public void DirectedTest() {
    	try {
    		assertTrue(graphdirect.isDirect());
    		assertFalse(graphundirect.isDirect());
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }
     
    @Test
    public void AddVertexTest() throws Exception {
    	try {
    		assertTrue(graphdirect.addVertex(vertex1));
    		assertTrue(graphundirect.addVertex(vertex1));
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }     
    }
    
    @Test
    public void AddTwoVertexTest() throws Exception {
    	try {
            assertTrue(graphdirect.addVertex(vertex1));
            assertTrue(graphdirect.addVertex(vertex2));
            assertTrue(graphundirect.addVertex(vertex1));
            assertTrue(graphundirect.addVertex(vertex2));
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }

    @Test
    public void AddAllVertexDirectedTest() throws Exception {
    	try {
            assertTrue(graphdirect.addVertex(vertex1));
            assertTrue(graphdirect.addVertex(vertex2));
            assertTrue(graphdirect.addVertex(vertex3));
            assertTrue(graphdirect.addVertex(vertex4));
            assertTrue(graphdirect.addVertex(vertex5));
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }
    
    @Test
    public void AddAllVertexUndirectedTest() throws Exception {
    	try {
            assertTrue(graphundirect.addVertex(vertex1));
            assertTrue(graphundirect.addVertex(vertex2));
            assertTrue(graphundirect.addVertex(vertex3));
            assertTrue(graphundirect.addVertex(vertex4));
            assertTrue(graphundirect.addVertex(vertex5));
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }
    
    @Test
    public void EmptyNumberTest() throws Exception {
    	try {
    		assertEquals(graphdirect.getNumberVertex(), 0);
    		assertEquals(graphundirect.getNumberVertex(), 0);
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }
    
    @Test
    public void RemoveVertexTest() throws Exception {
    	try {
    		graphdirect.addVertex(vertex1);
    		graphdirect.addVertex(vertex2);
    		assertTrue(graphdirect.containsVertex(vertex2));
    		graphdirect.removeVertex(vertex2);
    		assertFalse(graphdirect.containsVertex(vertex2));
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }
    
    @Test
    public void RemoveEdgeTest() throws Exception {
    	try {
    		graphdirect.addVertex(vertex1);
    		graphdirect.addVertex(vertex2);
    		graphdirect.addEdge(vertex1, vertex2, 'A');
    		assertTrue(graphdirect.containsEdge(vertex1, vertex2));
    		graphdirect.removeEdge(vertex1, vertex2);
    		graphdirect.removeVertex(vertex1);
    		graphdirect.removeVertex(vertex2);
    		assertFalse(graphdirect.containsEdge(vertex1, vertex2));
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }
    
    @Test
    public void AllVertexTest() throws Exception {
    	try {
    		graphdirect.addVertex(vertex1);
    		graphdirect.addVertex(vertex2);
    		graphdirect.addVertex(vertex3);
    		graphdirect.addVertex(vertex4);
    		graphdirect.addVertex(vertex5);
    		graphundirect.addVertex(vertex1);
    		graphundirect.addVertex(vertex2);
    		graphundirect.addVertex(vertex3);
    		graphundirect.addVertex(vertex4);
    		graphundirect.addVertex(vertex5);
    		assertEquals(graphdirect.getNumberVertex(),5);
    		assertEquals(graphundirect.getNumberVertex(),5);
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }
    
    @Test
    public void ContainsFalseTest() throws Exception { 
    	try {
    		assertFalse(graphdirect.containsVertex(vertex1));
    		assertFalse(graphdirect.containsVertex(vertex1));
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }
    
    @Test
    public void AddEdgeTest() throws Exception {
    	try {
    		graphdirect.addVertex(vertex1);
    		graphdirect.addVertex(vertex2);
    		graphundirect.addVertex(vertex1);
    		graphundirect.addVertex(vertex2);
    		graphdirect.addEdge(vertex1, vertex2, 'A');
    		graphundirect.addEdge(vertex1, vertex2, 'B');
    	}
    	catch (Exception e) {
    		System.err.println(e.toString());
    	 }  
    }
    

  
}
