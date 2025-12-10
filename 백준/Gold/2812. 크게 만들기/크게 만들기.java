//package BOJ.고층건물;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] res = new int[N-K];
		int[] map = new int[N];
		int idx = N-K-1;
		String input = br.readLine();
		
		Stack<Integer> stk = new Stack<>();
		for (int i=0; i<N; i++) {
			int num = input.charAt(i)-'0';
			
			while(!stk.isEmpty() && stk.peek()<num && K>0) {
				stk.pop();
				K--;
			}
			stk.add(num);
		}
		
		while(!stk.isEmpty() && K>0) {
			stk.pop();
			K--;
		}
		
		while(!stk.isEmpty()) {
			res[idx--] = stk.pop();
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<res.length; i++) {
			sb.append(res[i]);
		}
		System.out.println(sb);
	}
}

/*
	1924
	6
	
	5
	
	5
	7
	7
	
*/