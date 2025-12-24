//package BOJ.퍼즐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] di = {-1, 1, -3, 3};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Map<String, Integer> check = new HashMap<>();
		N = 3;
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				sb.append(Integer.parseInt(st.nextToken()));
			}
		}
		
		ArrayDeque<StringBuilder> que = new ArrayDeque<>();
		
		que.add(sb);
		check.put(sb.toString(), 0);
		int res = -1;
		while(!que.isEmpty()) {
			StringBuilder now = que.poll();
			int dis = check.get(now.toString());
			
			int idx = find(now);
			if (isCorrect(now)) {
				res = dis;
				break;
			}
			
			//0 1 2
			for (int i=0; i<4; i++) {
				int nx = idx + di[i];
				
				if (i<2 && idx/N != nx/N) continue;
				if (nx < 0 || nx>=N*N) continue;
				
				StringBuilder nxt = new StringBuilder(now);
				nxt.setCharAt(idx, now.charAt(nx));
				nxt.setCharAt(nx, '0');
				
				if(check.containsKey(nxt.toString())) continue;
				check.put(nxt.toString(), dis+1);
				
				que.add(nxt);
			}
		}
		System.out.println(res);
		
	}
	
	public static boolean isCorrect(StringBuilder now) {
		int idx = 1;
		for (int i=0; i<N*N-1; i++) {
			if (now.charAt(i) != '0' + idx++) return false;
		}
		return true;
	}
	
	
	public static int find(StringBuilder now) {
		for (int i=0; i<N*N; i++) {
			if(now.charAt(i)=='0') {
				return i;
			}
		}
		return -1;
	}
}
