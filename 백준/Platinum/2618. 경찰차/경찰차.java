//package BOJ.경찰차;

import java.io.*;
import java.util.*;

public class Main {
	static int N, W;
	static int[][] map, dp, choice;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		W = Integer.parseInt(br.readLine());
		map = new int[W+1][2];
		for (int i=1; i<=W; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		choice = new int[W+1][W+1];
		dp = new int[W+1][W+1];
		for (int i=0; i<W+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(0, 0));
		reconstruct();
	}
	
	public static int dfs(int posA, int posB) {
		if (posA == W || posB == W) {
			return 0;
		}
		if(dp[posA][posB] != -1) return dp[posA][posB];
		
		int depth = Math.max(posA, posB) + 1;
		
		int r1 = dfs(posA, depth);
		if (posB==0) {
			r1 += Math.abs(N - map[depth][0]) + Math.abs(N - map[depth][1]);
		} else {
			r1 += Math.abs(map[posB][0] - map[depth][0]) + Math.abs(map[posB][1] - map[depth][1]);
		}
		
		int r2 = dfs(depth, posB);  
		if (posA==0) {
			r2 += Math.abs(1 - map[depth][0]) + Math.abs(1 - map[depth][1]);
		} else {
			r2 += Math.abs(map[posA][0] - map[depth][0]) + Math.abs(map[posA][1] - map[depth][1]);
		}
		
		if(r2<=r1) {
			choice[posA][posB] = 1;
		} else {
			choice[posA][posB] = 2;
		}
		
		return dp[posA][posB] = Math.min(r1, r2);
	}
	
    public static void reconstruct() {
        int a = 0, b = 0;

        for (int depth = 1; depth <= W; depth++) {
            int c = choice[a][b];
            System.out.println(c);

            if (c == 1) {
                a = depth;
            } else {
                b = depth;
            }
        }
    }
}
