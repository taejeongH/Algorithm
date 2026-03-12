//package BOJ.데이터구조;

import java.io.*;
import java.util.*;

public class Main{
	static int[] tree;
	
	static void update(int num, int start, int end, int node, int n) {
		if (start > num || end < num) return;
		if (start == num && end == num) {
			tree[node]+=n;
			return;
		}
		
		int mid = (end+start)/2;
		update(num, start, mid, node*2, n);
		update(num, mid+1, end, node*2+1, n);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static int query(int left, int right, int start, int end, int node) {
		if (start > right || end < left) return 0;
		if (left <= start && end <= right) return tree[node];
		
		int mid = (start+end)/2;
		return query(left, right, start, mid, node*2) + query(left, right, mid+1, end, node*2+1);
	}
	static final int N = 2_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		tree = new int[2_000_000 * 4];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if (order == 1) {
				update(num, 1, N, 1, 1);
			} else {
				int n = binarySearch(num);
				sb.append(n).append("\n");
				update(n, 1, N, 1, -1);
			}
		}
		System.out.println(sb);
	}
	
	static int binarySearch(int n) {
		int l = 1;
		int r = 2_000_000;
		
		while(l < r) {
			int mid = (l+r)/2;
			
			int cnt = query(1, mid, 1, N, 1);
			if (cnt < n) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return r;
	}
	
}