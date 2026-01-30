//package BOJ.도로포장;

import java.io.*;
import java.util.*;

public class Main {
	static final long INF = Long.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<int[]>[] g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			g[s].add(new int[] {e, c});
			g[e].add(new int[] {s, c});
		}
		
		long[][] distance = new long[N+1][K+1];
		for (int i=0; i<=N; i++) Arrays.fill(distance[i], INF);
		
		PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
		pq.add(new long[] {1, 0, 0});
		distance[1][0] = 0;
		
		while(!pq.isEmpty()) {
			long[] now = pq.poll();
			int node = (int) now[0];
			long dis = now[1];
			int cnt = (int) now[2];
			
			if (distance[node][cnt] < dis) continue;
			
			for (int[] nxt : g[node]) {
				if (distance[nxt[0]][cnt] > dis+nxt[1]) {
					distance[nxt[0]][cnt] = dis+nxt[1];
					pq.add(new long[] {nxt[0], dis+nxt[1], cnt});
				}
				
				if(cnt < K && distance[nxt[0]][cnt+1] > dis) {
					distance[nxt[0]][cnt+1] = dis;
					pq.add(new long[] {nxt[0], dis, cnt+1});
				}
			}
		}
		long res = INF;
		for (int i=0; i<=K; i++) res = Math.min(res, distance[N][i]);
//		for (int i=1; i<=N; i++) System.out.println(Arrays.toString(distance[i]));
		System.out.println(res);
	}
}
