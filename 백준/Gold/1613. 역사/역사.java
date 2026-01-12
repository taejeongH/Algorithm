//package BOJ.역사;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Integer>[] g = new List[N+1]; for(int i=1; i<=N; i++) g[i] = new ArrayList<>();
		int[] inDegree = new int[N+1];
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			g[s].add(e);
			inDegree[e]++;
		}
		
		ArrayDeque<Integer> que = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			if (inDegree[i]==0) {
				que.add(i);
			}
		}
		
		boolean[][] before = new boolean[N+1][N+1];
		
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for (int nxt : g[now]) {
				
				for (int i=1; i<=N; i++) {
					if (before[i][now]) {
						before[i][nxt] = true;
					}
				}
				
				before[now][nxt]=true;
				
				if(--inDegree[nxt]==0) {
					que.add(nxt);
				}
			}
		}
		
		int S = Integer.parseInt(br.readLine());
		StringBuilder res = new StringBuilder();
		for (int i=0; i<S; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(before[s][e]) res.append(-1).append("\n");
			else if (before[e][s]) res.append(1).append("\n");
			else res.append(0).append("\n");
		}
		System.out.println(res);
	}
}
