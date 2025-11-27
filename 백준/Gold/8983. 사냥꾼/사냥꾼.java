//package BOJ.사냥꾼;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken()); //사대의 수
		int N = Integer.parseInt(st.nextToken()); //동물의 수
		int L = Integer.parseInt(st.nextToken()); //사정거리
		
		int[] hunterPos = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			hunterPos[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(hunterPos);
		int res = 0;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int min = y+x-L;
			int max = L-y+x;
			
			int idx = upperBound(hunterPos, min);
			
			if (hunterPos[idx] >= min && hunterPos[idx]<=max) res++;
		}
		System.out.println(res);
	}
	
	public static int upperBound(int[] map, int key) {
		int l = 0;
		int r = map.length-1;
		while(l<r) {
			int mid = (l+r)/2;
			if (map[mid] < key) {
				l = mid+1;
			} else {
				r = mid;
			}
			
		}
		return r;
	}
}

/*
	N마리의 동물
	
	일직선 상에 위치한 M개의 사대에서만 사격 가능
	
	사정거리 L이 주어졌을 때 잡을 수 있는 동물의 수
	
*/