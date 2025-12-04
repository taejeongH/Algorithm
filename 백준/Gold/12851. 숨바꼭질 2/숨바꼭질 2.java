//package BOJ.숨바꼭질2;

import java.io.*;
import java.util.*;

public class Main {
	static final int M = 200000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		int[] distance = new int[M];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {N, 0});
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[N]=0;
		int minDis = Integer.MAX_VALUE;
		int count = 1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int num = now[0];
			int dis = now[1];
			
			if(dis>distance[num]) continue;
			
			if(num==K) {
				if (minDis==dis) count++;
				else {
					minDis = dis;
					count = 1;
				}
			}
			
			int nxt = num-1;
			if (nxt>=0 && distance[nxt]>=dis+1) {
				distance[nxt] = dis+1;
				que.add(new int[] {nxt, dis+1});
			}
			
			nxt = num+1;
			if (nxt<M && distance[nxt]>=dis+1) {
				distance[nxt] = dis+1;
				que.add(new int[] {nxt, dis+1});
			}
			
			nxt = num*2;
			if(nxt<M && distance[nxt]>=dis+1) {
				distance[nxt] = dis+1;
				que.add(new int[] {nxt, dis+1});
			}
		}
		System.out.println(minDis);
		System.out.println(count);
	}
}
