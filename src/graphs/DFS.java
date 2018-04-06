package graphs;

import java.util.ArrayList;

public class DFS {
	private Graph G;
	private Integer startNode;
	boolean[] marked;
	
	public DFS(Graph G, Integer startNode) {
		this.G = G;
		this.startNode = startNode;
		int numNodes = G.numNodes();
		this.marked = new boolean[numNodes];
	}
	
	public void executeDFS() {
		G.validateVertex(this.startNode);
		marked[this.startNode] = true;
		for (Integer tail : G.getAdjList().get(this.startNode)) {
			if (!marked[tail]) {
				executeDFS(G, tail);
			}
		}
	}
	
	public static void main(String[] args) {
		
		Graph sampleGraph = new Graph();
		
	}

}
