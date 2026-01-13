//package BOJ.소형기관차;

import java.io.*;
import java.util.*;

public class Main{
	static int N, M;
	static int[] map, sum;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		sum = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			sum[i] = i==0?map[i]:map[i]+sum[i-1];
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
		
		int res = idx==0?sum[idx+M-1]:sum[idx+M-1]-sum[idx-1];
		
		res += dfs(idx+M, choice+1);
		
		res = Math.max(res, dfs(idx+1, choice));
		
		return dp[idx][choice] = res;
	}
	
}