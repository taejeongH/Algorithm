//package BOJ.소형기관차;

import java.io.*;
import java.util.*;

public class Main{
	static int N, M;
	static int[] map;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		dp = new int[N][3];
		for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
		System.out.println(dfs(0, 0));
	}
	
	static int dfs(int idx, int choice) {
		if (idx + M > N) return 0;
		if(choice==3) return 0;
		if (dp[idx][choice] != -1) return dp[idx][choice];
		
		int res = 0;
		
		for (int i=idx; i<idx+M; i++) {
			res += map[i];
		}
		res += dfs(idx+M, choice+1);
		
		res = Math.max(res, dfs(idx+1, choice));
		
		return dp[idx][choice] = res;
	}
	
}