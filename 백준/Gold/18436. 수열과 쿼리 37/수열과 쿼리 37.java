//package BOJ.수열과쿼리37;

import java.io.*;
import java.util.*;

public class Main{
	static void init(int[] map, int start, int end, int node) {
		if(start>end) return;
		if(start==end) {
			tree[node] = map[start]%2==0?1:0;
			return;
		}
		
		int mid = (start+end)/2;
		init(map, start, mid, node*2);
		init(map, mid+1, end, node*2+1);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static void update(int left, int right, int start, int end, int node, int value) {
		if (end < left || start > right) return;
		
		if(start==end) {
			tree[node] = value%2==0?1:0;
			return;
		}
		
		int mid = (start+end)/2;
		update(left, right, start, mid, node*2, value);
		update(left, right, mid+1, end, node*2+1, value);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static int query(int left, int right, int start, int end, int node) {
		if (end < left || start > right) return 0;
		
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		return query(left, right, start, mid, node*2) + query(left, right, mid+1, end, node*2+1);
	}
	static int[] tree;
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
		tree = new int[1<<treeHeight+1];
		init(map, 0, N-1, 1);
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			if(order == 1) {
				int idx = Integer.parseInt(st.nextToken())-1;
				int num = Integer.parseInt(st.nextToken());
				update(idx, idx, 0, N-1, 1, num);
			} else if (order == 2) {
				//짝수 개수
				int l = Integer.parseInt(st.nextToken())-1;
				int r = Integer.parseInt(st.nextToken())-1;
				
				sb.append(query(l, r, 0, N-1, 1)).append("\n");
			} else{
				//홀수 개수
				int l = Integer.parseInt(st.nextToken())-1;
				int r = Integer.parseInt(st.nextToken())-1;
				
				sb.append((r-l+1) - query(l, r, 0, N-1, 1)).append("\n");
			}
//			System.out.println(Arrays.toString(tree));
		}
		System.out.println(sb);
	}
	
}