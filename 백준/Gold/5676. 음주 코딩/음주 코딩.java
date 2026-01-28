//package BOJ.음주코딩;

import java.io.*;
import java.util.*;

public class Main{
	static void init(int[] map, int start, int end, int node) {
		if (start > end) return;
		
		if (start == end) {
			tree[node] = map[start];
			return;
		}
		
		int mid = (start+end)/2;
		init(map, start, mid, node*2);
		init(map, mid+1, end, node*2+1);
		if (tree[node*2] == -1 || tree[node*2+1] == -1) tree[node] = -1;
		else tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static void update(int left, int right, int start, int end, int node, int val) {
		if (start > right || end < left) return;
		
		if (left <= start && end <= right) {
			tree[node] = val;
			return;
		}
		int mid = (start+end)/2;
		update(left, right, start, mid, node*2, val);
		update(left, right, mid+1, end, node*2+1, val);
		
		if (tree[node*2] == -1 || tree[node*2+1] == -1) tree[node] = -1;
		else tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static int query(int left, int right, int start, int end, int node) {
		if (start > right || end < left) return 0;
		
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		int l = query(left, right, start, mid, node*2);
		int r = query(left, right, mid+1, end, node*2+1);
		
		if(l==-1 || r==-1) return -1;
		
		return l + r;
	}
	
	static int[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			if (line.trim().isEmpty()) break;
			StringTokenizer st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int treeHeight = (int) Math.ceil(Math.log(N)/Math.log(2));
			tree = new int[1<<treeHeight+1];
			int[] map = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) {
				int num = Integer.parseInt(st.nextToken());
				if (num<0) map[i] = 1;
				else if(num==0) map[i] = -1;
			}
			
			init(map, 1, N ,1);
			
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				String order = st.nextToken();
				
				if (order.equals("C")) {
					int idx = Integer.parseInt(st.nextToken());
					int num = Integer.parseInt(st.nextToken());
					
					int val = 0;
					if (num < 0) val = 1;
					else if (num == 0) val = -1;
					//update
					update(idx, idx, 1, N, 1, val);
				} else {
					int from = Integer.parseInt(st.nextToken());
					int to = Integer.parseInt(st.nextToken());
					//query
					int res = query(from, to, 1, N, 1);
					if (res == -1) {
						sb.append(0);
					} else if(res % 2 == 0) {
						sb.append("+");
					} else {
						sb.append("-");
					}
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
		
		
	}
	
}