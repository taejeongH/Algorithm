//package BOJ.부분배열고르기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] map;
	static final int INF = 1_000_000_000;
	static long[] sum;
	static int N;

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		map = new int[N+1];
		sum = new long[N+1];
		for (int i=1; i<=N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			sum[i] = map[i] + sum[i-1];
		}
		
		System.out.println(divCon(1, N));
	}
	
	static long divCon(int start, int end) {
		if (start == end) return (long) map[start] * map[start];
		int mid = (start+end)/2;
		
		int l = mid;
		int r = mid+1;
		int minVal = Math.min(map[l], map[r]);
		long res = minVal * (sum[r] - sum[l-1]);
		while (l > start || r < end) {
		    if (r < end && (l == start || map[l - 1] < map[r + 1])) {
		        r++;
		        minVal = Math.min(minVal, map[r]);
		    } else {
		        l--;
		        minVal = Math.min(minVal, map[l]);
		    }
		    res = Math.max(res, minVal * (sum[r] - sum[l-1]));
		}
		
		res = Math.max(res, divCon(start, mid));
		res = Math.max(res, divCon(mid+1, end));
		
		return res;
	}
}
