//package BOJ.트리의지름;

import java.io.*;
import java.util.*;

public class Main {
	static List<int[]>[] g;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		g = new List[N+1]; for(int i=1; i<N+1; i++) g[i] = new ArrayList<>();
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			g[parent].add(new int[] {child, cost});
			g[child].add(new int[] {parent, cost});
		}
		
		int res = 0;
		for (int i=1; i<N+1; i++) {
			v = new boolean[N+1];
			v[i]=true;
			res= Math.max(dfs(i), res);
		}
		System.out.println(res);
	}
	public static int dfs(int node) {
		
		int res = 0;
		for (int[] nxt : g[node]) {
			if(v[nxt[0]]) continue;
			v[nxt[0]]=true;
			res = Math.max(dfs(nxt[0]) + nxt[1], res);
		}
		
		return res;
	}
}
