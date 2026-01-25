//package BOJ.수열과쿼리21;

import java.io.*;
import java.util.*;

public class Main{
	static void init(int[] map, int start, int end, int node) {
		if (start==end) {
			tree[node] = map[start];
			return;
		}
		
		int mid = (start+end)/2;
		init(map, start, mid, node*2);
		init(map, mid+1, end, node*2+1);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static void update(int left, int right, int start, int end, int node, int value) {
		propagate(start, end, node);
		if (end < left || right < start) return;
		
		if (left <= start && end <= right) {
			lazy[node] += value;
	        propagate(start, end, node);
	        return;
		}
		int mid = (start+end)/2;
		update(left, right, start, mid, node*2, value);
		update(left, right, mid+1, end, node*2+1, value);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static long query(int left, int right, int start, int end, int node) {
		propagate(start, end, node);
		if (end < left || right < start) return 0;
		
		if(start == end) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		return query(left, right, start, mid, node*2) + query(left, right, mid+1, end, node*2+1);
	}
	
	static void propagate(int start, int end, int node) {
	    if (lazy[node] != 0) {
	        tree[node] += (end - start + 1) * lazy[node];

	        if (start != end) {
	            lazy[node*2] += lazy[node];
	            lazy[node*2 + 1] += lazy[node];
	        }
	        lazy[node] = 0;
	    }
	}
	
	static long[] tree;
	static long[] lazy;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		int treeHeight = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree = new long[1<<treeHeight+1];
		lazy = new long[1<<treeHeight+1];
		init(map, 0, N-1, 1);
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			if (order == 1) {
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int num = Integer.parseInt(st.nextToken());
				update(from, to, 0, N-1, 1, num);
			} else {
				int x = Integer.parseInt(st.nextToken())-1;
				sb.append(query(x, x, 0, N-1, 1)).append("\n");
			}
		}
		System.out.print(sb);
	}
	
}