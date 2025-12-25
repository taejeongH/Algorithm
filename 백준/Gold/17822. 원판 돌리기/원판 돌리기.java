//package BOJ.원판돌리기;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //원판 개수 반지름 : 1, 2, ... ,N
		M = Integer.parseInt(st.nextToken()); //원판에 적혀있는 정수의 개수
		int T = Integer.parseInt(st.nextToken()); //원판 회전 
		
		int[][] map = new int[N+1][M];
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
//			System.out.println();
//			System.out.println(t + "회전 전");
//			for (int i=1; i<=N; i++) System.out.println(Arrays.toString(map[i]));
			//원판 회전
			for (int i=x; i<=N; i+=x) {
				rotate(map[i], d, k);
			}
//			System.out.println();
//			System.out.println(t + "회전 후");
//			for (int i=1; i<=N; i++) System.out.println(Arrays.toString(map[i]));
			
			remove(map);
//			System.out.println();
//			System.out.println(t + "지운 후");
//			for (int i=1; i<=N; i++) System.out.println(Arrays.toString(map[i]));
		}
		
		int res = 0;
		for (int i=1; i<=N; i++) {
			for (int j=0; j<M; j++) {
				res += map[i][j];
			}
		}
		System.out.println(res);
	}
	public static void rotate(int[] map, int d, int k) {
		int[] tmp = new int[M];
		if (d==0) {
			//시계 방향
			for (int i=0; i<M; i++) {
				tmp[(i+k)%M] = map[i];
			}
		} else {
			//반시계 방향
			for (int i=0; i<M; i++) {
				int idx = (i-k);
				if (idx<0) idx += M;
				tmp[idx] = map[i];
			}
		}
		for (int i=0; i<M; i++) {
			map[i] = tmp[i];
		}
	}
	
	public static void remove(int[][] map) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		ArrayDeque<int[]> store = new ArrayDeque<>();
		boolean[][] v = new boolean[N+1][M];
		
		boolean isChange = false;
		int sum = 0;
		int cnt = 0;
		for (int i=1; i<=N; i++) {
			for (int j=0; j<M; j++) {
				if (v[i][j] || map[i][j]==0) continue;
				cnt++;
				sum += map[i][j];
				int cur = map[i][j];
				que.add(new int[] {i, j});
				v[i][j] = true;
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					store.add(new int[] {y, x});
					
					for (int k=0; k<4; k++) {
						int ny = y+dy[k];
						int nx = (x+dx[k])%M;
						if(nx<0) nx+=M;
						
						if(ny<1 || ny>N) continue;
						
						if(map[ny][nx]!=cur || v[ny][nx]) continue;
						
						v[ny][nx] = true;
						que.add(new int[] {ny, nx});
					}
				}
				
				if (store.size()>1) {
					isChange = true;
					while(!store.isEmpty()) {
						int[] now = store.poll();
						int y = now[0];
						int x = now[1];
						
						map[y][x] = 0;
					}
				} else {
					while(!store.isEmpty()) {
						store.poll();
					}
				}
			}
		}
		
		if (isChange) return;
		
		
		for (int i=1; i<=N; i++) {
			for (int j=0; j<M; j++) {
				if(map[i][j]==0) continue;
				if (map[i][j]*cnt > sum) {
					map[i][j]--;
				} else if(map[i][j]*cnt < sum){
					map[i][j]++;
				}
			}
		}
		
	}
	
	
}
