//package BOJ.사회망서비스;

import java.io.*;
import java.util.*;

public class Main {
	static boolean[] v;
	static List<Integer>[] g;
	static final int INF = 1_000_000_000;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		g = new List[N+1]; for(int i=1; i<=N; i++) g[i] = new ArrayList<>();
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			g[e].add(s);
		}
		v = new boolean[N+1];
		dp = new int[N+1][2];
		
		
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	public static void dfs(int cur) {
		v[cur] = true;
		
		dp[cur][0] = 0;
		dp[cur][1] = 1;
		
		for (int nxt : g[cur]) {
			if(v[nxt]) continue;
			
			dfs(nxt);
			
			dp[cur][0] += dp[nxt][1];
			dp[cur][1] += Math.min(dp[nxt][0], dp[nxt][1]);
		}
		
		
		return;
	}
}
