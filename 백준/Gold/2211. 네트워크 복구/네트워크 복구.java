//package BOJ.네트워크복구;

import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]>[] g = new List[N+1]; for(int i=1; i<=N; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			g[s].add(new int[] {e,c});
			g[e].add(new int[] {s,c});
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
		pq.add(new int[] {1, 0});
		
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[1] = 0;
		
		parent = new int[N+1];
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			int node = now[0];
			int dis = now[1];
			
			if (dis>distance[node]) continue;
			
			for (int[] nxt : g[node]) {
				if (distance[nxt[0]] > dis+nxt[1]) {
					parent[nxt[0]] = node;
					distance[nxt[0]] = dis+nxt[1];
					pq.add(new int[] {nxt[0], nxt[1]+dis});
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(N-1).append("\n");
		for (int i=2; i<=N; i++) {
			sb.append(i).append(" ").append(parent[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
