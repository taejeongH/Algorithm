//package BOJ.열혈강호;

import java.io.*;
import java.util.*;

public class Main {
	
	static List<Integer>[] list;
	static boolean[] check;
	static int[] d;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new List[N+1]; for (int i=1; i<=N; i++) list[i] = new ArrayList<>();
		
		check = new boolean[M+1];
		d = new int[M+1];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			for (int j=0; j<s; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int cnt = 0;
		for (int i=1; i<=N; i++) {
			Arrays.fill(check, false);
			if(dfs(i)) cnt++;
		}
		System.out.println(cnt);
	}
	
	static boolean dfs(int x) {
		for (int nxt : list[x]) {
			if (!check[nxt]) {
				check[nxt] = true;
				if(d[nxt] == 0 || dfs(d[nxt])) {
					d[nxt] = x;
					return true;
				}
			}
		}
		return false;
	}
}
