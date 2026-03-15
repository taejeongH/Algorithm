//package BOJ.수열과쿼리22;

import java.io.*;
import java.util.*;

public class Main {
	static void init(int[] map, int start, int end, int node) {
		if (start > end) return;
		if (start==end) {
			tree[node] = map[start];
			return;
		}
		int mid = (start+end)/2;
		init(map, start, mid, node*2);
		init(map, mid+1, end, node*2+1);
		tree[node] = tree[node*2]+tree[node*2+1];
	}
	
	static long query(int left, int right, int start, int end, int node) {
		if (right<start || end<left) return 0;
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start+end)/2;
		return query(left, right, start, mid , node*2) + query(left, right, mid+1, end, node*2+1);
	}
	
	static void update(int now, int start, int end, int node, int val) {
		if (now < start || now > end) return;
		
		if(start == now && now == end) {
			tree[node] = val;
			return;
		}
		
		int mid = (start+end)/2;
		update(now, start, mid, node*2, val);
		update(now, mid+1, end, node*2+1, val);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static long[] tree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N+1];
		
		tree = new long[N*4];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		init(map, 1, N, 1);
		int M = Integer.parseInt(br.readLine());
		
		int idx = 1;
		int qidx = 0;
		PriorityQueue<int[]> query = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		
		for (int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			
			int[] now;
			if (order == 1) {
				int i = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				now = new int[] {order, idx++, i, v};
			} else {
				int k = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				now = new int[] {order, k, i, j, qidx++};
			}
			query.add(now);
		}
		
		long[] res = new long[qidx];
		
		while(!query.isEmpty()) {
			int[] now = query.poll();
			int order = now[0];
			
			if (order == 1) {
				update(now[2], 1, N, 1, now[3]);
			} else {
				res[now[4]] = query(now[2], now[3], 1, N, 1);
			}
		}
		
		for (int i=0; i<qidx; i++) {
			sb.append(res[i]).append("\n");
		}
		
		System.out.print(sb);
			
	}
}
