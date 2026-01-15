//package BOJ.마법사상어와복제;

import java.io.*;
import java.util.*;

public class Main{
	static class Fish {
		int y;
		int x;
		int dir;
		
		public Fish(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Fish [y=" + y + ", x=" + x + ", dir=" + dir + "]";
		}
	}
	
	static int N = 4;
	static int[] dx = {-1, -1 , 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int[] sharkDx = {0, -1, 0, 1};
	static int[] sharkDy = {-1, 0, 1, 0};
	
	static int[][] fishCount;
	static int maxCount;
	static int[] saveDir;
	static boolean[][] v;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		fishCount = new int[N][N];
		
		ArrayDeque<Fish> fishes = new ArrayDeque<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken())-1;
			
			fishes.add(new Fish(y, x, dir));
			fishCount[y][x]++;
		}
		
		st = new StringTokenizer(br.readLine());
		int sharkY = Integer.parseInt(st.nextToken())-1;
		int sharkX = Integer.parseInt(st.nextToken())-1;
		
		ArrayDeque<Fish> copy = new ArrayDeque<>();
		
		for (int t=1; t<=S; t++) {
			int iter = fishes.size();
			for (int k=0; k<iter; k++) {
				//물고기 복제
				Fish f = fishes.poll();
				copy.add(new Fish(f.y, f.x, f.dir));
				
				//물고기 이동
				int y = f.y;
				int x = f.x;
				int dir = f.dir;
				for (int i=0; i<8; i++) {
					int ny = y + dy[dir];
					int nx = x + dx[dir];
					
					
					if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx]>0 || (ny==sharkY && nx==sharkX)) {
						dir--;
						if(dir<0) dir += 8;
						continue;
					}
					
					fishCount[y][x]--;
					fishCount[ny][nx]++;
					f.y = ny;
					f.x = nx;
					f.dir = dir;
					break;
				}
				fishes.add(f);
			}
			//상어 이동하기
			maxCount = -1;
			saveDir = new int[3];
			v = new boolean[N][N];
			dfs(0, sharkY, sharkX, 0, new int[3]);
			for (int i=0; i<3; i++) {
				int ny = sharkY + sharkDy[saveDir[i]];
				int nx = sharkX + sharkDx[saveDir[i]];
				
				if (fishCount[ny][nx] > 0) {
					map[ny][nx] = t;
					fishCount[ny][nx] = 0;
				}
				sharkY = ny;
				sharkX = nx;
			}
			
			iter = fishes.size();
			for (int i=0; i<iter; i++) {
				Fish f = fishes.poll();
				if(map[f.y][f.x]==t) continue;
				fishes.add(f);
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (t-map[i][j]>=2) {
						map[i][j] = 0;
					}
				}
			}
			
			while(!copy.isEmpty()) {
				Fish f = copy.poll();
				fishes.add(f);
				fishCount[f.y][f.x]++;
			}
		}
		
		int res = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				res += fishCount[i][j];
			}
		}

		System.out.println(res);
	}
	
	public static void dfs(int depth, int y, int x, int cnt, int[] dir) {
		if (depth == 3) {
			if (cnt > maxCount) {
				maxCount = cnt;
				for (int i=0; i<3; i++) {
					saveDir[i] = dir[i];
				}
			}
			return;
		}
		
		for (int i=0; i<4; i++) {
			int ny = y+sharkDy[i];
			int nx = x+sharkDx[i];
			
			if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
			
			
			dir[depth] = i;
			if (v[ny][nx]) {
				dfs(depth+1, ny, nx, cnt, dir);
			} else {
				v[ny][nx] = true;
				dfs(depth+1, ny, nx, cnt+fishCount[ny][nx], dir);
				v[ny][nx] = false;
			}
		}
		
		//상, 좌, 하, 우
	}
}

/*
	(r, c) (1, 1)~(4,4)
	
	격자의 물고기 M마리
	이동방향 8방향 : 상하좌우, 대각선
	
	격자에는 물고기와 상어가 들어가 있으며, 같은 칸에 있을 수도 있음
	
	1. 상어가 모든 물고기에 복제 마법 시전, 5번에서 물고기가 복제되어 칸에 나타남
	2. 모든 물고기가 한 칸 이동. 
	 - 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로 이동 불가
	 - 이동할 수 있는 칸을 향할 때 까지 방향을 45도 반시계 회전
	 - 이동할 수 있는 칸이 없으면 이동 x
	3. 상어가 연속해서 3칸 이동
	 - 상어는 현재 칸에서 상하좌우 인접한 칸으로 이동 가능
	 - 연속해서 이동하는 칸 중에 격자의 범위를 벗어나는 칸이 있으면 이동 불가
	 - 상어가 이동하는 중에 물고기가 있으면, 물고기는 제외되며, 물고기는 냄새를 남김
	 - 가능한 이동 방법 중 물고기가 가장 많이 제외되는 방법으로 이동하며, 여러가지인 경우 사전 순으로 가장 앞서는 방법 이용
    4. 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라짐
    5. 1에서 사용한 복제마법 완료, 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 갖음
    
*/