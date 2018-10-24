package graph.modello.impl;

import graph.modello.AbstractVertex;

public class Vertex extends AbstractVertex {
	
	public Vertex(Object data) {
		this.data = data;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null)
			throw new IllegalArgumentException();
		if(o == this)
			return true;
		if(!(o instanceof Vertex))
			return false;
		
		Vertex v = (Vertex) o;

		if(v.getData().equals(data))
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return data.toString().hashCode();
	}
	
	@Override
	public String toString() {
		return "" + data;
	}

	

	

}
