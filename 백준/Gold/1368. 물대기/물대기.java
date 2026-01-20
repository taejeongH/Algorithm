//package BOJ.물대기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[][] g = new int[N+1][N+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[1]-o2[1]);
		int[] distance = new int[N+1];
		for (int i=1; i<=N; i++) {
			distance[i] = Integer.parseInt(br.readLine());
			pq.add(new int[] {i, distance[i]});
		}
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] v = new boolean[N+1];
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			int node = now[0];
			int dis = now[1];
			if(dis > distance[node]) continue;
			v[node] = true;
			
			for (int i=1; i<=N; i++) {
				if (i==node) continue;
				if (!v[i] && distance[i] > g[node][i]) {
					distance[i] = g[node][i];
					pq.add(new int[] {i, g[node][i]});
				}
			}
		}
//		System.out.println(Arrays.toString(distance));
		int res = 0;
		for (int i=1; i<=N; i++) {
			res += distance[i];
		}
		System.out.println(res);
		
	}
}
