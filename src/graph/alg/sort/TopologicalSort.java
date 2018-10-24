package graph.alg.sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import graph.modello.AbstractEdge;
import graph.modello.AbstractVertex;
import graph.modello.impl.AdjListGraph;

public class TopologicalSort <V extends AbstractVertex, E extends AbstractEdge>{
	/**
	 * Number of incoming edges of a vertex
	 */
	Map<V,Integer> indegree;
	
	AdjListGraph<V, E> ag;
	
	
	/**
	 * Initialize {@link #indegree}
	 * @param ag an adjacency graph
	 */
	public TopologicalSort(AdjListGraph<V, E> ag) {
		this.ag = ag;
		
		for(V v: ag.getVerticesList())
			indegree.put(v, 0);
		
		for(E e : ag.getEdgeList())
			indegree.put((V) e.getTarget(), indegree.get(e.getTarget()) + 1);	
	}
	
	/**
	 * Uses 2 lists: 
	 * <ul>
	 * 		<li>listaNodi : list of vertices with indegree = 0;</li>
	 * 		<li>listaOrdinata: resulting ordered list</li>
	 * </ul>
	 * <p>
	 * First listaNodi gets filled with initial vertices with indegree = 0.<br> 
	 * {@code while(listaNodi is not empty)} removes the head, put it in listaOrdinata and update the indegree counter of its adjList.
	 * @return a list of vertex in which every vertex come before other vertices linked to the vertex outcoming edges.
	 */
	public List<V> getSortedVertices() {
		List<V> listaNodi = new LinkedList<>();
		List<V> listaOrdinata = new LinkedList<>();
		
		int orderedVerticesCounter = 0;
		
		for(V v : ag.getVerticesList())
			if(indegree.get(v) == 0)
				listaNodi.add(v);
		
		while(!listaNodi.isEmpty()) {
			V i = listaNodi.remove(0);
			
			orderedVerticesCounter++;
			
			listaOrdinata.add(i);
			
			for(V j : ag.getAdjList().get(i)) {
				indegree.put(j, indegree.get(j) -1);
				
				if(indegree.get(j) == 0)
					listaNodi.add(j);
			}
				
		}
		
		if(orderedVerticesCounter > ag.getVerticesList().size())
			System.out.println("Graph contains a cycle");
		
		
		return listaOrdinata;
	}
	
}
