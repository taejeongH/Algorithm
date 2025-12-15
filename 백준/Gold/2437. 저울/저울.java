//package BOJ.저울;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(map);
		int res = 0;
		int j = 0;
		for (int i=0; i<N; i++) {
			if (map[i] > j+1) {
				break;
			}
			
			j += map[i];
		}
		
		
		System.out.println(j+1);
		
//		System.out.println(Arrays.toString(map));
	}
	
}
