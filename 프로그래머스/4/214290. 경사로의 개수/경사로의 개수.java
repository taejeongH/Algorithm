import java.util.*;

class Solution {
    int N, M;
    long K;
    int[] d;
    int[][] grid;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    final int MOD = 1_000_000_007;

    long[][] adj;
    Map<String, long[]> memo = new HashMap<>();

    public int solution(int[][] grid, int[] d, int k) {
        this.N = grid.length;
        this.M = grid[0].length;
        this.K = k;
        this.d = d;
        this.grid = grid;

        int size = N * M;
        adj = new long[size][size];

        // 1. memo dfs로 adj 구성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                long[] res = dfs(i, j, 0);
                int start = i * M + j;

                for (int k2 = 0; k2 < size; k2++) {
                    adj[start][k2] = res[k2] % MOD;
                }
            }
        }

        // 2. 행렬 거듭제곱
        long[][] AK = pow(adj, K);

        // 3. 전체 합
        long answer = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                answer = (answer + AK[i][j]) % MOD;
            }
        }

        return (int) answer;
    }

    // memo DFS
    long[] dfs(int y, int x, int cur) {
        if (cur == d.length) {
            long[] res = new long[N * M];
            res[y * M + x] = 1;
            return res;
        }

        String key = y + "," + x + "," + cur;
        if (memo.containsKey(key)) return memo.get(key);

        long[] res = new long[N * M];

        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

            if (grid[ny][nx] - grid[y][x] == d[cur]) {
                long[] tmp = dfs(ny, nx, cur + 1);

                for (int i = 0; i < N * M; i++) {
                    res[i] = (res[i] + tmp[i]) % MOD;
                }
            }
        }

        memo.put(key, res);
        return res;
    }

    // 행렬 곱
    long[][] mul(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }

    // 단위 행렬
    long[][] identity(int n) {
        long[][] I = new long[n][n];
        for (int i = 0; i < n; i++) I[i][i] = 1;
        return I;
    }

    // 빠른 거듭제곱
    long[][] pow(long[][] A, long k) {
        int n = A.length;
        long[][] res = identity(n);

        while (k > 0) {
            if ((k & 1) == 1) res = mul(res, A);
            A = mul(A, A);
            k >>= 1;
        }
        return res;
    }
}