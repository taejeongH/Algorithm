//package BOJ.가장긴바이토닉부분수열;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] left = new int[N];
		int[] right= new int[N];
		left[0]=1;
		right[N-1]=1;
		for (int i=1; i<N; i++) {
			for (int j=i-1; j>=0; j--) {
				if(map[j] < map[i]) {
					left[i] = Math.max(left[j], left[i]);
				}
			}
			left[i]++;
		}
		
		for (int i=N-2; i>=0; i--) {
			for (int j=i+1; j<N; j++) {
				if(map[j] < map[i]) {
					right[i] = Math.max(right[j], right[i]);
				}
			}
			right[i]++;
		}
		
		int res = 0;
		for (int i=0; i<N; i++) {
			res = Math.max(left[i]+right[i]-1, res);
		}
		System.out.println(res);
	}
	
}


/*
	커지거나 작아지거나
	현재 기준으로 커지는 수열
	현재 기준으로 작아지는 수열
	
*/