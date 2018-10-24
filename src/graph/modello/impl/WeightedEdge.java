package graph.modello.impl;

public class WeightedEdge extends Edge {
	private int weight;
	
	public WeightedEdge(Object source, Object target) {
		this(source,target,1);
	}

	public WeightedEdge(Object source, Object target, int weight) {
		super(source,target);
		
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	

}
