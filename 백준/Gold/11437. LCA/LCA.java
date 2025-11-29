//package BOJ.LCA;

import java.io.*;
import java.util.*;

public class Main {
	static int height;
	static int[][] parent;
	static int[] level;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		List<Integer>[] g = new List[N+1]; for(int i=1; i<=N; i++) g[i] = new ArrayList<>();
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			g[e].add(s);
		}
		height = (int) Math.ceil(Math.log(N)/Math.log(2));
		parent = new int[height][N+1];
		level = new int[N+1];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {1, 0});
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int node = now[0];
			int dis = now[1];
			for (int nxt : g[node]) {
				if(parent[0][nxt]==0 && nxt!=1) {
					parent[0][nxt]=node;
					level[nxt] = dis+1;
					que.add(new int[] {nxt, dis+1});
				}
			}
		}
		
		for (int i=1; i<height; i++) {
			for (int j=1; j<=N; j++) {
				int mid = parent[i-1][j];
				parent[i][j] = mid==0?0:parent[i-1][mid];
			}
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());
			sb.append(lca(nodeA, nodeB)).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int lca(int nodeA, int nodeB) {
		if(level[nodeA]<level[nodeB]) {
			int tmp=nodeA;
			nodeA=nodeB;
			nodeB=tmp;
		}
		
		int diff = level[nodeA]-level[nodeB];
		for (int i=0; i<height; i++) {
			if(((diff>>i) & 1)==1) nodeA=parent[i][nodeA];
		}
		
		if(nodeA==nodeB) return nodeA;
		
		for (int i=height-1; i>=0; i--) {
			if (parent[i][nodeA]!=parent[i][nodeB]) {
				nodeA = parent[i][nodeA];
				nodeB = parent[i][nodeB];
			}
		}
		return parent[0][nodeA];
			
		
	}
}
