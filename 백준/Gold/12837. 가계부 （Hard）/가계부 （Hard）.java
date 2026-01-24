//package BOJ.가계부;

import java.io.*;
import java.util.*;

public class Main{
	static long[] tree;
	static void update(int left, int right, int start, int end, int node, int value) {
		if (end < left || start > right) return;
		
		if(left <= start && end <= right) {
			tree[node] += value;
			return;
		}
		
		int mid = (start+end)/2;
		update(left, right, start, mid, node*2, value);
		update(left, right, mid+1, end, node*2+1, value);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static long query(int left, int right, int start, int end, int node) {
		if (end < left || start > right) return 0;
		if (left <= start && end <= right) return tree[node];
		
		int mid = (start+end)/2;
		return query(left, right, start, mid, node*2) + query(left, right, mid+1, end, node*2+1);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int treeHeight = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree = new long[1<<treeHeight+1];
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			if (order == 1) {
				int x = Integer.parseInt(st.nextToken());
				update(p, p, 1, N, 1, x);
			} else {
				int q = Integer.parseInt(st.nextToken());
				sb.append(query(p, q, 1, N, 1)).append("\n");
			}
		}
		System.out.println(sb);
	}
	
}