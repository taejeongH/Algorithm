//package BOJ.배열에서이동;

import java.io.*;
import java.util.*;

public class Main{
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[][] map;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//min~max의 범위를 지정해 놓고 이 범위 안에서 도달 가능한지 확인 -> 이분 탐색으로 범위를 줄여가며 최솟값 찾기
		int left = 0;
		int right = 200;
		
		int res = 0;
		while(left <= right) {
			int mid = (left+right)/2;
			
			//가능한지
			if(isExist(mid)) {
				res = mid;
				right = mid-1;
			} else {
				left = mid+1;
			}
		}
		System.out.println(res);
	}
	
	static boolean isExist(int diff) {
		for (int minVal=0; minVal<=200; minVal++) {
			int maxVal = minVal+diff;
			if(maxVal>200) break;
			
			if (map[0][0] < minVal || map[0][0] > maxVal) continue;
	        if (map[N-1][N-1] < minVal || map[N-1][N-1] > maxVal) continue;
	        
	        if(bfs(minVal, maxVal)) return true;
		}
		return false;
	}
	
	static boolean bfs(int minVal, int maxVal) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N];
		v[0][0] = true;
		que.add(new int[] {0, 0});
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			
			if (y==N-1 && x==N-1) return true;
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if (ny<0 || ny>=N || nx<0 || nx>=N || v[ny][nx]) continue;
				if(map[ny][nx] < minVal || map[ny][nx] > maxVal) continue;
				v[ny][nx] = true;
				que.add(new int[] {ny, nx});
			}
		}
		return false;
	}

}