package graph.modello.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import graph.modello.AbstractEdge;
import graph.modello.AbstractVertex;

public class AdjListGraph<V extends AbstractVertex,E extends AbstractEdge> extends BaseGraph<V, E> {
	/**
	 * Adjacency list implemented as a {@link Map} 
	 */
	private Map<V,List<V>> adjList;
	
	/**
	 * Initialize vertex list using {@link BaseGraph#BaseGraph(List)} and initialize adjlist from edge list
	 * @param edges the edge list
	 */
	public AdjListGraph(List<E> edges) {
		super(edges);
		
		buildAdjListFromEdges();
	}

	private void buildAdjListFromEdges() {
		adjList = new HashMap<V, List<V>>();
		
		for(V v : vertices) 
			if(!adjList.containsKey(v)) {
				List<V> tmp = new LinkedList<>();
				
				for(E e: edges) {
					if(e.getSource().equals(v) && !tmp.contains(e.getTarget()))
						tmp.add((V) e.getTarget());
				}
				
				adjList.put(v, tmp);
			}
	}

	/**
	 * Getter for {@link #adjList}
	 * @return the adjList
	 */
	public Map<V,List<V>> getAdjList() {
		return adjList;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nAdjList : \n");
		
		for(V v : adjList.keySet()) {
			sb.append(v + ": ");
			
			for(V x : adjList.get(v))
				sb.append(x + " ");
			
			sb.append("\n");
		}

		return super.toString() + sb;
	}
	
	
}
