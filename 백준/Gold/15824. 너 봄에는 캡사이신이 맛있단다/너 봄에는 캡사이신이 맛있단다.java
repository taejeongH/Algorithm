//package BOJ.너봄에는캡사이신이맛있단다;

import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long[] pow = new long[N];
        pow[0] = 1;
        for (int i = 1; i < N; i++) {
            pow[i] = (pow[i-1] * 2) % MOD;
        }

        long ans = 0;
        for (int i = 0; i < N; i++) {
            long maxCnt = pow[i];
            long minCnt = pow[N-i-1];
            long contrib = (arr[i] * (maxCnt - minCnt)) % MOD;
            ans = (ans + contrib) % MOD;
        }

        if (ans < 0) ans += MOD;
        System.out.println(ans);
    }
}