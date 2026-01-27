//package BOJ.DFS스페셜저지;

import java.io.*;
import java.util.*;

public class Main {
	static boolean[] v;
	static List<Integer>[] g;
	static int[] order;
	static int idx = 1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());

		g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			g[e].add(s);
		}
		v = new boolean[N+1];
		order = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		v[1] = true;
		if (order[0] != 1) {
		    System.out.println(0);
		    return;
		}
		System.out.println(dfs(1) ? 1 : 0);
	}
	
	public static boolean dfs(int node) {
		HashSet<Integer> child = new HashSet<>();
		for (int nxt : g[node]) {
			if (!v[nxt]) {
				child.add(nxt);
			}
		}
//		System.out.println(node + " " + idx);
		for (int i=0; i<child.size(); i++) {
			int nxt = order[idx];
			if (!child.contains(nxt)) return false;
			
			v[nxt] = true;
			idx ++;
			if(!dfs(nxt))return false;
		}
		
		return true;
	}
}
