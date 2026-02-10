//package BOJ.장난감조립;

import java.io.*;
import java.util.*;

public class Main{
	static int N;
	static int[] dp;
	static List<int[]>[] g;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[] isMiddle = new boolean[N+1];
		
		g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			g[Y].add(new int[] {X, K});
			isMiddle[X] = true;
		}
		
		dp = new int[N];
		Arrays.fill(dp, -1);
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			if(isMiddle[i]) continue;
			sb.append(i).append(" ").append(dfs(i)).append("\n");
		}
		System.out.print(sb);
	}
	public static int dfs(int node) {
		if (node == N) return 1;
		if (dp[node]!=-1) return dp[node];
		int res = 0;
		for (int[] nxt : g[node]) {
			res += dfs(nxt[0]) * nxt[1];
		}
		
		return dp[node] = res;
	}
}