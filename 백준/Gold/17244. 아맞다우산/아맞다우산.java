//package BOJ.아맞다우산;

import java.io.*;
import java.util.*;

public class Main{
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int startY = 0;
		int startX = 0;
		int endY = 0;
		int endX = 0;
		int cnt = 1;
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				char c = input.charAt(j);
				if (c=='S') {
					startY = i;
					startX = j;
				} else if (c=='E') {
					endY = i;
					endX = j;
				} else if(c=='X') {
					map[i][j] = cnt++;
				} else if(c=='#') {
					map[i][j] = -1;
				}
			}
		}
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {startY, startX, 0, 0});
		boolean[][][] v = new boolean[N][M][1<<cnt];
		v[startY][startX][0] = true;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dis = now[2];
			int key = now[3];
			
//			System.out.println(y+ " " + x + " " + dis + " " + key);
			if (y==endY && x==endX) {
//				System.out.println(key);
				if (key == (1<<(cnt-1))-1){
					System.out.println(dis);
					break;
				}
//				System.out.println(key);
			}
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
//				System.out.println("test");
				if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]==-1) continue;
				if (map[ny][nx] > 0) {
					int nk = 1<<(map[ny][nx]-1) | key;
					if (!v[ny][nx][nk]) {
						v[ny][nx][nk] = true;
						que.add(new int[] {ny, nx, dis+1, nk});
					}
				} else {
					if (!v[ny][nx][key]) {
						v[ny][nx][key] = true;
						que.add(new int[] {ny, nx, dis+1, key});
					}
				}
			}
		}
	}
}
/*
	가장 긴 조각을 계속 자르면 됨
	중간에 가장 가까운 조각을 자르기?
*/