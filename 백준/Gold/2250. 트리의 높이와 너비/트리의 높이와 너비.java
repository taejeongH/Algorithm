//package BOJ.트리의높이와너비;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] width;
	static int[][] g;
	static int[][] level;
	static int idx;
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		g = new int[N+1][2];
		level = new int[N+1][2];
		for (int i=0; i<=N; i++) {
			level[i][0] = INF;
		}
		width = new int[N+1];
		
		boolean[] isChild = new boolean[N+1];
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			g[cur][0] = left;
			g[cur][1] = right;
			if (left!=-1) {
				isChild[left] = true;
			}
			
			if(right!=-1) {
				isChild[right] = true;
			}
		}
		
		int root = 1;
		for (int i=1; i<=N; i++) {
			if (!isChild[i]) {
				root = i;
				break;
			}
		}
		
		idx = 1;
		dfs(root, 1);
		int resW = 0;
		int resL = 0;
		for (int i=1; i<=N; i++) {
			if (level[i][0] == INF) continue;
			int w = level[i][1]-level[i][0]+1;
			if (resW < w) {
				resW = w;
				resL = i;
			}
		}
		System.out.println(resL + " " + resW);
	}
	
	public static void dfs(int node, int dis) {
		if(node==-1) return;
		
		dfs(g[node][0], dis+1);
		width[node] = idx++;
		level[dis][0] = Math.min(width[node], level[dis][0]);
		level[dis][1] = Math.max(width[node], level[dis][1]);
		dfs(g[node][1], dis+1);
	}
}