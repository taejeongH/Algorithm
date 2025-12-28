//package BOJ.친구비;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //학생수
		int M = Integer.parseInt(st.nextToken()); //친구관계수
		int K = Integer.parseInt(st.nextToken()); //가지고있는돈
		
		List<Integer>[] g = new List[N+1]; for (int i=0; i<=N; i++) g[i] = new ArrayList<>();
		
		int[] price = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			g[s].add(e);
			g[e].add(s);
		}
		
		
		int[] parent = new int[N+1];
		int[] distance = new int[N+1];
		ArrayDeque<Integer> que = new ArrayDeque<>();
		Arrays.fill(distance, Integer.MAX_VALUE);
		for (int i=1; i<=N; i++) {
			que.add(i);
			if(distance[i] > price[i]) {
				distance[i] = price[i];
				parent[i] = 0;
			}
			while(!que.isEmpty()) {
				int node = que.poll();
				
				if(distance[node] < price[i]) continue;
				
				for (int nxt : g[node]) {
					if (distance[nxt] > price[i]) {
						parent[nxt] = node;
						distance[nxt] = price[i];
						que.add(nxt);
					}
				}
			}
		}
		
		
		int res = 0;
		for (int i=1; i<=N; i++) {
			if (parent[i]==0) res+=distance[i];
		}
		
//		System.out.println(Arrays.toString(distance));
		if(res > K) System.out.println("Oh no");
		else System.out.println(res);
		
	}
}
