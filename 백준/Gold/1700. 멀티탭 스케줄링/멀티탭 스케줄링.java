//package BOJ.멀티탭스케줄링;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] map = new int[K];
		int[] nxt = new int[K];
		
		
		Set<Integer> set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<K; i++) {
			int nxtIdx = K;
			for (int j=i+1; j<K; j++) {
				if(map[i] == map[j]) {
					nxtIdx = j;
					break;
				}
			}
			nxt[i] = nxtIdx;
		}
		
		int res = 0;
		for (int i=0; i<K; i++) {
			if (set.size()<N) {
				boolean isContain = false;
				int containIdx = 0;
				for (int n : set) {
					if(map[n] == map[i]) {
						containIdx = n;
						isContain = true;
						break;
					}
				}
				if(isContain) {
					set.remove(containIdx);
					set.add(i);
					continue;
				}
				set.add(i);
			} else {
				int highNxt = 0;
				int highNum = 0;
				boolean isContain = false;
				int containIdx = 0;
				for (int n : set) {
					if(map[n] == map[i]) {
						isContain = true;
						containIdx = n;
						break;
					}
					if (nxt[n] > highNxt) {
						highNum = n;
						highNxt = nxt[n];
					}
				}
				if(isContain) {
					set.remove(containIdx);
					set.add(i);
					continue;
				}
				set.remove(highNum);
				res++;
				set.add(i);
			}
//			System.out.println(set);
		}
		System.out.println(res);
		
	}
}
