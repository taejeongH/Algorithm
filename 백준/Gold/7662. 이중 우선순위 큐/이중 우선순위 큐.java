//package BOJ.이중우선순위큐;

import java.io.*;
import java.util.*;

public class Main {
	static class Number implements Comparable<Number>{
		long num;
		int id;
		
		public Number(long num, int id) {
			this.num = num;
			this.id = id;
		}

		@Override
		public int compareTo(Number o) {
			Long sub = this.num - o.num;
			if (sub > 0) return 1;
			else if (sub == 0) return this.id-o.id;
			else return -1;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Number [num=").append(num).append(", id=").append(id).append("]");
			return builder.toString();
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		
		int tc = Integer.parseInt(br.readLine());
		for (int t=1; t<=tc; t++) {
			int Q = Integer.parseInt(br.readLine());
			
			int idx = 0;
			TreeSet<Number> set = new TreeSet<>();
			for (int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine());
				String order = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				if (order.equals("I")) {
					//삽입
					set.add(new Number(num, idx++));
				} else {
					if (set.isEmpty()) continue;
					if (num == 1) {
						Number n = set.last();
						set.remove(n);
					} else {
						Number n = set.first();
						set.remove(n);
					}
				}
			}
			if (set.isEmpty()) sb.append("EMPTY").append("\n");
			else sb.append(set.last().num).append(" ").append(set.first().num).append("\n");
		}
		System.out.print(sb);
	}
}
