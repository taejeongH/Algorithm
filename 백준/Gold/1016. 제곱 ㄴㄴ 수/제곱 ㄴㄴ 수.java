//package BOJ.제곱ㄴㄴ수;

import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		HashMap<Long, Integer> map = new HashMap<>();
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		int e = (int) (Math.sqrt(max));
		
		int answer = (int) (max-min+1);
		for (int i=2; i<=e; i++) {
			long pow = 1L * i*i;
			
			long start = min/pow;
			for (long j=start; j<=max/pow; j++) {
				long num = pow*j;
				if (min <= num && num <= max) {
					if (map.containsKey(num)) continue;
					map.put(num, 1);
					answer--;
				}
			}
		}
		
		System.out.println(answer);
	}
}