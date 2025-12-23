//package BOJ.Astromeeting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 500_000_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t=1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //사람의 수
			int P = Integer.parseInt(st.nextToken()); //은하의 수
			int Q = Integer.parseInt(st.nextToken()); //은하간의 길 개수
			
			int[] person = new int[N];
			for (int i=0; i<N; i++) {
				person[i] = Integer.parseInt(br.readLine());
			}
			
			int[][] g = new int[P+1][P+1];
			for (int i=1; i<=P; i++) Arrays.fill(g[i], INF);
			
			
			for (int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				g[s][e] = Math.min(c, g[s][e]);
				g[e][s] = Math.min(c, g[e][s]);
			}
			
			for (int i = 1; i <= P; i++) {
			    g[i][i] = 0;
			}
			
			for (int k=1; k<=P; k++) {
				for (int i=1; i<=P; i++) {
					for (int j=1; j<=P; j++) {
						if (g[i][k] == INF || g[k][j]==INF) continue;
						g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
					}
				}
			}
			
			int node = 1;
			long res = Long.MAX_VALUE;
			for (int i=1; i<=P; i++) {
				long sum = 0;
				for (int j=0; j<N; j++) {
					if (g[person[j]][i] == INF) {
						sum = INF;
						break;
					}
					sum += (long) g[person[j]][i] * g[person[j]][i];
				}
				
				if (sum < res) {
					node = i;
					res = sum;
				}
			}
//			for (int i=1; i<=P; i++) System.out.println(Arrays.toString(g[i]));
			sb.append(node).append(" ").append(res).append("\n");
		}
		System.out.print(sb);
	}
}
