//package BOJ.우수마을;

import java.io.*;
import java.util.*;

public class Main {
    static int[] people;
    static List<Integer>[] g;
    static boolean[] v;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        people = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        g = new List[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            g[b].add(a);
        }

        dp = new int[N + 1][2];
        v = new boolean[N + 1];

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int node) {
        v[node] = true;

        dp[node][1] = people[node]; // 내가 선택됨

        for (int nxt : g[node]) {
            if (v[nxt]) continue;
            dfs(nxt);

            dp[node][0] += Math.max(dp[nxt][0], dp[nxt][1]);
            dp[node][1] += dp[nxt][0];
        }
    }
}