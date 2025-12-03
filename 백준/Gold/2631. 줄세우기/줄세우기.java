//package BOJ.줄세우기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> lca = new ArrayList<>();
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			int idx = upperBound(lca, num);
			if (idx >= lca.size()) {
				lca.add(num);
			} else {
				lca.set(idx, num);
			}
		}
		System.out.println(N-lca.size());
		
	}
	
	public static int upperBound(ArrayList<Integer> map, int key) {
		int left = 0;
		int right = map.size();
		while(left < right) {
			int mid = (left+right)/2;
			if (map.get(mid) <= key){
				left = mid+1;
			} else {
				right = mid;
			}
		}
		
		return right;
	}
}
