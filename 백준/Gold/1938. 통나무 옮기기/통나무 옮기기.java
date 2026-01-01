//package BOJ.통나무옮기기;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] dx = { {-1, 0, 1}, {-1, 0, 1}, {1, 1, 1}, {-1, -1, -1} };
	static int[][] dy = { {-1, -1, -1}, {1, 1, 1}, {-1, 0, 1}, {-1, 0, 1} };
	
	static int[] dx2 = {-1, 1, 0, 0};
	static int[] dy2 = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] map = new boolean[N][N];
		
		int startYSum = 0;
		int startXSum = 0;
		int endYSum= 0;
		int endXSum= 0;
		int BY = 0;
		int EY = 0;
		int startStatus = 0;
		int endStatus = 0;
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				if (input.charAt(j) == '1') {
					map[i][j] = true;
				} else if(input.charAt(j) == 'B') {
					if(startYSum!=0 && BY!=i) startStatus = 1;
					startYSum += i;
					startXSum += j;
					BY = i;
				} else if(input.charAt(j) == 'E') {
					if(endYSum!=0 && EY!=i) endStatus = 1;
					endYSum += i;
					endXSum +=j;
					EY = i;
				}
			}
		}
		
		int startY = startYSum/3;
		int startX= startXSum/3;
		int endY = endYSum/3;
		int endX = endXSum/3;
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {startY, startX, startStatus, 0});
		
		boolean[][][] v = new boolean[N][N][2];
		v[startY][startX][startStatus] = true;
		
		int res = 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int status = now[2];
			int dis = now[3];
			
//			System.out.println(y+ " " + x + " " + status);
			
			if(y==endY && x==endX && status==endStatus) {
				res = dis;
				break;
			}
			
			if(status == 0) {
				boolean canTurn = true;
				for (int i=0; i<2; i++) {
					boolean canMove = true;
					for (int j=0; j<3; j++) {
						int ny = y+dy[i][j];
						int nx = x+dx[i][j];
						
						if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx]) {
							canMove = false;
							canTurn = false;
							break;
						}
					}
					
					if(canMove) {
						int ny = y+dy[i][0];
						if (!v[ny][x][status]) {
							v[ny][x][status] = true;
							que.add(new int[] {ny, x, status, dis+1});
						}
					}
				}
				
				if(canTurn) {
					if(!v[y][x][Math.abs(status-1)]) {
						v[y][x][Math.abs(status-1)] = true;
						que.add(new int[] {y, x, Math.abs(status-1), dis+1});
					}
				}
				
				//좌 우
				for (int i=0; i<2; i++) {
					int ny = y+(dy2[i]*2);
					int nx = x+(dx2[i]*2);
					
					if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx]) continue;
					
					ny = y+dy2[i];
					nx = x+dx2[i];
					if(v[ny][nx][status]) continue;
					v[ny][nx][status] = true;
					que.add(new int[] {ny, nx, status, dis+1});
				}
				
			} else {
				boolean canTurn = true;
				for (int i=2; i<4; i++) {
					boolean canMove = true;
					for (int j=0; j<3; j++) {
						int ny = y+dy[i][j];
						int nx = x+dx[i][j];
						
						if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx]) {
							canMove = false;
							canTurn = false;
							break;
						}
					}
					
					if(canMove) {
						int nx = x+dx[i][0];
						if (!v[y][nx][status]) {
							v[y][nx][status] = true;
							que.add(new int[] {y, nx, status, dis+1});
						}
					}
				}
				
				if(canTurn) {
					if(!v[y][x][Math.abs(status-1)]) {
						v[y][x][Math.abs(status-1)] = true;
						que.add(new int[] {y, x, Math.abs(status-1), dis+1});
					}
				}
				
				for (int i=2; i<4; i++) {
					int ny = y+(dy2[i]*2);
					int nx = x+(dx2[i]*2);
					
					if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx]) continue;
					
					ny = y+dy2[i];
					nx = x+dx2[i];
					if(v[ny][nx][status]) continue;
					v[ny][nx][status] = true;
					que.add(new int[] {ny, nx, status, dis+1});
				}
			}
			
		}
		
		System.out.println(res);
		
	}
}
