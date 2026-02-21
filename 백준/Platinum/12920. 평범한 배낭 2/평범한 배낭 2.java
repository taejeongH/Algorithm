//package BOJ.평범한배낭2;

import java.io.*;
import java.util.*;

public class Main {

    static class Item {
        int w, v;
        Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int cnt = 1;
            while (k > 0) {
                int take = Math.min(cnt, k);
                items.add(new Item(w * take, v * take));
                k -= take;
                cnt <<= 1;
            }
        }

        int[] dp = new int[M + 1];

        for (Item it : items) {
            for (int j = M; j >= it.w; j--) {
                dp[j] = Math.max(dp[j], dp[j - it.w] + it.v);
            }
        }

        System.out.println(dp[M]);
    }
}