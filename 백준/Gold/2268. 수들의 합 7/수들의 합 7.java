//package BOJ.수들의합7;

import java.io.*;
import java.util.*;

public class Main {
	
	static void update(int left, int right, int start, int end, int node, int value) {
		if (start>right || end<left) return;
		
		if(start==end) {
			tree[node] = value;
			return;
		}
		
		int mid = (start+end)/2;
		update(left, right, start, mid, node*2, value);
		update(left, right, mid+1, end, node*2+1, value);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static long query(int left, int right, int start, int end, int node) {
		if(start>right || end<left) return 0;
		
		if (left <=start && end <= right) {
			return tree[node];
		}
		int mid = (start+end)/2;
		
		return query(left, right, start, mid, node*2) + query(left, right, mid+1, end, node*2+1);
	}
	
	static long[] tree;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] map = new int[N];
		
		int treeHeight = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree = new long[1<<treeHeight+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			if(order == 0) {
				//sum
				if(start<=end)sb.append(query(start, end, 0, N-1, 1)).append("\n");
				else sb.append(query(end, start, 0, N-1, 1)).append("\n");
			} else {
				//modify
				update(start, start, 0, N-1, 1, end+1);
			}
		}
		System.out.println(sb);
	}
}
