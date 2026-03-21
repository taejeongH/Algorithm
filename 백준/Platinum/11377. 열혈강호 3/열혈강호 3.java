//package BOJ.열혈강호3;

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
		int K = Integer.parseInt(st.nextToken());
		
		g = new List[N+1]; for(int i=1; i<=N; i++) g[i] = new ArrayList<>();
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken());
			for (int j=0; j<c; j++) {
				int num = Integer.parseInt(st.nextToken());
				g[i].add(num);
			}
		}
		
		match = new int[M+1];
		int res = 0;
		for (int i=1; i<=N; i++) {
			v = new boolean[M+1];
			if (dfs(i)) res++;
		}
		
		for (int i = 1; i <= N && K > 0; i++) {
			v = new boolean[M+1];
		    if (dfs(i)) {
		        res++;
		        K--;
		    }
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
