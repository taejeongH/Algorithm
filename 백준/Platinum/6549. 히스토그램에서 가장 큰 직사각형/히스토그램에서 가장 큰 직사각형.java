//package BOJ.히스토그램에서가장큰직사각형;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			if (N==0) break;
			
			int[] map = new int[N];
			for (int i=0; i<N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(divCon(map, 0, N-1)).append("\n");
		}
		System.out.println(sb);
	}
	
	static long divCon(int[] map, int start, int end) {
		if (start==end) return map[start];
		long res = 0;
		
		int mid = (start+end)/2;
		int l = mid;
		int r = mid+1;
		
		res = Math.max(divCon(map, start, mid), divCon(map, mid+1, end));
		
		int min = Math.min(map[l], map[r]);
		int cnt = 2;
		res = Math.max(min*cnt, res);
		while(l>start || r<end) {
			if((l==start) || (r<end && map[l-1]< map[r+1])) {
				min = Math.min(min, map[r+1]);
				r++;
			} else {
				min = Math.min(min, map[l-1]);
				l--;
			}
			cnt++;
			res = Math.max((long) min*cnt, res);
		}
		
		return res;
	}
}
