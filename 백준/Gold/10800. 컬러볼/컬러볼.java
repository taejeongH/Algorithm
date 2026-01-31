//package BOJ.컬러볼;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][3];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			
			map[i][0] = color;
			map[i][1] = size;
			map[i][2] = i;
		}
		
		Arrays.sort(map, (o1, o2)->o1[1]-o2[1]);
		
		int[] result = new int[N];
        int[] colorSum = new int[N + 1];
        
        int sum = 0;
        int j = 0; 
		for (int i=0; i<N; i++) {
			
			while(map[j][1] < map[i][1]) {
				sum += map[j][1];
				colorSum[map[j][0]] += map[j][1];
				j++;
			}
			
			//전체에서 나보다 작은 것들의 합 - 같은 컬러의 합
			result[map[i][2]] = sum - colorSum[map[i][0]];
		}
		
		StringBuilder sb = new StringBuilder();
		for (int res : result) {
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
}

/*
	나보다 작은 것들의 사이즈 합 - 나보다 작은 것들 중 나와 색깔이 같은 사이즈 합
	
	사이즈 기준 정렬된 전체 컬러가 들어간 배열
	사이즈 기준 정렬된 각각의 컬
*/