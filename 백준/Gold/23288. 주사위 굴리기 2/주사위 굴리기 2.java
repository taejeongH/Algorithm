//package BOJ.주사위굴리기2;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dx = {1, 0, -1, 0}; //동남서북 +1=90도회전
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//동쪽 -> col을 한칸 왼쪽으로 옮긴다 {1, 3, 6, 4} -> row[3] = col[3];, row[1]=col[1];
		//서쪽 -> col을 한칸 오른쪽으로 옮긴다 {6, 4, 1, 3} -> row[3] = col[3]; row[1]=col[1];
		//북쪽 -> row를 한칸 왼쪽으로 옮긴다 {1, 5, 6, 2} -> col[3] = row[3]; row[1] = col[1]; 
		//남쪽 -> row를 한칸 오른쪽으로 옮긴다 {
		int[] row = {2, 6, 5, 1};
		int[] col = {4, 6, 3, 1};
		int dir = 0; //동남서북
		int y = 0;
		int x = 0;
		int res = 0;
		for (int move=0; move<K; move++) {
			//이동 방향으로 1칸 이동
			int ny = y+dy[dir];
			int nx = x+dx[dir];
			
			if (ny<0 || ny>=N || nx<0 || nx>=M) {
				dir = (dir+2)%4;
				move--;
				continue;
			}
			
			if (dir==0) {
				//동쪽
				shift(col, true);
				row[1] = col[1];
				row[3] = col[3];
			} else if(dir==1) {
				//남쪽
				shift(row, true);
				col[1] = row[1];
				col[3] = row[3];
			} else if(dir==2) {
				//서
				shift(col, false);
				row[1] = col[1];
				row[3] = col[3];
			} else {
				//북
				shift(row, false);
				col[1] = row[1];
				col[3] = row[3];
			}
			int bottom = col[1];
			int num = map[ny][nx];
			
			//점수 획득.
			int count = calCount(ny, nx);
			res += count*num;
			
			if (bottom < num) {
				//반시계방향 90
				dir = (dir+3)%4;
			} else if (bottom > num) {
				//시계방향 90
				dir = (dir+1)%4;
			}
			y=ny;
			x=nx;
//			System.out.println(move + " " + (y+1) + " " + (x+1) + " " + (count*num));
//			System.out.println(Arrays.toString(row));
//			System.out.println(Arrays.toString(col));
		}
		System.out.println(res);
	}
	
	public static void shift(int[] map, boolean dir) {
		if (dir) {
			//왼쪾
			int tmp = map[0];
			for (int i=0; i<3; i++) {
				map[i] = map[i+1];
			}
			map[3] = tmp;
		} else {
			int tmp = map[3];
			for (int i=3; i>0; i--) {
				map[i] = map[i-1];
			}
			map[0] = tmp;
		}
	}
	
	public static int calCount(int i, int j) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][] v = new boolean[N][M];
		que.add(new int[] {i, j});
		v[i][j]=true;
		int cnt = 0;
		int num = map[i][j];
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			cnt++;
			
			for (int k=0; k<4; k++) {
				int ny = y+dy[k];
				int nx = x+dx[k];
				if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]!=num) continue;
				if(v[ny][nx]) continue;
				
				v[ny][nx]=true;
				que.add(new int[] {ny, nx});
			}
		}
		
		return cnt;
	}
}

/*
	N*M 지도 (1,1) ~ (N,M)
	
	오른쪽 동쪽, 위쪽 북쪽
	
	주사위의 초기 상태 : 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 상태, 좌표(1,1), 이동방향 동쪽
	
	1. 이동 방향으로 1칸 굴러감. 이동 방향에 칸이 없다면 이동 방향을 반대로 한 다음 한칸 굴러감
	2. 주사위가 도착한 칸에 대한 점수를 획득
	  - B의 숫자가 연속해서 있는 개수를 세서 곱한 값
	3. 주사위 아랫면에 있는 정수 A와 칸에 있는 정수 B를 비교해 이동 방향 결정
	  - A > B 인경우 이동방향 90도 시계방향 회전
	  - A < B 인경우 이동 방향을 90도 반시계방향 회전
	  - A = B 인경우 이동 방향 변화 x
*/