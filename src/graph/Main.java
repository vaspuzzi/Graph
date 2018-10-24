package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import graph.alg.TransitiveClosure;
import graph.alg.print.GraphPrinter;
import graph.alg.shortestpath.APSPMatrix;
import graph.alg.shortestpath.BellmanFordShortestPath;
import graph.alg.shortestpath.DijkstraShortestPath;
import graph.modello.AbstractGraph;
import graph.modello.impl.AdjListGraph;
import graph.modello.impl.Edge;
import graph.modello.impl.Vertex;
import graph.modello.impl.WeightedEdge;

public class Main {

	public static void main(String[] args) {
//		String filename1 = "/resource/textfiles/graph.txt";
//		
//		URL url = AbstractGraph.class.getResource(filename1);
//				
//		File f = new File(url.getPath());
//
//		List<Edge> edgeList = buildEdgesFromFile(f);
//		
//		AdjListGraph<Vertex, Edge> ag = new AdjListGraph<>(edgeList);
//	
//		GraphPrinter.printBFS(ag, ag.getVerticesList().get(1));
//		GraphPrinter.printBFS2(ag, ag.getVerticesList().get(1));
//		GraphPrinter.printDFS(ag, ag.getVerticesList().get(1));
//		GraphPrinter.printDFS2(ag, ag.getVerticesList().get(1));
//		
		String filename2 = "/resource/textfiles/WeightedGraph.txt";
		
		URL url2 = AbstractGraph.class.getResource(filename2);
				
		File ff = new File(url2.getPath());
		
		List<WeightedEdge> weightedEdgeList = buildWeightedEdgesFromFile(ff);
		
		AdjListGraph<Vertex, WeightedEdge> wg = new AdjListGraph<>(weightedEdgeList);
		
		
//		Vertex source = wg.getVertex(new Vertex(1));
//		Vertex sink = wg.getVertex(new Vertex(4));
//		
//		GraphPrinter.printBFS2(wg, source);
//		
//		DijkstraShortestPath<Vertex, WeightedEdge> dsp = new DijkstraShortestPath<>(wg, source, sink);
//		List<Vertex> dijkstraShortestPath = dsp.getShortestPath();
//		
//		System.out.println("Dijkstra shortest path " + source + "->" + sink + " : " + dijkstraShortestPath);
//		
//		BellmanFordShortestPath<Vertex, WeightedEdge> bfsp = new BellmanFordShortestPath<>(wg, source, sink);
//		List<Vertex> bellmanFordShortestPath = bfsp.getShortestPath();
//		
//		System.out.println("Bellman-Ford shortest path " + source + "->" + sink + " : " + bellmanFordShortestPath);

//		TransitiveClosure<Vertex, WeightedEdge> tc = new TransitiveClosure<>(wg);
//		
//		int[][] m = tc.getClosure();
//		
//		System.out.println(wg);
//		
//		System.out.println("Transitive closure: ");
//		for(int i=0; i<wg.getVerticesList().size(); i++)
//			System.out.println(Arrays.toString(m[i]));
		
		
		APSPMatrix<Vertex, WeightedEdge> apsp = new APSPMatrix<>(wg);
		
		int[][] m = apsp.allPairShortestPath();
		
		int n = wg.getVerticesList().size();
		
		for(int i=0; i<n; i++)
			System.out.println(Arrays.toString(m[i]));
	}
	
	private static List<WeightedEdge> buildWeightedEdgesFromFile(File f) {
		List<WeightedEdge> edgeList = new LinkedList<>();
		
		try (Scanner sc = new Scanner(f)) {
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				
				String[] tokens = line.split("-");
			
				Integer i1 = Integer.valueOf(tokens[0]);
				Integer i2 = Integer.valueOf(tokens[1]);
				
				Integer w =  Integer.valueOf(tokens[2]);
			
				Vertex v1 = new Vertex(i1);
				Vertex v2 = new Vertex(i2);
				
				WeightedEdge e = new WeightedEdge(v1,v2,w);
				
				boolean contains = false;
				for(WeightedEdge edge : edgeList) 
					if(e.equals(edge))
						contains = true;
				if(!contains)
					edgeList.add(e);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return edgeList;
	}

	private static List<Edge> buildEdgesFromFile(File f) {
		List<Edge> edgeList = new LinkedList<>();

		try (Scanner sc = new Scanner(f)) {
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				
				String[] tokens = line.split("-");
				
				System.out.println(tokens[0] + " " + tokens[1]);
				
				Integer i1 = Integer.valueOf(tokens[0]);
				Integer i2 = Integer.valueOf(tokens[1]);
			
				Vertex v1 = new Vertex(i1);
				Vertex v2 = new Vertex(i2);
				
				Edge e = new Edge(v1,v2);
				
				boolean contains = false;
				for(Edge edge : edgeList) 
					if(e.equals(edge))
						contains = true;
				if(!contains)
					edgeList.add(e);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return edgeList;
	}

}
