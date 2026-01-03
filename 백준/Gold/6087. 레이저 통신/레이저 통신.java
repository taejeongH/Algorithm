//package BOJ.레이저통신;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		int startY = -1;
		int startX = -1;
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(startY==-1 && map[i][j]=='C') {
					startY = i;
					startX = j;
					map[i][j] = '.';
				}
			}
		}
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
		int[][][] distance = new int[N][M][4];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) Arrays.fill(distance[i][j], Integer.MAX_VALUE);
		}
		for (int i=0; i<4; i++) {
			distance[startY][startX][i] = 0;
			int ny = startY+dy[i];
			int nx = startX+dx[i];
			if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]=='*') continue;
			que.add(new int[] {ny, nx, 0, i});
			distance[ny][nx][i] = 0;
		}
		
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dis = now[2];
			int dir = now[3];
//			System.out.println(y + " " + x);
			
			if(distance[y][x][dir]<dis) continue;
			if(map[y][x] == 'C') {
				System.out.println(dis);
				break;
			}
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if (ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]=='*') continue;
				if (distance[ny][nx][i] > dis + (dir==i?0:1)) {
					distance[ny][nx][i] = dis + (dir==i?0:1);
					que.add(new int[] {ny, nx, distance[ny][nx][i], i});
				}
			}
		}
		
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(distance[i]));
	}
}
