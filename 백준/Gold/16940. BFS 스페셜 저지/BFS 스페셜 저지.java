//package BOJ.bfs스페셜저지;

import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			g[s].add(e);
			g[e].add(s);
		}
		
		int[] order = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		boolean[] v = new boolean[N+1];
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.add(1);
		v[1] = true;
		int idx = 2;
		int res = 1;
		nxt : while(!que.isEmpty()) {
			int now = que.poll();
			
			Set<Integer> child = new HashSet<>();
			
			for (int nxt : g[now]) {
				if(!v[nxt]) {
					child.add(nxt);
				}
			}
			
			for (int i=0; i<child.size(); i++) {
				if (!child.contains(order[idx])) {
					res = 0;
					break nxt;
				}
				v[order[idx]] = true;
				que.add(order[idx]);
				idx++;
			}
		}
		
		System.out.println(res);
	}
	
}