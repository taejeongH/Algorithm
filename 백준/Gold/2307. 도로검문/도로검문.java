//package BOJ.도로검문;

import java.io.*;
import java.util.*;

public class Main{
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]>[] g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[s].add(new int[] {e, c});
			g[e].add(new int[] {s, c});
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		int[] parent = new int[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, INF);
		pq.add(new int[] {1, 0});
		distance[1] = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int node = now[0];
			int dis = now[1];
			
			if(dis > distance[node]) continue;
			
			for (int[] nxt : g[node]) {
				if (distance[nxt[0]] > dis+nxt[1]) {
					distance[nxt[0]] = dis+nxt[1];
					parent[nxt[0]] = node;
					pq.add(new int[] {nxt[0], dis+nxt[1]});
				}
			}
		}
		
		int org = distance[N];
		int res = 0;
		
		int e = N;
		int s = parent[e];
		while (s != 0) {
			
			pq.add(new int[] {1, 0});
			Arrays.fill(distance, INF);
			distance[1] = 0;
			
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int node = now[0];
				int dis = now[1];
				
				if(dis > distance[node]) continue;
				
				for (int[] nxt : g[node]) {
					if((node == s && nxt[0] == e) || (node == e && nxt[0] == s)) continue;
					if (distance[nxt[0]] > dis+nxt[1]) {
						distance[nxt[0]] = dis+nxt[1];
						parent[nxt[0]] = node;
						pq.add(new int[] {nxt[0], dis+nxt[1]});
					}
				}
			}
			res = Math.max(distance[N], res);
			
			e = s;
			s = parent[e];
			
		}
		System.out.println(res==INF?-1:res-org);
	}
	
}
