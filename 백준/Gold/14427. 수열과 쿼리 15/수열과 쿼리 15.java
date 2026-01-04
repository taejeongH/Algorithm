//package BOJ.수열과쿼리15;

import java.io.*;
import java.util.*;

public class Main {
	static class Num implements Comparable<Num>{
		int idx;
		int num;
		
		public Num(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}

		@Override
		public int compareTo(Num o) {
			if(o.num == this.num)  return this.idx - o.idx;
			return this.num-o.num;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		TreeSet<Num> set = new TreeSet<>();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] map = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			set.add(new Num(i, map[i]));
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			
			if(order == 2) {
				sb.append(set.first().idx).append("\n");
			} else {
				int idx = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				
				set.remove(new Num(idx, map[idx]));
				map[idx] = num;
				set.add(new Num(idx, map[idx]));
			}
		}
		System.out.println(sb);
	}
}
