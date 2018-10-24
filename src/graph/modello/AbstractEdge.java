package graph.modello;

public abstract class AbstractEdge {
	protected Object source;
	protected Object target;
	
	/**
	 * Getter for {@link #source}
	 * @return source vertex
	 */
	public abstract Object getSource();

	/**
	 * Getter for target
	 * @return target vertex
	 */
	public abstract Object getTarget();
	
}
