//package BOJ.할일정하기1;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_000_000_000;
	static int N;
	static int[][] map, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N][1<<N];
		for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
		System.out.println(dfs(0, 0));
	}
	
	public static int dfs(int node, int field) {
		if (node == N) {
			return 0;
		}
		if(dp[node][field]!=-1) return dp[node][field];
//		System.out.println(node + " " + field);
		
		int res = INF;
		for (int i=0; i<N; i++) {
			if ((field & (1<<i)) == 0) {
				res = Math.min(dfs(node+1, field|(1<<i))+map[node][i], res);
			}
		}
		
		return dp[node][field]=res;
	}
}
