//package BOJ.서울지하철2호선;

import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] g;
	static boolean[] isCycle;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			g[e].add(s);
		}
		
		isCycle = new boolean[N+1];
		
		int[] degree = new int[N+1];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
		    degree[i] = g[i].size();
		    if (degree[i] == 1) q.add(i);
		}

		while (!q.isEmpty()) {
		    int cur = q.poll();
		    for (int nxt : g[cur]) {
		        if (--degree[nxt] == 1) {
		            q.add(nxt);
		        }
		    }
		}

		for (int i=1; i<=N; i++) {
		    if (degree[i] >= 2) isCycle[i] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		for (int i=1; i<=N; i++) {
			if (isCycle[i]) {
				que.add(new int[] {i, 0});
				distance[i] = 0;
			}
		}
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int node = now[0];
			int dis = now[1];
			
			for (int nxt : g[node]) {
				if(dis+1 >= distance[nxt]) continue;
				distance[nxt] = dis+1;
				que.add(new int[] {nxt, dis+1});
			}
		}
		
		for (int i=1; i<=N; i++) {
			sb.append(distance[i]).append(" ");
		}
		System.out.println(sb);
	}
}
