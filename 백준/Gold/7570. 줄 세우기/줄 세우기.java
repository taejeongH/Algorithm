//package BOJ.줄세우기;

import java.io.*;
import java.util.*;

public class Main{
	static int N;
	static int[] map, pos ,dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		map = new int[N];
		pos = new int[N];
		for (int i=0; i<N; i++ ) {
			map[i] = Integer.parseInt(st.nextToken());
			pos[map[i]-1] = i;
		}
		dp = new int[N];
		Arrays.fill(dp, -1);
		int res = 0;
		for (int i=0; i<N; i++) {
			res = Math.max(dfs(i)+1, res);
		}
		
		System.out.println(N-res);
	}
	
	public static int dfs(int idx){
		if(idx>=N) return 0;
		if(dp[idx]!=-1) return dp[idx];
		
		int res = 0;
		int num = map[idx];
		if (num < N && pos[num] > idx) {
			res = dfs(pos[num])+1;
		}
		
		return dp[idx]=res;
	}
	
	
}