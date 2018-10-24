package graph.alg.shortestpath;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import graph.modello.AbstractVertex;
import graph.modello.impl.AdjListGraph;
import graph.modello.impl.WeightedEdge;

public class BellmanFordShortestPath<V extends AbstractVertex, E extends WeightedEdge> extends BaseShortestPath<V,E> {
	
	/**
	 * Initialize every predecessor with {@code null} and every distance with {@link BaseShortestPath#POSITIVE_INFINITY POSITIVE_INFINITY} but distance to source with 0
	 * @param ag an {@link AdjListGraph} 
	 * @param source source vertex
	 * @param sink sink vertex
	 */
	public BellmanFordShortestPath(AdjListGraph<V, E> ag, V source, V sink) {
		this.ag = ag;
		this.source = source;
		this.sink = sink;
		
		distance = new HashMap<>();
		predecessor = new HashMap<>();
		
		for(V v: ag.getVerticesList()) {
			predecessor.put(v, null);
			distance.put(v, POSITIVE_INFINITY);
		}
		
		distance.put(source, 0.0);
	
	}
	
	
	/**
	 * iterates n-1 (n is the number of vertices) times: {@link BaseShortestPath#relax(AbstractVertex, AbstractVertex) relax} every edge.<br>
	 * After that checks if there are negative cycle: <br>
	 * {@code if(d[u] > d[u] + w(u,v))} the graph has a negative cycle
	 * 		because after n-1 iteration in a graph without negative cycle that condition is never true.
	 */
	private void updateDistances() {
		for(int i=0; i< ag.getVerticesList().size() -1; i++) 
			for(E e : ag.getEdgeList()) 
				relax((V) e.getSource(),(V) e.getTarget());
			
		for(E e : ag.getEdgeList()) {
			if(distance.get((V) e.getSource()) > distance.get((V) e.getTarget()) + e.getWeight())
				System.out.println("Graph contains a negative cycle.");			
		}
	}

	/**
	 * {@link #updateDistances() Update distances} and create a list that contains the shortest path from source to sink.<br>
	 *  Insert the sink and iterates adding the predecessor of the first element in the first position.
	 */
	@Override
	public List<V> getShortestPath() {
		List<V> path = new LinkedList<>();
		
		updateDistances();
		
		path.add(0,sink);
		
		while(predecessor.get(path.get(0)) != null)
			path.add(0, predecessor.get(path.get(0)));
			
		
		return path;
	}

}
