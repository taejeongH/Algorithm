//package BOJ.노트북의주인을찾아서;

import java.io.*;
import java.util.*;

public class Main {
	static boolean[] v;
	static int[] match;
	static List<Integer>[] g;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			g[p].add(n);
		}
		
		int res = 0;
		match = new int[N+1];
		for (int i=1; i<=N; i++) {
			v = new boolean[N+1];
			if(dfs(i)) res++;
		}
		System.out.println(res);
	}
	
	static boolean dfs(int node) {
		for (int nxt : g[node]) {
			if (v[nxt]) continue;
			v[nxt] = true;
			
			if (match[nxt]==0 || dfs(match[nxt])) {
				match[nxt] = node;
				return true;
			}
		}
		return false;
	}
}
