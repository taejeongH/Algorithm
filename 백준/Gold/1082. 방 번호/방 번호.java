//package BOJ.방번호;

import java.io.*;
import java.util.*;

public class Main {
	static int N, min;
	static int[] price;
	static String[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		price = new int[N];
		min = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
			min = Math.min(price[i], min);
		}
		int M = Integer.parseInt(br.readLine());
		dp = new String[M+1];
		Arrays.fill(dp, "");
		String ans = dfs(M);
		System.out.println(ans==""?"0":sortDesc(ans));
	}
	
	public static String dfs(int money) {
		if (money<0) return null;
		if (money == 0) return "";
		if(dp[money]!="") return dp[money];
		
		String res = "";
		for (int i=N-1; i>=0; i--) {
			if(money>=price[i]) {
				String nxt = dfs(money-price[i]);
				
				if(nxt==null) continue;
				if(nxt.equals("") && i==0) continue;
				String candidate = nxt + i;
				if (isBetter(candidate, res)) res = candidate;
			}
		}
		
		return dp[money]=res;
	}
	
	public static boolean isBetter(String a, String b) {
		if(b.equals("")) return true;
		if (a.length() != b.length()) return a.length() > b.length();
		return a.compareTo(b) > 0;
	}
	
	static String sortDesc(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new StringBuilder(new String(arr)).reverse().toString();
    }
}


/*
	길이가 길어야 하기 때문에 길이가 긴 결과들을 저장하고
	내림차순으로 정렬 -> 가장 큰 값
*/