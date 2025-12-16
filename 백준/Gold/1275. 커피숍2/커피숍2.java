//package BOJ.커피숍2;

import java.io.*;
import java.util.*;

public class Main {
	static long[] tree;
	
	static void init(int[] map, int left, int right, int idx) {
		if(right<left) return;
		if(left==right) {
			tree[idx] = map[left];
			return;
		}
		
		int mid = (left+right)/2;
		init(map, left, mid, idx*2);
		init(map, mid+1, right, idx*2+1);
		tree[idx] = tree[idx*2] + tree[idx*2+1];
	}
	
	static long query(int[] map, int left, int right, int start, int end, int node) {
		if(start>right || end<left) return 0;
		
		if (start <= left && right <= end) {
			return tree[node];
		}
		
		int mid = (left+right)/2;
		return query(map, left, mid, start, end, node*2)+query(map, mid+1, right, start, end, node*2+1);
	}
	
	static void update(int left, int right, int start, int end, int node, int value) {
		if(start>right || end<left) return;
		
		if (start <= left && right <= end) {
			tree[node] = value;
			return;
		}
		
		int mid = (left+right)/2;
		update(left, mid, start, end, node*2, value);
		update(mid+1, right, start, end, node*2+1, value);
		tree[node] = tree[node*2]+tree[node*2+1];
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int height = (int) (Math.ceil(Math.log(N)/Math.log(2)))+1;
		tree = new long[1<<height];
		init(map, 0, N-1, 1);
		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken());
			
			if (x>y) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			
			sb.append(query(map, 0, N-1, x, y, 1)).append("\n");
			map[a]=b;
			update(0, N-1, a, a, 1, b);
		}
		System.out.println(sb);
	}
}
