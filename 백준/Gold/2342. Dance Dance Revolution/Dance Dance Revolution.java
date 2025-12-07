//package BOJ.댄스댄스레볼루션;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int N;
	static int[] pos;
	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		pos = new int[100001];
		pos[0]=0;
		N = 1;
		while (true) {
			int num = Integer.parseInt(st.nextToken());
			if (num==0) break;
			pos[N++] = num;
		}
		
		dp = new int[N][5][5];
		for (int i=0; i<N; i++) {
			for (int j=0; j<5; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(dfs(1, 0, 0));
	}
	
	public static int dfs(int depth, int l, int r) {
		if (depth==N) return 0;
		if (dp[depth][l][r]!=-1) return dp[depth][l][r];
		
		int res = INF;
		if(pos[depth] == l) {
			res = dfs(depth+1, l, r) + cal(l, pos[depth]);
		} else if (pos[depth]==r) {
			res = dfs(depth+1, l, r) + cal(r, pos[depth]);
		} else {
			res = Math.min(dfs(depth+1, pos[depth], r) + cal(l, pos[depth]), res);
			res = Math.min(dfs(depth+1, l, pos[depth]) + cal(r, pos[depth]), res);
		}
		
		return dp[depth][l][r]=res;
	}
	
	public static int cal (int before, int after) {
		if (before == 0) return 2;
		if (before == after) return 1;
		if (before == 1 && after == 3) return 4;
		if (before == 3 && after == 1) return 4;
		if (before == 2 && after == 4) return 4;
		if (before == 4 && after == 2) return 4;
		
		return 3;
	}
}
