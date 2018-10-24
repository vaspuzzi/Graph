package graph.modello.impl;

import graph.modello.AbstractEdge;

public class Edge extends AbstractEdge {
	
	public Edge(Object source, Object target) {
		this.source = source;
		
		this.target = target;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getSource() {
		return source;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getTarget() {
		return target;
	}
	
	@Override
	public String toString() {
		return "(" + source + "," + target + ")";
	}
	
	/**
	 * 
	 * @return true if source and target are the same
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null)
			throw new IllegalArgumentException();
		if(o == this)
			return true;
		if(!(o instanceof Edge))
			return false;
		
		Edge e = (Edge) o;

		if(e.getSource().equals(source) && e.getTarget().equals(target))
			return true;
		
		return false;
	}
	
	/**
	 * Uses the hashcode of its toString, if two edges are equals, their toString give the same String, for how equals and toString are defined.
	 */
	@Override
	public int hashCode() {
		String s = this.toString();
		
		return s.hashCode();
	}

}
