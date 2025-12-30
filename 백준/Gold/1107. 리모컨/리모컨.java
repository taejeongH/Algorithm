//package BOJ.리모컨;

import java.io.*;
import java.util.*;

public class Main {
	static String goal;
	static int N;
	static boolean[] broken;
	static int res;
	static String resNum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		goal = br.readLine();
		N = goal.length();
		int M = Integer.parseInt(br.readLine());
		
		broken = new boolean[10];
		if (M>0) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				broken[Integer.parseInt(st.nextToken())] = true;
			}
		}
		res = Integer.MAX_VALUE;
		if (Integer.parseInt(goal) == 100) {
			System.out.println(0);
			return;
		}
		
		resNum = "";
		dfs(0, new StringBuilder());
		
		int ans = res + resNum.length();
		if (res == Integer.MAX_VALUE) ans = Integer.MAX_VALUE;
		
		System.out.println(Math.min(ans, Math.abs(Integer.parseInt(goal)-100)));
	}
	
	public static void dfs(int depth, StringBuilder num) {
		if(num.length()!=0) {
			int sub = Math.abs(Integer.parseInt(goal) - Integer.parseInt(num.toString()));
			if (res > sub) {
				res = sub;
				resNum = num.toString();
			} else if(res == sub) {
				if (resNum.length() > num.length()) {
					resNum = num.toString();
				}
			}
		}
		if(depth == N+1) {
			return;
		}
		
		for (int i=0; i<10; i++) {
			if(!broken[i]) {
				StringBuilder sb = new StringBuilder(num.toString());
				dfs(depth+1, sb.append(i));
			}
		}
		
	}
}
