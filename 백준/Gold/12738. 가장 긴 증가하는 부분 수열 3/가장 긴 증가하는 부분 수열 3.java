//package BOJ.가장긴증가하는부분수열3;

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
		ArrayList<Integer> lis = new ArrayList<>();
		
		
		for (int i=0; i<N; i++) {
			int pos = binarySearch(lis, map[i]);
			
			if(pos == lis.size()) {
				lis.add(map[i]);
			} else {
				lis.set(pos, map[i]);
			}
		}
		
		System.out.println(lis.size());
	}
	public static int binarySearch(List<Integer> lis, int key) {
		int left = 0;
		int right = lis.size()-1;
		while(left<=right) {
			int mid = left + (right-left)/2;
			
			if (lis.get(mid) == key) {
				return mid;
			} else if (lis.get(mid)<key) {
				left = mid+1;
			} else {
				right = mid-1;
			}
		}
		return left;
	}
	
}
