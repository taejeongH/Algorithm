//package BOJ.경찰차;

import java.io.*;
import java.util.*;

public class Main {

    static int N, W;
    static int[][] event;
    static int[][] dp;

    static int[][] prevA;  // 이전 상태의 a
    static int[][] prevB;  // 이전 상태의 b
    static int[][] move;   // 1이면 경찰1이 처리, 2이면 경찰2가 처리

    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        event = new int[W + 1][2];
        for (int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());
            event[i][0] = Integer.parseInt(st.nextToken());
            event[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[W + 1][W + 1];
        prevA = new int[W + 1][W + 1];
        prevB = new int[W + 1][W + 1];
        move = new int[W + 1][W + 1];

        for (int i = 0; i <= W; i++)
            Arrays.fill(dp[i], INF);

        dp[0][0] = 0;

        // ===== Bottom-up DP =====
        for (int a = 0; a <= W; a++) {
            for (int b = 0; b <= W; b++) {
                if (dp[a][b] == INF) continue;

                int next = Math.max(a, b) + 1;
                if (next > W) continue;

                // --- 경찰 1이 처리 ---
                int costA = dp[a][b] + distA(a, next);
                if (dp[next][b] > costA) {
                    dp[next][b] = costA;
                    prevA[next][b] = a;
                    prevB[next][b] = b;
                    move[next][b] = 1; 
                }

                // --- 경찰 2가 처리 ---
                int costB = dp[a][b] + distB(b, next);
                if (dp[a][next] > costB) {
                    dp[a][next] = costB;
                    prevA[a][next] = a;
                    prevB[a][next] = b;
                    move[a][next] = 2;
                }
            }
        }

        // ===== 최소 거리 찾기 =====
        int ans = INF;
        int endA = 0, endB = 0;

        for (int i = 0; i <= W; i++) {
            if (dp[i][W] < ans) {
                ans = dp[i][W];
                endA = i; endB = W;
            }
            if (dp[W][i] < ans) {
                ans = dp[W][i];
                endA = W; endB = i;
            }
        }

        System.out.println(ans);

        // ===== 경로 복원 (역추적) =====
        Stack<Integer> stack = new Stack<>();
        int a = endA, b = endB;

        while (a != 0 || b != 0) {
            stack.push(move[a][b]);
            int na = prevA[a][b];
            int nb = prevB[a][b];
            a = na;
            b = nb;
        }

        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }

    static int distA(int a, int next) {
        if (a == 0)
            return Math.abs(1 - event[next][0]) + Math.abs(1 - event[next][1]);
        return Math.abs(event[a][0] - event[next][0])
             + Math.abs(event[a][1] - event[next][1]);
    }

    static int distB(int b, int next) {
        if (b == 0)
            return Math.abs(N - event[next][0]) + Math.abs(N - event[next][1]);
        return Math.abs(event[b][0] - event[next][0])
             + Math.abs(event[b][1] - event[next][1]);
    }
}
