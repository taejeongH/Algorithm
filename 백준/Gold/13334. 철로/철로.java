//package BOJ.철로;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a > b) { // 정렬
                int t = a;
                a = b;
                b = t;
            }

            arr[i][0] = a;
            arr[i][1] = b;
        }

        int L = Integer.parseInt(br.readLine());

        // 끝점 기준 정렬
        Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int res = 0;

        for (int i = 0; i < N; i++) {
            int a = arr[i][0];
            int b = arr[i][1];

            if (b - a > L) continue;

            pq.add(a);

            int start = b - L;

            while (!pq.isEmpty() && pq.peek() < start) {
                pq.poll();
            }

            res = Math.max(res, pq.size());
        }

        System.out.println(res);
    }
}
