//package BOJ.게임;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int N, M;
	static int[][] map;
	static boolean[][] v;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				if(input.charAt(j)=='H') map[i][j] = -1;
				else map[i][j] = input.charAt(j) - '0';
			}
		}
		v = new boolean[N][M];
		dp = new int[N][M];
		for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
		int res = dfs(0, 0);
		System.out.println(res==Integer.MAX_VALUE?-1:res);
	}
	
	public static int dfs(int y, int x) {
		if (y<0 || y>=N || x<0 || x>=M || map[y][x]==-1) {
			//종료 조건
			return 0;
		}
		
		if (v[y][x]) {
			//무한히 갈 수 있음
			return Integer.MAX_VALUE;
		}
		
		if(dp[y][x]!=-1) return dp[y][x];
		int dis = map[y][x];
		int res = 0;
		for (int i=0; i<4; i++) {
			int ny = y + (dy[i]*dis);
			int nx = x + (dx[i]*dis);
			
			v[y][x] = true;
			res = Math.max(dfs(ny, nx), res);
			if(res == Integer.MAX_VALUE) return Integer.MAX_VALUE;
			v[y][x] = false;
		}
		
		
		return dp[y][x] = res+1;
	}
}
