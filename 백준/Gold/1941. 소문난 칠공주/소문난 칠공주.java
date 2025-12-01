//package BOJ.소문난칠공주;

import java.io.*;
import java.util.*;

public class Main {
	static int N = 5, res;
	static boolean[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				if (input.charAt(j)=='S') map[i][j] = true;
			}
		}
		
		res = 0;
		dfs(0, 0, new int[7], 0, 0);
		System.out.println(res);
	}
	
	public static void dfs(int depth, int start, int[] choice, int ssum, int ysum) {
		if(ysum>=4) return;
		if (depth==7) {
//			System.out.println(Arrays.toString(choice));
			if(bfs(choice)) res++;
			return;
		}
		for (int i=start; i<25; i++) {
			choice[depth] = i;
			int y = i/N;
			int x = i%N;
			dfs(depth+1, i+1, choice, ssum+(map[y][x]?1:0), ysum+(map[y][x]?0:1));
		}
		
	}
	
	public static boolean bfs(int[] choice) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N];
		int sy = choice[0]/N;
		int sx = choice[0]%N;
		que.add(new int[] {sy, sx});
		v[sy][sx] = true;
		
		int count = 7;
		while (!que.isEmpty()) {
			int[] now = que.poll();
			
			int y = now[0];
			int x = now[1];
			count--;
			
			for (int i=0; i<4; i++) {
				int ny=y+dy[i];
				int nx=x+dx[i];
				
				if (ny<0 || ny>=N || nx<0 || nx>=N || v[ny][nx]) continue;
				int key = ny*N+nx;
				boolean find = false;
				for (int j=0; j<7; j++) {
					if(choice[j]==key) {
						find = true;
						break;
					}
				}
				if(!find) continue;
				v[ny][nx]=true;
				que.add(new int[] {ny, nx});
			}
		}
		if(count==0) return true;
		return false;
		
	}
}

/*
	7명의 학생으로 구성
	가로나 세로로 인접
	이다솜파가 더 많아야 함
*/