//package graphs;
//
//import java.util.ArrayList;
//
//public class DFS {
//	private Integer t; // to compute "finishing time" for first dfs loop
//	private Integer s; // start node
//	boolean[] marked;
//	Integer[] leader;
//	Integer[] F; // to keep track of finishing times for nodes
//	
//	public DFS() {
//		t = 0;
//		s = null;
//	}
//	
//	public void dfs(Graph G, Integer s) {
//		G.validateVertex(s);
//		marked[s] = true;
//		
//		this.startNode = startNode;
//		this.marked = new boolean[G.numNodes()];
//	}
//	
//	public void executeDFS(DFS ) {
//		G.validateVertex(this.startNode);
//		marked[this.startNode] = true;
//		for (Integer tail : G.getAdjList().get(this.startNode)) {
//			if (!marked[tail]) {
//				executeDFS(G, tail);
//			}
//		}
//	}
//	
//	public static void main(String[] args) {
//		
//		Graph sampleGraph = new Graph();
//		
//	}
//
//}
