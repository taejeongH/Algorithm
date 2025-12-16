//package BOJ.스도쿠;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] colVisited;
	static boolean[][] rowVisited;
	static boolean[][][] crossCheck;
	static boolean isChecked;
	static ArrayList<int[]> calPos; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
		N = 9;
		map = new int[N][N];
		colVisited = new boolean[N][N+1];
		rowVisited = new boolean[N][N+1];
		crossCheck = new boolean[N/3][N/3][N+1];
		M = 0;
		calPos = new ArrayList<>();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				crossCheck[i/3][j/3][map[i][j]] = true;
				if(map[i][j] != 0) {
					colVisited[i][map[i][j]] = true;
					rowVisited[j][map[i][j]] = true;
				}
				else {
					calPos.add(new int[] {i, j});
					M++;
				}
			}
		}
		isChecked = false;
		bt(0);
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	public static void bt(int num) {
		if (num == M) {
			isChecked = true;
			return;
		}
		
		boolean cant = true;
		for (int k=1; k<=N; k++) {
			int i = calPos.get(num)[0];
			int j = calPos.get(num)[1];
			if (!colVisited[i][k] && !rowVisited[j][k] && !crossCheck[i/3][j/3][k]) {
				map[i][j] = k;
				colVisited[i][k] = true;
				rowVisited[j][k] = true;
				crossCheck[i/3][j/3][k] = true;
				bt(num+1);
				if(isChecked) return;
				map[i][j] = 0;
				colVisited[i][k] = false;
				rowVisited[j][k] = false;
				crossCheck[i/3][j/3][k] = false;
				cant = false;
			}
		}
		if(cant) return;
	}
}


/*
103000509
002109400
000704000
300502006
060000050
700803004
000401000
009205800
804000107
*/