//package BOJ.책나눠주기;

import java.io.*;
import java.util.*;

public class Main {
	static class Book implements Comparable<Book>{
		int start;
		int end;
		int remain;
		public Book(int start, int end, int remain) {
			super();
			this.start = start;
			this.end = end;
			this.remain = remain;
		}
		@Override
		public int compareTo(Book o) {
			if(this.remain==o.remain) return this.start - o.start;
			return this.remain-o.remain;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Book [start=").append(start).append(", end=").append(end).append(", remain=").append(remain)
					.append("]");
			return builder.toString();
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int tc = Integer.parseInt(br.readLine());
		for (int t=1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Book[] book = new Book[M];
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				book[i] = new Book(s, e, e-s+1);
			}
			
			boolean[] v = new boolean[N+1];
			int res = 0;
			
			for (int i=0; i<M; i++) {
				Arrays.sort(book);
//				System.out.println(Arrays.toString(book));
				int select = 0;
				book[0].remain = 1_000_000_000;
				if(book[0].remain==0) continue;
				for (int j=book[0].start; j<=book[0].end; j++) {
					if(!v[j]) {
						select = j;
						v[j]=true;
						res++;
						break;
					}
				}
				
				if(select==0) continue;
				for (int j=0; j<M-i; j++) {
					if (book[j].start <= select && select <= book[j].end) {
						book[j].remain--;
					}
				}
				
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
}
