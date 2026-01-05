//package BOJ.히스토그램;

import java.io.*;
import java.util.*;

public class Main {
	
	static void init(int[] map, int start, int end, int node) {
		if (start>end) return;
		
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
	
	static int query(int[] map, int left, int right, int start, int end, int node) {
		if(start>right || end <left) return 0;
		
		if(left <= start && end <= right) {
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
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[] map = new int[N+1];
		for (int i=1; i<=N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		map[0] = Integer.MAX_VALUE;
		
		int treeHeight = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree = new int[1<<treeHeight+1];
		
		init(map, 1, N, 1);
		System.out.println(divCon(map, 1, N));
	}
	
	public static int divCon(int[] map, int start, int end) {
		if(start>end) return 0;
		
		int minPos = query(map, start, end, 1, N, 1);
		int cur = map[minPos] * (end - start + 1);
		int left = divCon(map, start, minPos-1);
		int right = divCon(map, minPos+1, end);
		
		int max = Math.max(left, right);
		
		return Math.max(cur, max);
	}
}
