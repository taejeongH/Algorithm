//package BOJ.닭싸움팀정하기;

import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<Integer>[] friend = new List[N+1];
		List<Integer>[] enemy = new List[N+1];
		parent = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			friend[i] = new ArrayList<>();
			enemy[i] = new ArrayList<>();
			parent[i] = i;
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if (order.equals("E")) {
				enemy[s].add(e); 
				enemy[e].add(s); 
			} else {
				union (s, e);
			}
		}
		
		for (int i=1; i<=N; i++) {
			if (enemy[i].isEmpty()) continue;
			
			for (int e : enemy[i]) {
				for (int f : enemy[e]) {
					union(i, f);
				}
			}
		}
		
		boolean[] v = new boolean[N+1];
		
		int res = 0;
		for (int i=1; i<=N; i++) {
			int root = find(i);
			if (!v[root]) {
				res++;
				v[root] = true;
			}
		}
		System.out.println(res);
//		System.out.println(Arrays.toString(parent));
	}
	
	static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX == rootY) return;
		
		parent[rootX] = rootY;
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		
		int rootX = find(parent[x]);
		
		return parent[x] = rootX;
	}
}
