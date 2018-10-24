package graph.modello.impl;

import java.util.List;

import graph.modello.AbstractEdge;
import graph.modello.AbstractVertex;

public class MatrixGraph<V extends AbstractVertex, E extends AbstractEdge> extends BaseGraph<V, E> {
	/**
	 * A int matrix of size NxN (where N is the number of vertices in the graph). <p>
	 * adjMatrix[i][j] assumes value:
	 * <ul>
	 * 		<li>1 if there is an edge with source i and target j (i and j are the indexes of the vertices list)</li>
	 * 		<li>0 otherwise </li>
	 * </ul>
	 */
	private int [][] adjMatrix;	
	private int n = vertices.size();
	
	/**
	 * Initialize vertices list (calling {@link BaseGraph#BaseGraph(List)}) and the adjacency matrix (calling {@link #buildAdjacencyMatrixFromEdges()})
	 * @param edges a list of edges
	 */
	public MatrixGraph(List<E> edges) {
		super(edges);
		
		adjMatrix = new int[n][n];
		
		buildAdjacencyMatrixFromEdges();
	}
	
	
	/**
	 * Support function that initialize {@link #adjMatrix}
	 */
	private void buildAdjacencyMatrixFromEdges() {
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				if(getEdge(vertices.get(i), vertices.get(j)) != null)
					adjMatrix[i][j] = 1;
				else
					adjMatrix[i][j] = 0;
	}
	
	/**
	 * Getter for {@link #adjMatrix}
	 * @return Adjacency Matrix
	 */
	public int[][] getAdjacencyMatrix() {
		return adjMatrix;
	}

}
