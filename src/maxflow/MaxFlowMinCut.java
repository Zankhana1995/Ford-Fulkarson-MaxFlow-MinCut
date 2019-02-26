package maxflow;

import java.util.LinkedList;

public class MaxFlowMinCut {
	static final int Vertices = 6;

	// this function will use bfs to check if there is any path exists from source
	// to sink
	// parent will store the path
	boolean checkpathExists(int residualGraph[][], int s, int t, int parent[]) {
		boolean visited[] = new boolean[Vertices];
		for (int i = 0; i < Vertices; i++) {
			visited[i] = false;
		}
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		parent[s] = -1;
		while (queue.size() != 0) {
			int u = queue.poll();
			for (int v = 0; v < Vertices; v++) {
				if (visited[v] == false && residualGraph[u][v] > 0) {
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}
		return (visited[t] == true);
	}

	int maxFlow(int graph[][], int s, int t) {
		int u, v;
		int maxflow = 0;
		int parent[] = new int[Vertices];
		int residualGraph[][] = new int[Vertices][Vertices];
		for (u = 0; u < Vertices; u++) {
			for (v = 0; v < Vertices; v++) {
				residualGraph[u][v] = graph[u][v];
			}
		}
		// make the flow while there is path from source to sink
		while(checkpathExists(residualGraph, s, t, parent))
		{
			int path_flow = Integer.MAX_VALUE; 
            for (v=t; v!=s; v=parent[v]) 
            { 
                u = parent[v]; 
                path_flow = Math.min(path_flow, residualGraph[u][v]); 
            } 
  
            // update residual capacities of the edges and 
            // reverse edges along the path 
            for (v=t; v != s; v=parent[v]) 
            { 
                u = parent[v]; 
                residualGraph[u][v] -= path_flow; 
                residualGraph[v][u] += path_flow; 
            } 
  
            // Add path flow to overall flow 
            maxflow += path_flow; 
            
		}
		return maxflow;

	}

	public static void main(String[] args) {
		int graph[][] = new int[][] { { 0, 16, 13, 0, 0, 0 }, { 0, 0, 10, 12, 0, 0 }, { 0, 4, 0, 0, 14, 0 },
				{ 0, 0, 9, 0, 0, 20 }, { 0, 0, 0, 7, 0, 4 }, { 0, 0, 0, 0, 0, 0 } };
		MaxFlowMinCut m = new MaxFlowMinCut();
		System.out.println(m.maxFlow(graph,0, 5));
	}
}
