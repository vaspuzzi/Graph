package graph.alg;

import graph.modello.AbstractEdge;
import graph.modello.AbstractVertex;
import graph.modello.impl.AdjListGraph;

public class TransitiveClosure <V extends AbstractVertex, E extends AbstractEdge> {
	private AdjListGraph<V, E> ag;
	
	/**
	 * A int matrix NxN (N is the size of vertices list) that assumes values:
	 * <ul>
	 * 		<li>1 if vertex i has a path to vertex j (i and j are the indexes of vertices list)</li>
	 * 		<li>0 otherwise</li>
	 * </ul>
	 */
	private int[][] closureMatrix;
	
	/**
	 * Fills closureMatrix with 0
	 * @param ag a {@link AdjListGraph}
	 */
	public TransitiveClosure(AdjListGraph<V, E> ag) {
		this.ag = ag;
		
		int size = ag.getVerticesList().size();
		
		closureMatrix = new int[size][size];
		
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++)
				closureMatrix[i][j] = 0;
		
	}
	
	/**
	 * Calls the recursive function {@link #dfsVisit(AbstractVertex, AbstractVertex) dfsVisit(V,V)} that modifies the closure matrix.
	 * @return modified {@link #closureMatrix}
	 */
	public int[][] getClosure() {
		for(V v : ag.getVerticesList())
			dfsVisit(v,v);
		
		return closureMatrix;
	}

	/**
	 * Initially called with v1 = v2, sets to 1 closureMatrix[v1][v2] and recursively calls itself on the adjacency list of v2 (dfsVisit(v1,v)). 
	 * 
	 * @param v1 first vertex	
	 * @param v2 second vertex
	 */
	private void dfsVisit(V v1, V v2) {
		int indexV1 = ag.getVerticesList().indexOf(v1);
		int indexV2 = ag.getVerticesList().indexOf(v2);
		
		closureMatrix[indexV1][indexV2] = 1;
		
		for(V v : ag.getAdjList().get(v2)) {
			int indexV = ag.getVerticesList().indexOf(v);
			if(closureMatrix[indexV1][indexV] == 0) 
				dfsVisit(v1,v);
			
		}
			
	}

}
