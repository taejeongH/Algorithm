//package BOJ.팰린드롬분할;

import java.io.*;
import java.util.*;

public class Main {
	static String s;
	static int N;
	static int[] dp;
	static boolean[][] palindrome;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		N = s.length();
		
		dp = new int[N];
		Arrays.fill(dp, -1);
		
		palindrome = new boolean[N][N];
		
		for (int len=1; len<=N; len++) {
			for (int i=0; i+len-1<N; i++) {
				int j = i+len-1;
				
				if(s.charAt(i)==s.charAt(j)) {
					if (len <=2 ) palindrome[i][j]=true;
					else palindrome[i][j] = palindrome[i+1][j-1]; 
				}
			}
		}
		
		
		System.out.println(dfs(0));
	}
	
	public static int dfs(int start) {
		if (start >= N) return 0;
		if(dp[start]!=-1) return dp[start];
		
		int res = dfs(start+1) + 1;
		for (int i=start+1; i<N; i++) {
			if (palindrome[start][i]) {
				res = Math.min(dfs(i+1)+1, res);
			}
		}
		
		return dp[start]=res;
	}
	
}


/*
	첫 번째에서 최대를 안고르는게 최적의 해일 경우가 있나
	


*/