//package BOJ.문제추천시스템버전1;

import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();
		TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			set.add(new int[] {num, val});
			map.put(num, val);
		}
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			if (order.charAt(0)=='r') {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					int[] hard = set.last();
					sb.append(hard[0]);
				} else {
					int[] easy = set.first();
					sb.append(easy[0]);
				}
				sb.append("\n");
			} else if(order.charAt(0) == 'a') {
				int num = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				
				set.add(new int[] {num, val});
				map.put(num, val);
			} else {
				int num = Integer.parseInt(st.nextToken());
				int val = map.get(num);
				set.remove(new int[] {num, val});
			}
		}
		System.out.print(sb);
	}
	
}