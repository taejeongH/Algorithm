//package BOJ.불우이웃돕기;

import java.io.*;
import java.util.*;

public class Main{
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] g = new int[N][N];
		int total = 0;
		for (int i=0; i<N; i++) Arrays.fill(g[i], INF);
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				if (input.charAt(j) == '0') continue;
				
				int dis = 0;
				if(input.charAt(j)>='a') {
					dis = input.charAt(j)-'a'+1;
				} else {
					dis = input.charAt(j)-'A'+27;
				}
				
				g[i][j] = Math.min(dis, g[i][j]);
				g[j][i] = Math.min(dis, g[j][i]);
				total += dis;
			}
		}
		
		boolean[] v = new boolean[N];
		int[] prim = new int[N];
		int mst = 0;
		Arrays.fill(prim, INF);
		prim[0] = 0;
		for (int i=0; i<N; i++) {
			int minVertex = -1;
			int min = INF;
			for (int j=0; j<N; j++) {
				if (!v[j] && min>prim[j]) {
					min = prim[j];
					minVertex = j;
				}
			}
			
			if(minVertex==-1) {
				mst = -1;
				break;
			}
			
			mst += min;
			v[minVertex] = true;
			
			for (int j=0; j<N; j++) {
				if(!v[j] && prim[j]>g[minVertex][j]) {
					prim[j] = g[minVertex][j];
				}
			}
		}
		
		System.out.println(mst==-1?mst:total-mst);
	}
}