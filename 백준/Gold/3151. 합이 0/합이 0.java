//package BOJ.합이0;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(map);
		long res = 0;
		for (int i=0; i<N; i++) {
			for (int j=i+1; j<N; j++) {
				int key = -(map[j]+map[i]);
				int before = lowerBound(map, key);
				int after = upperBound(map, key)-1;
				
//				System.out.println(key + " " + before + " " + after);
				if(after <0 || map[after]!=key) continue;
				res += Math.max(after - Math.max(before, j+1)+1, 0);
			}
		}
		System.out.println(res);
	}
	
	//이분 탐색 -> key 값이 있는지, 있다면 몇 개 있는지 ?
	public static int lowerBound(int[] map, int key) {
		int l = 0;
		int r = map.length-1;
		
		while(l<r) {
			int mid = (l+r)/2;
			if (map[mid] < key) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		
		return r;
	}
	
	//찾고자 하는 값보다 큰 값이 
	public static int upperBound(int[] map, int key) {
		int l = 0;
		int r = map.length;
		
		while(l<r) {
			int mid = (l+r)/2;
			if (map[mid]<=key) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return r;
	}
}
