//package BOJ.파일합치기3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int tc = Integer.parseInt(br.readLine());
		for (int t=1; t<=tc; t++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Long> pq = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
			
			long res = 0; 
			while(pq.size()!=1) {
				long first = pq.poll();
				long second = pq.poll();
				
				res += first+second;
				pq.add(first+second);
			}
			
			sb.append(res).append("\n");
		}
		System.out.print(sb);
	}
	
}
