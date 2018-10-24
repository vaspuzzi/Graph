package graph.alg.shortestpath;

import java.util.HashMap;
import java.util.Map;

import graph.modello.AbstractVertex;
import graph.modello.impl.AdjListGraph;
import graph.modello.impl.WeightedEdge;

public class APSPMatrix <V extends AbstractVertex, E extends WeightedEdge> {
	private static final int POSITIVE_INFINITY = 99999999;

	AdjListGraph<V, E> ag;
	
	Map<V,Integer> vertexIndexes;
	
	int[][] weightMatrix;

	public APSPMatrix(AdjListGraph<V, E> ag) {
		this.ag = ag;
		
		int n = ag.getVerticesList().size();
		
		vertexIndexes = new HashMap<>();
		weightMatrix = new int[n][n];
		
		
		for(int i=0; i<n;i++) {
			vertexIndexes.put(ag.getVerticesList().get(i), i);
			
		}
		
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
	
	
	public int[][] extendShortestPath(int[][] distances, int[][] weights) {
		int n = ag.getVerticesList().size();
		int[][] result = new int[n][n];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++) {
				result[i][j] = POSITIVE_INFINITY;
				for(int k=0; k<n; k++) 
					result[i][j] = Math.min(result[i][j], distances[i][k] + weights[k][j]);
					
			}
		
		
		return result;
	}
	
	public int[][] allPairShortestPath() {
		int[][] result;
		
		int n = ag.getVerticesList().size();
		
		result = weightMatrix;
		
		int m = 1;
		
		while(n-1 > m) {
			System.out.println("m =");
			result = extendShortestPath(result, result);
			m*=2;
		}
		
		return result;
	}
}
