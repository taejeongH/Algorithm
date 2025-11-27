//package BOJ.LCA2;

import java.io.*;
import java.util.*;

public class Main {
	static int N, LOG;
	static int[][] parents;
	static int[] levels;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		List<Integer>[] g = new List[N+1]; for(int i=1; i<N+1; i++) g[i] = new ArrayList<>();
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[p].add(c);
			g[c].add(p);
		}
		
		LOG = 1;
		while ((1 << LOG) <= N) LOG++;
		parents = new int[LOG][N+1];
		levels = new int[N+1];
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {1, 0});
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int node = now[0];
			int level = now[1];
			
			levels[node] = level;
			for (int nxt : g[node]) {
				if(parents[0][nxt]==0 && nxt!=1) {
					parents[0][nxt] = node;
					que.add(new int[] {nxt, level+1});
				}
			}
		}
		
		for (int k=1; k<LOG; k++) {
			for (int v=1; v<=N; v++) {
				int mid = parents[k - 1][v];
				parents[k][v] = (mid==0?0:parents[k-1][mid]);
			}
		}
		
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			sb.append(findParents(n1, n2)).append("\n");
		}
		System.out.print(sb);
	}
	
	
	public static int findParents(int n1, int n2) {
		if(levels[n1] < levels[n2]) {
			int tmp = n1; n1=n2; n2=tmp;
		}
		
		int diff = levels[n1]-levels[n2];
		for (int k=0; k<LOG; k++) {
			if(((diff>>k) & 1)==1) n1=parents[k][n1];
		}
		if(n1==n2) return n1;
		
		for (int k=LOG-1; k>=0; k--) {
			if (parents[k][n1] != parents[k][n2]) {
				n1=parents[k][n1];
				n2=parents[k][n2];
			}
		}
		
		return parents[0][n1];
	}
}
