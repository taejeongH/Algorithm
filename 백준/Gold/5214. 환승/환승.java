//package BOJ.환승;

import java.io.*;
import java.util.*;

public class Main{
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] g = new List[N+1]; for(int i=1; i<=N; i++) g[i] = new ArrayList<>();
		HashSet<Integer>[] hyper = new HashSet[M+1]; for(int i=1; i<=M; i++) hyper[i] = new HashSet<>();
		for (int i=1; i<=M; i++) {
			
			st = new StringTokenizer(br.readLine());

			for (int j=0; j<K; j++) {
				int node = Integer.parseInt(st.nextToken());
				g[node].add(i);
				hyper[i].add(node);
			}
		}
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
		int[] distance = new int[N+1];
		Arrays.fill(distance, INF);
		distance[1] = 0;
		boolean[] v = new boolean[M+1];
		for (int i=0; i<g[1].size(); i++) {
			que.add(new int[] {1, g[1].get(i), 1});
		}
		
		
		int res = -1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int node = now[0];
			int hyperId = now[1];
			int dis = now[2];
			if (node == N) {
				res = dis;
				break;
			}
			
			
			for (int nxtId : g[node]) {
				if(v[nxtId]) continue;
				v[nxtId] = true;
				for (int nxt : hyper[nxtId]) {
					if (distance[nxt] > dis+1) {
						distance[nxt] = dis+1;
						que.add(new int[] {nxt, nxtId, dis+1});
					}
				}
			}
		}
		System.out.println(res);
	}
	
}