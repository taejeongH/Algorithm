//package BOJ.팰린드롬분할;

import java.io.*;
import java.util.*;

public class Main {
	static String s;
	static int N;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		N = s.length();
		
		dp = new int[N];
		Arrays.fill(dp, -1);
		System.out.println(dfs(0));
	}
	
	public static int dfs(int start) {
		if (start >= N) return 0;
		if(dp[start]!=-1) return dp[start];
		
		int res = dfs(start+1) + 1;
		for (int i=start+1; i<N; i++) {
			if (isPalindrome(start, i)) {
				res = Math.min(dfs(i+1)+1, res);
			}
		}
		
		return dp[start]=res;
	}
	
	public static boolean isPalindrome(int start, int end) {
		
		int i = start;
		int j = end;
		
		while(i<j) {
			if (s.charAt(i)!=s.charAt(j)) return false;
			i++;
			j--;
		}
		
		return true;
	}
}


/*
	첫 번째에서 최대를 안고르는게 최적의 해일 경우가 있나
	


*/