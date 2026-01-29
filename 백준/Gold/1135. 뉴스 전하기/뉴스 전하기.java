//package BOJ.뉴스전하기;

import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] g;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		g = new List[N]; for (int i=0; i<N; i++) g[i] = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if(i==0) continue;
			g[p].add(i);
		}
		System.out.println(dfs(0));
	}
	static int dfs(int node) {
		if(g[node].isEmpty()) return 0;
		
		int[] times = new int[g[node].size()];
		for (int i=0; i<g[node].size(); i++) {
			times[i] = dfs(g[node].get(i));
		}
		Arrays.sort(times);
		
		int res = 0;
		for (int i=0; i<g[node].size(); i++) {
			int t = times[g[node].size() - 1 - i];
            res = Math.max(res, t + (i + 1));
		}
		
		return res;
	}
}
