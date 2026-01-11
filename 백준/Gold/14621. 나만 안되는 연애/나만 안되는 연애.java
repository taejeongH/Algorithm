//package BOJ.나만안되는연애;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] isMan = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			if (st.nextToken().equals("M")) {
				isMan[i] = 1;
			}
		}
		List<int[]>[] g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			g[s].add(new int[] {e, c});
			g[e].add(new int[] {s, c});
		}
		
		int[] prim = new int[N+1];
		boolean[] v = new boolean[N+1];
		Arrays.fill(prim, INF);
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> o1[1]-o2[1]);
		pq.add(new int[] {1, 0, isMan[1]});
		prim[1] = 0;
		
		int mst = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int node = now[0];
			int dis = now[1];
			int man = now[2];
			
			if(v[node]) continue;
			if(prim[node] < dis)continue;
			
			v[node] = true;
			mst += dis;
			if(++cnt == N) break;
			
			for (int[] nxt : g[node]) {
				if (man == isMan[nxt[0]]) continue;
				if (prim[nxt[0]] <= nxt[1]) continue;
				prim[nxt[0]] = nxt[1];
				pq.add(new int[] {nxt[0], nxt[1], isMan[nxt[0]]});
			}
		}
		System.out.println(cnt!=N?-1:mst);
	}
}
