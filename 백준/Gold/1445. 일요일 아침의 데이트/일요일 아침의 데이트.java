//package BOJ.일요일아침의데이트;

import java.io.*;
import java.util.*;

public class Main{
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int sy = 0;
		int sx = 0;
		int ey = 0;
		int ex = 0;
		
		int[][] map = new int[N][M];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				char c = input.charAt(j);
				if (c=='F') {
					ey = i;
					ex = j;
					map[i][j] = 4;
				} else if (c=='S') {
					sy = i;
					sx = j;
					map[i][j] = 5;
				} else if(c=='g') {
					map[i][j] = 1;
					for (int k=0; k<4; k++) {
						int ny = i+dy[k];
						int nx = j+dx[k];
						
						if(ny<0|| ny>=N || nx<0 || nx>=M || map[ny][nx] != 0) continue;
						map[ny][nx] = 2;
					}
				}
			}
		}
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2]==o2[2]) return o1[3]-o2[3];
				return o1[2]-o2[2];
			}
		});
		
		pq.add(new int[] {sy, sx, 0, 0}); //쓰레기를 지나간 횟수, 쓰레기 옆을 지나간 횟수
		int[][] dis = new int[N][M];
		int[][] sedis = new int[N][M];
		for (int i=0; i<N; i++) {
			Arrays.fill(dis[i], INF);
			Arrays.fill(sedis[i], INF);
		}
		
		dis[sy][sx] = 0;
		sedis[sy][sx] = 0;
		
		int res1= 0;
		int res2 = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int y = now[0];
			int x = now[1];
			int flower = now[2];
			int nextFlower = now[3];
			
			if(dis[y][x] < flower || (dis[y][x]==flower && sedis[y][x] < nextFlower)) continue;
			
			
			if(y == ey && x == ex) {
				res1 = flower;
				res2 = nextFlower;
				break;
			}
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(ny<0|| ny>=N || nx<0 || nx>=M) continue;
				
				int nf = flower + (map[ny][nx]==1?1:0);
				int nsf = nextFlower + (map[ny][nx]==2?1:0);
				
				if (dis[ny][nx] > nf || (dis[ny][nx] == nf && sedis[ny][nx] > nsf)) {
					dis[ny][nx] = nf;
					sedis[ny][nx] = nsf;
					pq.add(new int[] {ny, nx, nf, nsf});
				}
			}
		}
		System.out.println(res1 + " " + res2);
	}
	
}