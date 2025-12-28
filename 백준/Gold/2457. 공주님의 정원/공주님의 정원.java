//package BOJ.공주님의정원;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			
			map[i][0] = sm*100 + sd;
			map[i][1] = em*100 + ed;
		}
		
		Arrays.sort(map, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) return o1[1]-o2[1];
				return o1[0]-o2[0];
			}
		});
		
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		
		
		int cur = 301;
		int idx = 0;
		int ans = 0;
		int maxEnd = 0;
		while(cur <= 1130) {
			boolean found = false;
			
			while(idx < N && map[idx][0] <= cur) {
				maxEnd = Math.max(maxEnd, map[idx][1]);
				idx++;
				found = true;
			}
			
			if (!found) {
				ans = 0;
				break;
			}
			
			cur = maxEnd;
			ans++;
		}
		
		System.out.println(ans);
		
	}
	
}
