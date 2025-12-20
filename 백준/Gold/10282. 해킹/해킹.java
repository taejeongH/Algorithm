//package BOJ.해킹;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int tc = Integer.parseInt(br.readLine());
		for (int t=1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			List<int[]>[] g = new List[N+1]; for (int i=0; i<=N; i++) g[i] = new ArrayList<>();
			for (int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				int end = Integer.parseInt(st.nextToken());
				int start = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				g[start].add(new int[] {end, cost});
			}
			
			PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
			que.add(new int[] {C, 0});
			int[] v = new int[N+1];
			Arrays.fill(v, Integer.MAX_VALUE);
			v[C] = 0;
			int count = 0;
			int totalTime = 0;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int node = now[0];
				int time = now[1];
				
				if(v[node]<time) continue;
				count++;
				totalTime = Math.max(time, totalTime);
				
				for (int[] nxt : g[node]) {
					if(v[nxt[0]] > time+nxt[1]) {
						v[nxt[0]] = time+nxt[1];
						que.add(new int[] {nxt[0], time+nxt[1]});
					}
				}
			}
			sb.append(count).append(" ").append(totalTime).append("\n");
		}
		System.out.println(sb);
	}
}
