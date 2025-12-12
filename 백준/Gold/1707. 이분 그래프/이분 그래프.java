//package BOJ.이분그래프;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			List<Integer>[] g = new List[V+1]; for(int i=1; i<=V; i++) g[i] = new ArrayList<>();
			for (int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				g[s].add(e);
				g[e].add(s);
			}
			
			int[] color = new int[V+1];
			
			ArrayDeque<Integer> que = new ArrayDeque<>();
			int res = 1;
			br: for (int i=1; i<=V; i++) {
				if (color[i]!=0) continue;
				color[i] = 1;
				que.add(i);
				
				while(!que.isEmpty()) {
					int now = que.poll();
					for (int nxt: g[now]) {
						if (color[nxt]==0) {
							color[nxt] = color[now]==1?-1:1;
							que.add(nxt);
						} else {
							if (color[nxt]==color[now]) {
								res = 0;
								break br;
							}
						}
					}
				}
			}
			
//			System.out.println(Arrays.toString(color));
			
			
			sb.append(res==0?"NO":"YES").append("\n");
		}
		System.out.println(sb);
	}
}
