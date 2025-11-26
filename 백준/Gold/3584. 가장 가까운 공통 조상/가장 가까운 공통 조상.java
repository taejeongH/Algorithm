//package BOJ.가장가까운공통조상;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] g = new int[N+1];
			for (int i=0; i<N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				g[c] = p;
			}
			
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			
			int[] v = new int[N+1];
			ArrayDeque<Integer> que = new ArrayDeque<>();
			que.add(s1);
			que.add(s2);
			while (!que.isEmpty()) {
				int now = que.poll();
				
				if (++v[now]==2) {
					sb.append(now).append("\n");
					break;
				}
				
				if(g[now]!=0) {
					que.add(g[now]);
				}
			}
		}
		System.out.print(sb);
	}
}

/*
	트리가 주어질 때 두 노드의 가장 가까운 공통 조상을 찾는 문제
	
	공통 조상 : 가장 가까우면서 공통된 조상
	자식 -> 부모로 그래프를 만들면?
	
	방문 지점이 최초로 2개가 되는 지점이 공통 조상...
	
	
*/

