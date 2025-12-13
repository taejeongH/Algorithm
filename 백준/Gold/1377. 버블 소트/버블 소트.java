//package BOJ.버블소트;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][2];
		for (int i=0; i<N; i++) {
			map[i][0] = Integer.parseInt(br.readLine());
			map[i][1] = i;
		}
		
		Arrays.sort(map, (o1, o2) -> Integer.compare(o1[0], o2[0]));
		
		int res = 0;
		for (int i=0; i<N; i++) {
			res = Math.max(map[i][1] - i, res);
		}
		System.out.println(res+1);
		
	}
}
