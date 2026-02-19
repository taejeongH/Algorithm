//package BOJ.Parcel;

import java.io.*;
import java.util.*;

public class Main{
	static int[] map;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(map);
		boolean[] pairSum = new boolean[W+1];
		for (int i=0; i<N; i++) {
			for (int j=i+1; j<N; j++) {
				int sum = map[i] + map[j];
				
				if(sum > W) break;
				
				if(pairSum[W-sum]) {
					System.out.println("YES");
					return;
				}
			}
			
			for (int j=0; j<i; j++) {
				int sum = map[i] + map[j];
				if (sum<=W) pairSum[sum] = true;
			}
		}
		
		
		System.out.println("NO");
	}
}