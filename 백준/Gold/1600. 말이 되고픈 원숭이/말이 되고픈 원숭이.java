//package BOJ.말이되고픈원숭이;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static int[] dx2 = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy2 = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				if(Integer.parseInt(st.nextToken())==1) map[i][j]=true;
			}
		}
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {0, 0, 0, 0}); //y, x, 원숭이동작회수, 동작횟수
		
		int res = Integer.MAX_VALUE;
		int[][][] distance = new int[N][M][K+1];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				Arrays.fill(distance[i][j], Integer.MAX_VALUE);
			}
		}
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int monkey = now[2];
			int dis = now[3];
			
			if(y==N-1 && x==M-1) {
				res = Math.min(res, dis);
			}
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]) continue;
				if(distance[ny][nx][monkey] > dis+1) {
					distance[ny][nx][monkey] = dis+1;
					que.add(new int[] {ny, nx, monkey, dis+1});
				}
			}
			
			
			if (monkey < K) {
				monkey++;
				for (int i=0; i<8; i++) {
					int ny = y+dy2[i];
					int nx = x+dx2[i];
					
					if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]) continue;
					if(distance[ny][nx][monkey] > dis+1) {
						distance[ny][nx][monkey] = dis+1;
						que.add(new int[] {ny, nx, monkey, dis+1});
					}
				}
			}
			
		}
		System.out.println(res==Integer.MAX_VALUE?-1:res);
	}
}
