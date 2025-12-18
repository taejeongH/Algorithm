//package BOJ.과제;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(map, (o1, o2) -> Integer.compare(o1[0], o2[0]));
		
		dp = new int[N+1][N+1];
		for (int i=0; i<N+1; i++) Arrays.fill(dp[i], -1);
		System.out.println(dfs(1, 0));
	}
	
	public static int dfs(int day, int start) {
		if (day==N+1 || start==N) return 0;
		
		if(dp[day][start] != -1) return dp[day][start];
		
		int res = 0;
		for (int i=start; i<N; i++) {
			if (day <= map[i][0]) {
				res = Math.max(dfs(day+1, i+1) + map[i][1], res); 
			}
		}
		return dp[day][start]=res;
	}
}
