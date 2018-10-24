package graph.alg.shortestpath;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import graph.modello.AbstractVertex;
import graph.modello.impl.AdjListGraph;
import graph.modello.impl.WeightedEdge;

public class DijkstraShortestPath<V extends AbstractVertex, E extends WeightedEdge> extends BaseShortestPath<V,E> {

	/**
	 * Initialize every predecessor with {@code null} and every distance with {@link BaseShortestPath#POSITIVE_INFINITY POSITIVE_INFINITY} but distance to source with 0
	 * @param ag an {@link AdjListGraph} 
	 * @param source source vertex
	 * @param sink sink vertex
	 */
	public DijkstraShortestPath(AdjListGraph<V, E> ag, V source, V sink) {
		this.ag = ag;
		this.sink = sink;
		this.source = source;
		
		distance = new HashMap<>();
		predecessor = new HashMap<>();
		
		for(V v: ag.getVerticesList()) {
			predecessor.put(v, null);
			distance.put(v, POSITIVE_INFINITY);
		}
		
		distance.put(source, 0.0);
	}
	
	/**
	 * creates a list of vertices and initialize it with the graph vertices list,
	 * at every iteration extract from q the vertex with the lowest distance and {@link #relax(AbstractVertex, AbstractVertex) relax}
	 * his adjacency list.
	 * 
	 */
	private void updateDistances() {		
		List<V> q = new LinkedList<>(ag.getVerticesList());
		
		

		while(!q.isEmpty()) {
			V u = extractMin(q);
			q.remove(u);
			
			for(V v : ag.getAdjList().get(u)) {
				relax(u,v);
			}
		}
	}

	
	

	private V extractMin(List<V> q) {
		V min = q.get(0);
		
		for(V v : q) 
			if(distance.get(v) < distance.get(min))
				min = v;
		
		return min;
			
	}
	
	
	/**
	 * {@link #updateDistances() Update distances} and create a list that contains the shortest path from source to sink.<br>
	 *  Insert the sink and iterates adding the predecessor of the first element in the first position.
	 */
	public List<V> getShortestPath() {
		List<V> path = new LinkedList<>();
		
		// set distances and predecessors
		updateDistances();
		
		// insert sink in path
		path.add(0, sink);
		
		// insert from sink to source, using predecessors
		while(predecessor.get(path.get(0)) != null)
			// add elem at the beginning and shifts everything to the right(+1)
			path.add(0, predecessor.get(path.get(0)));
		
		return path;
	}
	

}
