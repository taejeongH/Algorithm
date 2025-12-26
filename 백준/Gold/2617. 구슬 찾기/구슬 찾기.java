//package BOJ.구슬찾기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			g[e].add(s);
		}
		
		ArrayDeque<Integer> que = new ArrayDeque<>();
		
		int[] in = new int[N+1];
		int[] out = new int[N+1];
		for (int i=1; i<=N; i++) {
			boolean[] v = new boolean[N+1];
			que.add(i);
			v[i] = true;
			int sum = 0;
			while(!que.isEmpty()) {
				int now = que.poll();
				
				if (now!=i) in[now]++;
				
				for (int nxt : g[now]) {
					if(v[nxt]) continue;
					v[nxt] = true;
					que.add(nxt);
					sum++;
				}
			}
			out[i] += sum;
		}
		
		int res = 0;
		for (int i=1; i<=N; i++) {
			if (in[i] > N/2 || out[i] > N/2) res++;
		}
		System.out.println(res);
	}
}
