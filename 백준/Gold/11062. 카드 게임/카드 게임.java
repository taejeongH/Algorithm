//package BOJ.카드게임;

import java.io.*;
import java.util.*;

public class Main {
	static int[] map;
	static int N;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=tc; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			dp = new int[N][N];
			for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
			
			sb.append(dfs(0, N-1)).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int dfs(int left, int right) {
		if(left>right) return 0;
		if(dp[left][right]!=-1) return dp[left][right];
		
		int res = 0;
		if((N-left+right)%2==1) {
			//근우 턴
			res = Math.max(dfs(left+1,right)+map[left], dfs(left, right-1)+map[right]);
		} else {
			res = Math.min(dfs(left+1,right), dfs(left, right-1));
		}
		
		return dp[left][right]=res;
	}
}
