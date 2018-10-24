package graph.alg.print;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import graph.modello.AbstractEdge;
import graph.modello.AbstractVertex;
import graph.modello.impl.AdjListGraph;

public class GraphPrinter {
	private GraphPrinter() {}
	
	// prints vertices linked to source
	public static <V extends AbstractVertex, E extends AbstractEdge> void printBFS(AdjListGraph<V,E> g, V source) {
		System.out.println("printBFS: ");
		
		List<V> visitedVertices = new ArrayList<V>();
		
		Queue<V> q = new LinkedList<>();
		
		Map<V,List<V>> adjList = g.getAdjList();
		
		q.add(source);
		
		while(!q.isEmpty()) {
			V v = q.remove();
			
			if(!visitedVertices.contains(v))
				System.out.print(v + " ");
			visitedVertices.add(v);
			
			for(V vertex : adjList.get(v))
				if(!visitedVertices.contains(vertex))
					q.add(vertex);
		}
		
		System.out.println();
	}
	
	// prints all vertices, starting from those linked from source
	public static <V extends AbstractVertex, E extends AbstractEdge> void printBFS2(AdjListGraph<V,E> g, V source) {
		System.out.println("printBFS2: ");
		
		List<V> unvisitedVertices = new ArrayList<V>(g.getVerticesList());
		
		Queue<V> q = new LinkedList<>();
		
		Map<V,List<V>> adjList = g.getAdjList();
		
		q.add(source);
		
		while(!q.isEmpty() || !unvisitedVertices.isEmpty()) {
			if(q.isEmpty())
				q.add(unvisitedVertices.get(0));
				
			V v = q.remove();
			
			if(unvisitedVertices.contains(v))
				System.out.print(v + " ");
			unvisitedVertices.remove(v);
			
			for(V vertex : adjList.get(v))
				if(unvisitedVertices.contains(vertex))
					q.add(vertex);
		}
		
		System.out.println();
		
	}
	
	// prints vertices linked to source
	public static <V extends AbstractVertex, E extends AbstractEdge> void printDFS(AdjListGraph<V,E> g, V source) {
		System.out.println("printDFS: ");
		
		List<V> visitedVertices = new LinkedList<>();
		
		Map<V,List<V>> adjList = g.getAdjList();
		
		System.out.print(source + " ");
		
		visitedVertices.add(source);
		
		for(V v : adjList.get(source))
			if(!visitedVertices.contains(v))
				recursiveDFS(adjList,v,visitedVertices);
			
		System.out.println();
		
		
	}

	private static <V extends AbstractVertex> void recursiveDFS(Map<V, List<V>> adjList, V source, List<V> visitedVertices) {
		System.out.print(source + " ");
		
		visitedVertices.add(source);
		
		for(V v : adjList.get(source))
			if(!visitedVertices.contains(v))
				recursiveDFS(adjList,v,visitedVertices);
	}
	
	// prints all vertices, starting from those linked from source
	public static <V extends AbstractVertex, E extends AbstractEdge> void printDFS2(AdjListGraph<V,E> g, V source) {
		System.out.println("printDFS2: ");
		
		List<V> unvisitedVertices = new LinkedList<>(g.getVerticesList());
		
		Map<V,List<V>> adjList = g.getAdjList();
		
		System.out.print(source + " ");
		
		unvisitedVertices.remove(source);
		
		for(V v : adjList.get(source))
			if(unvisitedVertices.contains(v))
				recursiveDFS2(adjList,v,unvisitedVertices);
		
		while(!unvisitedVertices.isEmpty())
			recursiveDFS2(adjList, unvisitedVertices.get(0), unvisitedVertices);
			
		System.out.println();
		
		
	}
	
	private static <V extends AbstractVertex> void recursiveDFS2(Map<V, List<V>> adjList, V source, List<V> unvisitedVertices) {
		System.out.print(source + " ");
		
		unvisitedVertices.remove(source);
		
		for(V v : adjList.get(source))
			if(unvisitedVertices.contains(v))
				recursiveDFS2(adjList,v,unvisitedVertices);
	}
	
	
	

	
	

}
