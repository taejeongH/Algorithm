//package BOJ.오등큰수;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] counting = new int[1_000_001];
		int[] map = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			counting[map[i]]++;
		}
		
		Stack<int[]> stk = new Stack<>();
		int[] res = new int[N];
		Arrays.fill(res, -1);
		for (int i=0; i<N; i++) {
			int f = counting[map[i]];
			
			while(!stk.isEmpty() && stk.peek()[0] < f) {
				int[] now = stk.pop();
				res[now[1]] = map[i];
			}
			
			stk.add(new int[] {f, i});
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(res[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}
