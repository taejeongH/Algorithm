//package BOJ.같은탑;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static final int INF = 1_000_000_000;
	static int[] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N][500_001];
		for (int i=0; i<N; i++) {
			Arrays.fill(dp[i], -1);
		}
		int res = dfs(0, 0);
		System.out.println(res==0?-1:res);
	}
	static int dfs(int idx, int diff) {
		if (idx == N) {
			if (diff == 0) return 0;
			return -INF;
		}
		if(dp[idx][diff]!=-1 )return dp[idx][diff];
		
		int cur = map[idx];
		int res = dfs(idx+1, diff);
		res = Math.max(res,  dfs(idx+1, diff+cur));
		
		if (cur <= diff) {
			res = Math.max(res, dfs(idx+1, diff-cur)+cur);
		} else {
			res = Math.max(res, dfs(idx+1, cur-diff)+diff);
		}
		
		return dp[idx][diff]=res;
	}
}
