//package BOJ.스터디그룹;

import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //학생 수
		int K = Integer.parseInt(st.nextToken()); //알고리즘 수
		int D = Integer.parseInt(st.nextToken()); //실력차이 max
		
		int[][] map = new int[N][3]; // count, skill, algo
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int key = 0;
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				key += (1<<(Integer.parseInt(st.nextToken())-1));
			}
			map[i][0] = M;
			map[i][1] = d;
			map[i][2] = key;
		}
		
		Arrays.sort(map, (o1, o2) -> o1[1] - o2[1]);
		
		int res = 0;
		int lastIdx = 0;
		
		int[] count = new int[K];
		
		for (int i=0; i<N; i++) {
			int curSkill = map[i][1];

			while(lastIdx < N && map[lastIdx][1] - curSkill <= D) {
				for (int j=0; j<K; j++) {
					int key = (1<<j);
					if ((map[lastIdx][2] & key) == key) count[j]++;
				}
				lastIdx++;
			}

			int sum = lastIdx - i;
			int same = 0;
			int dup = 0;
			for (int j=0; j<K; j++) {
				if (count[j] == sum) dup++;
				if (count[j] != 0) same++;
			}
			
			for (int j=0; j<K; j++) {
				int key = 1<<j;
				if ((map[i][2] & key) == key) count[j]--;
			}
			res = Math.max((same - dup) * sum , res);
		}
		System.out.println(res);
	}
	
	
	
}