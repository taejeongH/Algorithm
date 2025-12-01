//package BOJ.최소비용구하기2;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<int[]>[] g = new List[N+1]; for(int i=1; i<=N; i++) g[i]=new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[s].add(new int[] {e, c});
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] parent = new int[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, INF);
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		que.add(new int[] {start, 0});
		distance[start] = 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int node = now[0];
			int dis = now[1];
			
			if(dis > distance[node]) continue;
			
			for (int[] nxt : g[node]) {
				if (distance[nxt[0]] > dis + nxt[1]) {
					distance[nxt[0]] = dis + nxt[1];
					parent[nxt[0]] = node;
					que.add(new int[] {nxt[0], dis + nxt[1]});
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(distance[end]).append("\n");
		Stack stk = new Stack<>();
		while (true) {
			stk.add(end);
			if(parent[end]==0) break;
			end = parent[end];
		}
		sb.append(stk.size()).append("\n");
		while(!stk.isEmpty()) {
			sb.append(stk.pop()).append(" ");
		}
		System.out.print(sb);
	}
}
