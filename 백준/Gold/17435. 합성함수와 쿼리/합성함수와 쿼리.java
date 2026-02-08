//package BOJ.합성함수와쿼리;

import java.io.*;
import java.util.*;

public class Main {

    static final int LOG = 30;
    static int[][] dp;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[LOG + 1][N + 1];

        for (int i = 0; i <= LOG; i++) {
            Arrays.fill(dp[i], -1);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            dp[0][i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            sb.append(query(n, x)).append("\n");
        }

        System.out.println(sb);
    }

    static int query(int n, int x) {
        int cur = x;

        for (int k = 0; k <= LOG; k++) {
            if ((n & (1 << k)) != 0) {
                cur = jump(k, cur);
            }
        }
        return cur;
    }

    static int jump(int k, int x) {
        if (dp[k][x] != -1) return dp[k][x];

        int mid = jump(k - 1, x);
        dp[k][x] = jump(k - 1, mid);

        return dp[k][x];
    }
}
