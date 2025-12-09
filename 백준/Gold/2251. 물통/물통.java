//package BOJ.물통;

import java.io.*;
import java.util.*;

public class Main {
	static int[] map;
	static boolean[][][] v;
	static ArrayList<Integer> res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 3;
		map = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		v = new boolean[map[0]+1][map[1]+1][map[2]+1];
		res = new ArrayList<>();
		dfs(0, 0, map[2]);
		
		StringBuilder sb = new StringBuilder();
		Collections.sort(res);
		for (int i=0; i<res.size(); i++) {
			sb.append(res.get(i)).append(" ");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int a, int b, int c) {
		if(v[a][b][c]) return;
		v[a][b][c] = true;
		if(a==0) {
			res.add(c);
		}
		
		//a->b
		if (a+b > map[1]) {
			dfs((a+b)-map[1], map[1], c);
		} else {
			dfs(0, a+b, c);
		}
		
		//a->c
		if (a+c > map[2]) {
			dfs((a+c)-map[2], b, map[2]);
		} else {
			dfs(0, b, a+c);
		}
		
		//b->c
		if (b+c > map[2]) {
			dfs(a, (b+c)-map[2], map[2]);
		} else {
			dfs(a, 0, b+c);
		}
		
		//b->a
		if (b+a > map[0]) {
			dfs(map[0], (b+a)-map[0], c);
		} else {
			dfs(b+a, 0, c);
		}
		
		//c->a
		if (c+a > map[0]) {
			dfs(map[0], b, (c+a)-map[0]);
		} else {
			dfs(c+a, b, 0);
		}
		
		//c->b
		if (c+b > map[1]) {
			dfs(a, map[1], (c+b)-map[1]);
		} else {
			dfs(a, c+b, 0);
		}
	}
}
