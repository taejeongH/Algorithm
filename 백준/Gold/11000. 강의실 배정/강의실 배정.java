//package BOJ.강의실배정;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		Arrays.sort(map, (o1, o2)->o1[0]-o2[0]);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i=0; i<N; i++) {
			if (pq.isEmpty() || map[i][0] < pq.peek()) {
				pq.add(map[i][1]);
			} else {
				pq.poll();
				pq.add(map[i][1]);
			}
		}
		
		System.out.println(pq.size());
	}
}
