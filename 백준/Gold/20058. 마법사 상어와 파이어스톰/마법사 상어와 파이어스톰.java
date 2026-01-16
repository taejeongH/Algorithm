//package BOJ.마법사상어와파이어스톰;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] tmp;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = (int) Math.pow(2,Integer.parseInt(st.nextToken()));
		int Q = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		tmp = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			change(map, L);
			minus(map);
		}
		
		
		int cnt = 0;
		int big = 0;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(v[i][j] || map[i][j]==0) continue;
				
				que.add(new int[] {i, j});
				v[i][j] = true;
				int mass = 0;
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					
					cnt+= map[y][x];
					mass++;
					
					for (int k=0; k<4; k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						if (ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx]==0 || v[ny][nx]) continue;
						v[ny][nx] = true;
						que.add(new int[] {ny, nx});
					}
				}
				big = Math.max(mass, big);
			}
		}
		
		System.out.println(cnt);
		System.out.println(big);
	}
	public static void minus(int[][] map) {
		ArrayDeque<int[] > que = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]==0) continue;
				int cnt = 0;
				for (int k=0; k<4; k++) {
					int ny = i+dy[k];
					int nx = j+dx[k];
					if (ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx]==0) continue;
					cnt++;
				}
				if (cnt<3) {
					que.add(new int[] {i, j});
				}
			}
		}
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			map[y][x]--;
		}
	}
	
	
	public static void rotate(int[][] map, int y, int x, int size) {
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[y + j][x + size - 1 - i] = map[y + i][x + j];
            }
        }
		
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[y + i][x + j] = tmp[y + i][x + j];
            }
        }
	}
	
	public static void change(int[][] map, int L) {
		int size = (int) Math.pow(2, L);
		for (int i=0; i<N; i+=size) {
			for (int j=0; j<N; j+=size) {
				rotate(map, i, j, size);
			}
		}
	}
}
