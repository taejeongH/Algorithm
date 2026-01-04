//package BOJ.불켜기;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]>[][] g = new List[N+1][N+1];
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				g[i][j] = new ArrayList<>();
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			g[startY][startX].add(new int[] {endY, endX});
		}
		
		boolean[][] map = new boolean[N+1][N+1];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {1, 1});
		boolean[][] v = new boolean[N+1][N+1];
		v[1][1] = true;
		map[1][1] = true;
		
		int res = 1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			
			for (int[] nxt: g[y][x]) {
				if(!map[nxt[0]][nxt[1]]) {
					map[nxt[0]][nxt[1]] = true;
					v = new boolean[N+1][N+1];
					v[y][x] = true;
					res++;
				}
			}
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(ny<1 || ny>N || nx<1 || nx>N || !map[ny][nx] || v[ny][nx]) continue;
				
				v[ny][nx] = true;
				que.add(new int[] {ny, nx});
			}
		}

		System.out.println(res);
	}
}
