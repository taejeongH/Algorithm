import java.io.*;
import java.util.*;

public class Main {

    static void reverse(int[] a, int l, int r, int[] pos) {
        while (l < r) {
            int vl = a[l], vr = a[r];
            a[l] = vr; a[r] = vl;
            pos[vr] = l;
            pos[vl] = r;
            l++; r--;
        }
        if (l == r) pos[a[l]] = l;
    }

    static boolean sorted(int[] a, int n) {
        for (int i = 1; i <= n; i++) if (a[i] != i) return false;
        return true;
    }

    static int[][] solveLeft(int[] orig, int n) {
        int[] a = orig.clone();
        int[] pos = new int[n + 1];
        for (int i = 1; i <= n; i++) pos[a[i]] = i;

        int[][] ans = new int[2][2];

        for (int t = 0; t < 2; t++) {
            int l = -1;
            for (int i = 1; i <= n; i++) {
                if (a[i] != i) { l = i; break; }
            }
            if (l == -1) { // 이미 정렬
                ans[t][0] = 1; ans[t][1] = 1;
                continue;
            }
            int r = pos[l];
            ans[t][0] = l; ans[t][1] = r;
            reverse(a, l, r, pos);
        }
        return sorted(a, n) ? ans : null;
    }

    static int[][] solveRight(int[] orig, int n) {
        int[] a = orig.clone();
        int[] pos = new int[n + 1];
        for (int i = 1; i <= n; i++) pos[a[i]] = i;

        int[][] ans = new int[2][2];

        for (int t = 0; t < 2; t++) {
            int rIdx = -1;
            for (int i = n; i >= 1; i--) {
                if (a[i] != i) { rIdx = i; break; }
            }
            if (rIdx == -1) { // 이미 정렬
                ans[t][0] = 1; ans[t][1] = 1;
                continue;
            }

            int lIdx = pos[rIdx]; // 값 rIdx가 있는 위치
            int l = Math.min(lIdx, rIdx);
            int r = Math.max(lIdx, rIdx);

            ans[t][0] = l; ans[t][1] = r;
            reverse(a, l, r, pos);
        }
        return sorted(a, n) ? ans : null;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int[] a = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());

        int[][] ans = solveLeft(a, N);
        if (ans == null) ans = solveRight(a, N);

        // 문제 특성상 여기까지 오면 보통 항상 해답 존재
        System.out.println(ans[0][0] + " " + ans[0][1]);
        System.out.println(ans[1][0] + " " + ans[1][1]);
    }
}