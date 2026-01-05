//package BOJ.나머지합;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] cnt = new long[M];

        cnt[0] = 1;

        st = new StringTokenizer(br.readLine());
        long prefix = 0;

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            prefix = (prefix + x) % M;
            cnt[(int) prefix]++;
        }

        long res = 0;
        for (int r = 0; r < M; r++) {
            long c = cnt[r];
            res += c * (c - 1) / 2; // nC2
        }

        System.out.println(res);
    }
}
