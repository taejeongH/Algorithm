//package BOJ.달려라홍준;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] map = new int[N];
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<2*M-1; i++) {
			while (!que.isEmpty() && que.peekLast()[1] <= map[i]) {
				que.pollLast();
			}
			que.add(new int[] {i, map[i]});
		}
		sb.append(que.peek()[1]).append(" ");
		for (int i=2*M-1; i<N; i++) {
			while (!que.isEmpty() && que.peekFirst()[0]<=i-(2*M-1)) {
				que.poll();
			}
			
			while (!que.isEmpty() && que.peekLast()[1] <= map[i]) {
				que.pollLast();
			}
			que.add(new int[] {i, map[i]});
			sb.append(que.peek()[1]).append(" ");
		}
		System.out.println(sb);
	}
}
