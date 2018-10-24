package graph.alg;

import java.util.HashMap;
import java.util.Map;

import graph.modello.AbstractVertex;
import graph.modello.impl.AdjListGraph;
import graph.modello.impl.WeightedEdge;

public class FloydWarshall<V extends AbstractVertex, E extends WeightedEdge> {
	private static final int POSITIVE_INFINITY = 9999999;

	AdjListGraph<V, E> ag;
	
	Map<V,Integer> vertexIndexes;
	
	int[][] weightMatrix;
	
	public FloydWarshall(AdjListGraph<V, E> ag) {
		this.ag = ag;
		
		int n = ag.getVerticesList().size();
		
		vertexIndexes = new HashMap<>();
		weightMatrix = new int[n][n];
		
		//initialize vertexIndexes
		for(int i=0; i<n;i++) 
			vertexIndexes.put(ag.getVerticesList().get(i), i);
			
		//initialize weightMatrix
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				if(i==j)
					weightMatrix[i][j] = 0;
				else
					weightMatrix[i][j] = POSITIVE_INFINITY;
				
		for(V x: ag.getVerticesList())
			for(V y : ag.getAdjList().get(x))
				weightMatrix[vertexIndexes.get(x)][vertexIndexes.get(y)] = ag.getEdge(x, y).getWeight();
			
	}

}
