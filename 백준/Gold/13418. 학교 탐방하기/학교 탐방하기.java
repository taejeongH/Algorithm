//package BOJ.학교탐방하기;

import java.io.*;
import java.util.*;

public class Main{
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]>[] g = new List[N+1]; for (int i=0; i<=N; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[s].add(new int[] {e, c});
			g[e].add(new int[] {s, c});
		}
		
		//최악의 경우 -> 0이 많은 경우 (최소 경로)
		int[] prim = new int[N+1];
		Arrays.fill(prim, INF);
		boolean[] v = new boolean[N+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
		//0오르막길 1내리막길
		pq.add(new int[] {0, 0});
		prim[0] = 0;
		int mst = 0;
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int node = now[0];
			int dis = now[1];
			if(prim[node] < dis) continue;
			mst += dis;
			if(++cnt == N+1) break;
			v[node] = true;
			for (int[] nxt : g[node]) {
				if (!v[nxt[0]] && prim[nxt[0]] > nxt[1]) {
					prim[nxt[0]] = nxt[1];
					pq.add(new int[] {nxt[0], nxt[1]});
				}
			}
			
		}
		int max = (int) Math.pow(cnt-mst-1, 2);
		
		//최적의 경로 -> 1이 많은 경우, 최대 경로
		prim = new int[N+1];
		Arrays.fill(prim, -1);
		pq = new PriorityQueue<>((o1, o2) -> o2[1]-o1[1]);
		pq.add(new int[] {0, 0});
		prim[0] = 0;
		v = new boolean[N+1];
		
		mst = 0;
		cnt = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int node = now[0];
			int dis = now[1];
			if(prim[node] > dis) continue;
			mst += dis;
			if(++cnt == N+1) break;
			v[node] = true;
			for (int[] nxt : g[node]) {
				if (!v[nxt[0]] && prim[nxt[0]] < nxt[1]) {
					prim[nxt[0]] = nxt[1];
					pq.add(new int[] {nxt[0], nxt[1]});
				}
			}
		}
        int min = (int) Math.pow((cnt-mst-1), 2);
		System.out.println(max - min);
	}
	
}