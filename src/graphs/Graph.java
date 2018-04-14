package graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Graph {
	private Map<Integer, List<Integer>> adjList;
	private int n;
	private int m;
	private Map<Integer, Boolean> marked = new HashMap<>();
	
	
//	public Graph() {
//		this.n = 0;
//		this.m = 0;
//		this.adjList = new HashMap<>();
//	}
	
	public Graph(String filename) {
		this.adjList = new HashMap<>();
		this.marked = new HashMap<>();
		ArrayList<String> edgeListStrings = edgeListStrings(filename);
		ArrayList<Integer[]> edgeListInts = edgeListInts(edgeListStrings);
		for (Integer[] edge : edgeListInts) {
			if (this.adjList.containsKey(edge[0])) {
				this.adjList.get(edge[0]).add(edge[1]);
			} else {
				this.adjList.put(edge[0], new LinkedList<Integer>());
				this.adjList.get(edge[0]).add(edge[1]);
			}
		}
		this.m = edgeListInts.size();
		this.n = Collections.max(this.adjList.keySet());
		for (Integer node : this.adjList.keySet()) {
			this.marked.put(node, false);
		}
		
	}
	
	// getter for adjList
	public HashMap<Integer, List<Integer>> getAdjList() {
		return (HashMap<Integer, List<Integer>>) this.adjList;
	}
	
	// getter for number of nodes
	public int numNodes() {
		return this.n;
	}
	
	// getter for number of edges
	public int numEdges() {
		return this.m;
	}
	
	// takes file input and returns list of strings, each of the form "a b"
	private static ArrayList<String> edgeListStrings(String filename)
	{
		ArrayList<String> edgeListStrings = new ArrayList<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while(line != null) {
				edgeListStrings.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Problem reading file.");
		}
		
		return edgeListStrings;
	}

	private static ArrayList<Integer[]> edgeListInts(ArrayList<String> edgeListString)
	{
		ArrayList<Integer[]> edgeListInts = new ArrayList<>();
		for (String string : edgeListString) {
			Integer[] edge = new Integer[2];
			Scanner s = new Scanner(string);
			edge[0] = s.nextInt();
			edge[1] = s.nextInt();
			edgeListInts.add(edge);
			s.close();
		}
		
		return edgeListInts;
	}
	
	/*public void constructGraph(ArrayList<Integer[]> edgeListInts) {
		for (Integer[] edge : edgeListInts) {
			if (adjList.containsKey(edge[0])) {
				adjList.get(edge[0]).add(edge[1]);
			} else {
				adjList.put(edge[0], new LinkedList<Integer>());
				adjList.get(edge[0]).add(edge[1]);
			}
		}
		
		this.m = edgeListInts.size();
		this.n = Collections.max(adjList.keySet());
	}*/
	
	public void validateVertex(Integer v) {
		if (v < 1 || v > this.n) throw new IndexOutOfBoundsException();
	}
	
	
	// depth-first search
	private void dfs(Integer s) {
		this.marked.put(s, true);
		for (Integer headNode : adjList.get(s)) {
			if (!marked.get(headNode).booleanValue()) {
				dfs(headNode);
			}
		}
	}
	
	private class DFS {
		private Integer t;
		private Integer s;
		boolean[] marked;
		int[] leader;
		int[] F;
		
		public DFS() {
			t = 0;
			s = null;	
		}
		
		public int[] DFSLoop1(Graph G) {
			int n = G.n;
			
			marked = new boolean[n + 1];
			leader = new int[n + 1];
			F = new int[n + 1];
			for( int i = n; i >= 1 ; i--) {
				if(!marked[i]) {
					s = i;
					dfs(G, i);
				}
			}
			return F;
		}
		
		public int[] DFSLoop2(Graph G, int[] F) {
			int n = G.n;
			this.F = new int[n + 1];
			marked = new boolean[n + 1];
			leader = new int[n + 1];
			int index = F.length - 1;
			
			for(int i = index; i > 0; i--) {
				if(!marked[F[i]]) {
					s = F[i];
					dfs(G, F[i]);
				}
			}
			return leader;
		}
		
		public void dfs(Graph G, int i) {
			G.validateVertex(i);
			marked[i] = true;
			leader[i] = s;
			
			for (int j : G.adjList.get(i)) {
				
				if(!marked[j]) {
					dfs(G,j);
				}
			}
			t++;
			G.validateVertex(t);
			this.F[t] = i;
		}
	}
	
	
	public static void main(String[] args) {
//		ArrayList<String> edgesStr = edgeListStrings("SCCmini.txt");
//		for (String string : edgesStr) {
//			System.out.println(string);
//		}
//		
//		ArrayList<Integer[]> edgesInt = edgeListInts(edgesStr);
//		for (Integer[] edge : edgesInt) {
//			System.out.println(edge[0] + ", " + edge[1]);
//		}
		
		Graph sampleGraph = new Graph("SCCmini.txt");
		
		for (Integer source : sampleGraph.adjList.keySet()) {
			System.out.println(source + ", " + sampleGraph.adjList.get(source));
			
		}
		System.out.println(sampleGraph.m);
		System.out.println(sampleGraph.n);
		System.out.println("---");
		sampleGraph.dfs(1);
		for (Integer node : sampleGraph.marked.keySet()) {
			System.out.println(sampleGraph.marked.get(node).booleanValue());
		}
		
		
	}

}

