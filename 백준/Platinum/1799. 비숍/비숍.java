//package BOJ.비숍;

import java.io.*;
import java.util.*;

public class Main {
	static int N, blackMax, whiteMax;
	static int[][] map;
	static int[] dy = {-1, -1, 1, 1};
	static int[] dx = {-1, 1, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		blackMax=0;
		whiteMax=0;
		dfs(0, 0, true);
		dfs(0, 0, false);
		
		System.out.println(blackMax+whiteMax);
		
	}
	
	public static void dfs(int idx, int count, boolean isBlack) {
		if (idx==N*N) {
			if(isBlack) blackMax = Math.max(count, blackMax);
			else whiteMax = Math.max(count, whiteMax);
			return;
		}
		
		int y = idx/N;
		int x = idx%N;
		
		if((y+x)%2==(isBlack?0:1) && map[y][x]==1) {
			if(check(map, y, x)) {
				map[y][x] = 2;
				dfs(idx+1, count+1, isBlack);
				map[y][x] = 1;
			}
		}
		dfs(idx+1, count, isBlack);
	}
	
	public static boolean check(int[][] map, int y, int x) {
		for (int i=0; i<4; i++) {
			int j=1;
			int ny = y+dy[i]*j;
			int nx = x+dx[i]*j;
			while(ny<N && nx<N && ny>=0 && nx>=0) {
				if(map[ny][nx]==2) return false;
				j++;
				ny = y+dy[i]*j;
				nx = x+dx[i]*j;
			}
		}
		return true;
	}
}
