package graph.alg.shortestpath;

import java.util.List;
import java.util.Map;

import graph.modello.AbstractVertex;
import graph.modello.impl.AdjListGraph;
import graph.modello.impl.WeightedEdge;

public abstract class BaseShortestPath<V extends AbstractVertex, E extends WeightedEdge> {
	protected static final Double POSITIVE_INFINITY = 999999.0;
	
	protected AdjListGraph<V,E> ag;
	
	protected V source , sink;
	
	protected Map<V,Double> distance;
	protected Map<V,V> predecessor;

	/**
	 * Check if the distance from source to v need to be updated: <br>
	 * {@code if(d[v] > d[u] + w(u,v))} --> {@code d[v] = d[u] + w(u,v)}<br>
	 * If distance is updated, predecessor is updated as well
	 * 
	 * 
	 * @param u first vertex
	 * @param v second vertex
	 */
	public void relax(V u, V v) {
		E e = ag.getEdge(u, v);
		
		int w = e.getWeight();
		
		if(distance.get(v) > distance.get(u) + w) {
			distance.put(v, distance.get(u) + w);

			predecessor.put(v, u);
		}
			
	}
	
	/**
	 * @return a list of vertices that is the shortest path from source to sink
	 */
	public abstract List<V> getShortestPath();
}
