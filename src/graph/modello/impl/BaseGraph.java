package graph.modello.impl;

import java.util.LinkedList;
import java.util.List;

import graph.modello.AbstractEdge;
import graph.modello.AbstractGraph;
import graph.modello.AbstractVertex;

public class BaseGraph<V extends AbstractVertex,E extends AbstractEdge> extends AbstractGraph<V, E> {
	
	/**
	 * Initialize the list of vertices using the given edge list
	 * @param edges a list of edges
	 */
	public BaseGraph(List<E> edges) {
		this.edges = edges;
		
		buildVerticesFromEdges();
	}
	
	@Override
	public List<E> getEdgeList() {
		return edges;
	}

	@Override
	public List<V> getVerticesList() {
		return vertices;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Vertices: \n");
		for(V v: vertices)
			sb.append(v + "\n");
		
		sb.append("\nEdges: \n");
		for(E e : edges)
			sb.append(e + "\n");
		
		return sb.toString();
		
	}
	
	
	/*	TODO: 	delegare a una factory la creazione dei vertici
	 * 		  	obiettivo finale: <V extends AbstractVertex,E extends AbstractEdge> -> <V,E>
	 * 			non deve esserci bisogno di usare e.getsource().
	 */
	
	
	private void buildVerticesFromEdges() {
		vertices = new LinkedList<>();
		
		for(E e: edges) {
			if(!vertices.contains(e.getSource()))
				vertices.add((V) e.getSource());
			
			if(!vertices.contains(e.getTarget()))
				vertices.add((V) e.getTarget());
			
		}
	}
	
	
	/**
	 * Check if the given edge is contained in the graph, if yes the corresponding edge is returned,
	 * if not null is returned
	 * 
	 * @param e an edge
	 * @return the corresponding edge if it exists, or null
	 */
	@Override
	public E getEdge(E e) {
		for(E edge : edges)
			if(e.equals(edge))
				return edge;
		
		return null;
	}
	
	/**
	 * Check if the given edge is contained in the graph, if yes the corresponding edge is returned,
	 * if not null is returned
	 * 
	 * @param source first vertex
	 * @param target second vertex
	 * @return an edge composed by source and target if it exists, or null
	 */
	@Override
	public E getEdge(V source, V target) {
		for(E e : edges)
			if(e.getSource().equals(source) && e.getTarget().equals(target))
				return e;
		return null;
	}
	
	
	/**
	 * Check if the given vertex is contained in the graph, if yes the corresponding vertex is returned,
	 * if not null is returned
	 * 
	 * @param v a vertex
	 * @return the corresponding vertex if it exists, or null
	 */
	@Override
	public V getVertex(V v) {
		for(V vertex : vertices)
			if(v.equals(vertex))
				return vertex;
		
		return null;
	}

}
