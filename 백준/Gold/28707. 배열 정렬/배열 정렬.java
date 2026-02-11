//package BOJ.배열정렬;

import java.io.*;
import java.util.*;

public class Main{
	static HashMap<Integer, Integer> distance;
	static final int INF = 1_000_000_000;
	static int N;
	static int[] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[][] order = new int[M][3];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			order[i][0] = l;
			order[i][1] = r;
			order[i][2] = c;
		}
		distance = new HashMap<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		int num = 0;
		for (int i=1; i<=N; i++) {
			num += i * Math.pow(10, N-i);
		}
		pq.add(new int[] {num, 0});
		distance.put(num, 0);
		int res = -1;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int node = now[0];
			int dis = now[1];
			
//			System.out.println(node + " " + dis);
			if (dis>distance.get(node)) continue;
			
			if(isCorrect(node)) {
				res = dis;
				break;
			}
			
			for (int i=0; i<M; i++) {
				int change = change(node, order[i][0], order[i][1]);
				if(!distance.containsKey(change)) {
					distance.put(change, INF);
				}
				
				if (distance.get(change) > dis+order[i][2]) {
					distance.put(change, dis+order[i][2]);
					pq.add(new int[] {change, dis+order[i][2]});
				}
				
			}
		}
		System.out.println(res);
	}
	
	public static boolean isCorrect(int num) {
		String s = Integer.toString(num);
		int prev = s.charAt(0)-'0';
		for (int i=1; i<N; i++) {
			if (map[prev] > map[s.charAt(i)-'0']) return false;
			prev = s.charAt(i)-'0';
		}
		return true;
	}
	
	public static int change(int node, int l, int r) {
//		System.out.println(node);
		int res = 0;
		String s = Integer.toString(node);
		for (int i=0; i<N; i++) {
			int num = s.charAt(i)-'0';
			if (num==l) num =r;
			else if (num==r) num =l;
			res += num*Math.pow(10, N-1-i);
		}
		return res;
	}
	
	
}