//package BOJ.고층빌딩;

import java.io.*;
import java.util.*;

public class Main{
	static int N, L, R;
	static final int MOD = 1000000007;
	static long[][][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		dp = new long[N+1][L+1][R+1];
		for (int i=0; i<=N; i++) {
			for (int j=0; j<=L; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(dfs(N, L, R));
	}
	
	static long dfs(int depth, int l, int r) {
		if (l <= 0 || r <= 0) return 0;
		if (depth == 1) {
			if (l==1 && r==1) return 1;
			else return 0;
		}
		
		if (dp[depth][l][r]!=-1) return dp[depth][l][r];
		
		long res = 0;
		res += dfs(depth-1, l-1, r);
		res += dfs(depth-1, l, r-1);
		res += dfs(depth-1, l, r) * (depth-2);
		
		return dp[depth][l][r] = res % MOD;
	}
}