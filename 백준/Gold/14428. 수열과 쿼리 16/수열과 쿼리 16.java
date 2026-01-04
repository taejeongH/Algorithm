//package BOJ.수열과쿼리16;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void init(int[] map, int start, int end, int node) {
		if(end < start) return;
		if(start == end) {
			tree[node] = start;
			return; 
		}
		
		int mid = (start+end)/2;
		
		init(map, start, mid, node*2);
		init(map, mid+1, end, node*2+1);
		
		int leftIdx = tree[node*2];
		int rightIdx = tree[node*2+1];
		
		if(map[leftIdx] < map[rightIdx]) {
			tree[node] = leftIdx;
		} else if (map[leftIdx] == map[rightIdx]) {
			tree[node] = Math.min(leftIdx, rightIdx);
		} else {
			tree[node] = rightIdx;
		}
	}
	
	public static void update(int[] map, int left, int right, int start, int end, int node) {
		if (end<left || start>right) return;
		
		
		if(left <= start && end <= right) {
			return;
		}
		
		int mid = (start+end)/2;
		update(map, left, right, start, mid, node*2);
		update(map, left, right, mid+1, end, node*2+1);
		
		int leftIdx = tree[node*2];
		int rightIdx = tree[node*2+1];
		
		if(map[leftIdx] < map[rightIdx]) {
			tree[node] = leftIdx;
		} else if (map[leftIdx] == map[rightIdx]) {
			tree[node] = Math.min(leftIdx, rightIdx);
		} else {
			tree[node] = rightIdx;
		}
	}
	
	public static int query(int[]map, int left, int right, int start, int end, int node) {
		if (end<left || start>right) return 0;
		
		if (left<=start && end<=right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		
		int leftIdx = query(map, left, right, start, mid, node*2);
		int rightIdx = query(map, left, right, mid+1, end, node*2+1);
		
		if(map[leftIdx] < map[rightIdx]) {
			return leftIdx;
		} else if (map[leftIdx] == map[rightIdx]) {
			return Math.min(leftIdx, rightIdx);
		} else {
			return rightIdx;
		}
	}
	
	static int[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<N+1; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		int treeHeight = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree = new int[1<<treeHeight+1];
		map[0] = Integer.MAX_VALUE;
		init(map, 1, N, 1);
		int M = Integer.parseInt(br.readLine());
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			if(order == 2) {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				sb.append(query(map, s, e, 1, N, 1)).append("\n");
			} else {
				int idx = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				map[idx] = value;
				update(map, idx, idx, 1, N, 1);
			}
		}
		System.out.println(sb);
	}
}
