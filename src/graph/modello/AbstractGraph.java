package graph.modello;

import java.util.List;

public abstract class AbstractGraph<V,E> {
	
	protected List<V> vertices;
	protected List<E> edges;
	
	/**
	 * 
	 * @param e requested edge
	 * @return an edge equals to e or null
	 */
	public abstract E getEdge(E e);
	
	/**
	 * 
	 * @param source source vertex
	 * @param target target vertex
	 * @return an edge that has source as first vertex and target as second vertex, or null
	 */
	public abstract E getEdge(V source, V target);
	
	/**
	 * 
	 * @param v requested vertex
	 * @return a vertex equals to v, or null
	 */
	public abstract V getVertex(V v);

	/**
	 * 
	 * @return the edge list
	 */
	public abstract List<E> getEdgeList();
	/**
	 * 
	 * @return the vertex list
	 */
	public abstract List<V> getVerticesList();
}
