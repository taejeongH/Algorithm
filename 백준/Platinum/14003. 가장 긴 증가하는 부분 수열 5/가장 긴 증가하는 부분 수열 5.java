//package BOJ.가장긴증가하는부분수열5;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> lis = new ArrayList<>();
		ArrayList<Integer> lisIdx = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] map = new int[N];
		int[] pos = new int[N];
		int[] prev = new int[N];
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			map[i] = num;
			int idx = binarySearch(lis, num);
			pos[i]=idx;
			if(idx<=0) {
				prev[i] = -1;
			} else {
				prev[i] = lisIdx.get(idx-1);
			}
			
			if (idx>=lis.size()) {
				lis.add(num);
				lisIdx.add(i);
			} else {
				lis.set(idx, num);
				lisIdx.set(idx, i);
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(lis.size()).append("\n");
		
		int cur = lisIdx.get(lisIdx.size()-1);
		Stack<Integer> stk = new Stack<>();
		while(cur != -1) {
			stk.add(map[cur]);
			cur = prev[cur];
		}
		
		while(!stk.isEmpty()) {
			sb.append(stk.pop()).append(" ");
		}
		System.out.print(sb);
	}
	public static int binarySearch(ArrayList<Integer> arr, int num) {
		int l = 0;
		int r = arr.size();
		while(l<r) {
			int mid = (l+r)/2;
			
			if (arr.get(mid) < num) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		
		return r;
	}
}

