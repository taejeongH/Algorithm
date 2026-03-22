//package BOJ.순열;

import java.io.*;
import java.util.*;

public class Main {
	static void init(int start, int end, int node) {
		if (start > end) return;
		if (start== end) {
			tree[node] = 1;
			return;
		}
		int mid = (start+end)/2;
		init(start, mid, node*2);
		init(mid+1, end, node*2+1);
		tree[node] = tree[node*2]+tree[node*2+1];
	}
	
	static int find(int left, int right, int start, int end, int node) {
		if(left > end || right < start) return 0;
		if (left <= start && end <= right) return tree[node];
		
		int mid = (start+end)/2;
		return find(left, right, start, mid, node*2) + find(left, right, mid+1, end, node*2+1);
	}
	
	static void update(int pos, int start, int end, int node) {
		if (start > pos || pos > end) return;
		if (start == pos && pos == end) {
			tree[node] = 0;
			return;
		}
		
		int mid = (start+end)/2;
		update(pos, start, mid, node*2);
		update(pos, mid+1, end, node*2+1);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static int[] tree;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		tree = new int[N*4];
		init(1, N, 1);
		
		int[] map = new int[N+1];
		for (int i=1; i<=N; i++) {
			int num = Integer.parseInt(br.readLine())+1;
			int idx = binarySearch(num);
			map[idx] = i;
			update(idx, 1, N, 1);
//			System.out.println(i + " " + idx);
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			sb.append(map[i]).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int binarySearch(int num) {
		int l = 1;
		int r = N;
		
		while(l<r) {
			int mid = (l+r)/2;
			int cnt = find(1, mid, 1, N, 1);
			
			if (cnt < num) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return r;
	}
}
