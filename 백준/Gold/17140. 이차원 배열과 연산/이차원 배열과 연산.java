//package BOJ.이차원배열과연산;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		int k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[100][100];
		for (int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int res = 0;
		int rTotal = 3;
		int cTotal = 3;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1]==o2[1]) return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		
		while(true) {
			if (map[r][c]==k) break;
			if (res>=100) {
				res = -1;
				break;
			}
			if (rTotal >= cTotal) {
				//R연산
				int iter = rTotal;
				for (int i=0; i<iter; i++) {
					int[] cnt = new int[101];
					for (int j=0; j<cTotal; j++) {
						cnt[map[i][j]]++;
					}
					
					for (int j=1; j<=100; j++) {
						if (cnt[j]==0) continue;
						pq.add(new int[] {j, cnt[j]});
					}
					
					cTotal = Math.max(cTotal, pq.size()*2);
					int idx = 0;
					Arrays.fill(map[i], 0);
					while(!pq.isEmpty()) {
						int[] now = pq.poll();
						map[i][idx++] = now[0];
						map[i][idx++] = now[1];
					}
				}
			} else {
				//C연산
				int iter = cTotal;
				for (int i=0; i<iter; i++) {
					int[] cnt = new int[101];
					for (int j=0; j<rTotal; j++) {
						cnt[map[j][i]]++;
					}
					
					for (int j=1; j<=100; j++) {
						if (cnt[j]==0) continue;
						pq.add(new int[] {j, cnt[j]});
					}
					
					rTotal = Math.max(rTotal, pq.size()*2);
					int idx = 0;
					for (int j=0; j<100; j++) {
						map[j][i] = 0;
					}
					while(!pq.isEmpty()) {
						int[] now = pq.poll();
						map[idx++][i] = now[0];
						map[idx++][i] = now[1];
					}
				}
			}
			res++;
//			for (int i=0; i<10; i++) System.out.println(Arrays.toString(map[i]));
//			System.out.println();
		}
		System.out.println(res);
	}
}


/*
	R연산 : 배열 A의 모든 행에 대해서 정렬 수행. 행의 개수 >= 열의 개수인 경우에 적용
	C연산 : 배열 A의 모든 열에 대해서 정렬을 수행. 행의 개수 < 열의 개수인 경우에 적용
	
	
*/