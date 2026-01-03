//package BOJ.수열과쿼리17;

import java.io.*;
import java.util.*;

public class Main {
	static void init(int[]map, int start, int end, int node) {
		if(start>end) return;
		
		if(start==end) {
			tree[node] = map[start];
			return;
		}
		
		int mid = (start+end)/2;
		init(map, start, mid, node*2);
		init(map, mid+1, end, node*2+1);
		
		tree[node] = Math.min(tree[node*2], tree[node*2+1]);
	}
	
	static void update(int left, int right, int start, int end, int node, int value) {
		if (start>right || end<left) return;
		
		if(left <= start && end <= right) {
			tree[node] = value;
			return;
		}
		
		int mid = (start+end)/2;
		
		update(left, right, start, mid, node*2, value);
		update(left, right, mid+1, end, node*2+1, value);
		
		tree[node] = Math.min(tree[node*2], tree[node*2+1]);
	}
	
	static int query(int left, int right, int start, int end, int node) {
		if (start>right || end<left) return Integer.MAX_VALUE;
		
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		
		return Math.min(query(left, right, start, mid, node*2), query(left, right, mid+1, end, node*2+1));
		
	}
	
	
	static int[] tree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int treeHeight = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree = new int[1<<treeHeight+1];
		init(map, 0, N-1, 1);
//		System.out.println(Arrays.toString(tree));
		
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			
			if (order == 1) {
				int idx = Integer.parseInt(st.nextToken())-1;
				int num = Integer.parseInt(st.nextToken());
				
				map[idx] = num;
				update(idx, idx, 0, N-1, 1, num);
			} else {
				int s = Integer.parseInt(st.nextToken())-1;
				int e = Integer.parseInt(st.nextToken())-1;
				sb.append(query(s, e, 0, N-1, 1)).append("\n");
			}
		}
		System.out.println(sb);
	}
}
