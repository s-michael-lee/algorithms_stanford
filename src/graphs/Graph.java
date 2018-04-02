package graphs;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


public class Graph {
	private ArrayList<int[]> adj; // list of edges
	private int V;
	private int E;
	ArrayList<String> lines = new ArrayList<>();
	public Graph(String filename) {
		adj = new ArrayList<int[]>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while(line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e) {
			System.err.println("Problem reading the file.");
		}
	
		for (String string : lines) {
			ArrayList<int[]> edgesForV = new ArrayList<>();
			Scanner s = new Scanner(string);
			int vertex = s.nextInt();
			while(s.hasNextInt()) {
				int n = s.nextInt(); 
				if(vertex < n) {
					int[] edge = new int[2];
					edge[0] = vertex;
					edge[1] = n;
					edgesForV.add(edge);
				}
			}
			s.close();
			adj.addAll(edgesForV);
		}
		this.V = lines.size();
		this.E = adj.size();
	}
	
	private void contract(Random rdm) {
		if(this.E > 0) {
			int index = rdm.nextInt(this.E);
			int[] cut = this.adj.get(index);
			int u = cut[0];
			int v = cut[1];
		//remove all edges between u and v
			Iterator<int[]> it1 = adj.iterator();
			while(it1.hasNext()) {
				int[] edge = it1.next();
				if(edge[0] == u && edge[1] == v) {
					it1.remove();
					this.E--;
				}
			}
			for (int[] edge : adj) {
				if(edge[0]==v) {
					edge[0] = u;
				}	
				if(edge[1]==v) {
					edge[1] = u;
				}
				if(edge[0] > edge[1]) {
					int temp = edge[0];
					edge[0] = edge[1];
					edge[1] = temp;
				}
			}
		}
		V--;
	}
	
	public static void main(String[] args) {
		int[] results = new int[1000];
		for(int i = 0; i<1000; i++) {
			Graph graph = new Graph("kargerMinCut.txt");
			int v = graph.V;
			for(int k = 1; k <= (v-2); k++) {
				Random rdm = new Random();
				graph.contract(rdm);
			}
			results[i] = graph.E;
		}
		java.util.Arrays.sort(results);
		System.out.println(results[0]);

	}
}
