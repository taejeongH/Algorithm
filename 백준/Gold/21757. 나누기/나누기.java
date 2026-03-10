//package BOJ.나누기;

import java.io.*;
import java.util.*;

public class Main{
	static int N;
	static int[] sumArr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		sumArr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			sumArr[i] = sumArr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		for (int i=1; i<=N; i++) {
			if(sumArr[i] - sumArr[0] == sumArr[N] - sumArr[i]) {
				res += dfs(1, i) * dfs(i+1, N);
			}
		}
		System.out.println(res);
	}

	static int dfs(int l, int r) {
		int res = 0;
		
		for (int i=l; i<=r; i++) {
			if(sumArr[i] - sumArr[l-1] == sumArr[r] - sumArr[i]) {
				res++;
			}
		}
		return res;
		
	}
	
}