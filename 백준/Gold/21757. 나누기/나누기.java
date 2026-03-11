//package BOJ.나누기;

import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		if (sum % 4 != 0) {
			System.out.println(0);
			return;
		}
		int target = sum/4;
		long prefix = 0;
		long count1 = 0;
		long count2 = 0;
		long res = 0;
		
		for (int i=0; i<N-1; i++) {
			
			prefix += arr[i];
			
			if (prefix == target*3) {
				res += count2;
			}
			
			if (prefix == target*2) {
				count2 += count1;
			}
			
			if (prefix == target) {
				count1++;
			}
			
		}
		
		System.out.println(res);
	}
}

