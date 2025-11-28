//package BOJ.욕심쟁이판다;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[][] map;
	static int[][] dp;
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
		
		dp = new int[N][N];
		for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
		
		
		long res = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				res = Math.max(dfs(i, j), res);
			}
		}
		System.out.println(res);
	}
	
	public static int dfs(int y, int x) {
		if(dp[y][x]!=-1) return dp[y][x];
		int res = 0;
		for (int i=0; i<4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			
			if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
			
			if(map[ny][nx]>map[y][x]) {
				res = Math.max(dfs(ny, nx), res);
			}
		}
		return dp[y][x] = res+1;
	}
}
