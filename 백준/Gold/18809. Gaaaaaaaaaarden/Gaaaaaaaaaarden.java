//package BOJ.가든;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, G, R;
    static int[][] map;
    static List<int[]> cand = new ArrayList<>();
    static int[] sel;
    static int ans = 0;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static class Node {
        int y, x, c, t;
        Node(int y, int x, int c, int t) {
            this.y = y; this.x = x; this.c = c; this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) cand.add(new int[]{i, j});
            }
        }

        sel = new int[cand.size()];
        dfs(0, 0, 0, 0);
        System.out.println(ans);
    }

    static void dfs(int idx, int start, int g, int r) {
        if (g > G || r > R) return;
        if (idx == G + R) {
            ans = Math.max(ans, bfs());
            return;
        }
        for (int i = start; i < cand.size(); i++) {
            if (sel[i] != 0) continue;
            if (g < G) {
                sel[i] = 1;
                dfs(idx + 1, i + 1, g + 1, r);
                sel[i] = 0;
            }
            if (r < R) {
                sel[i] = 2;
                dfs(idx + 1, i + 1, g, r + 1);
                sel[i] = 0;
            }
        }
    }

    static int bfs() {
        int[][] time = new int[N][M];
        int[][] color = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(time[i], -1);

        ArrayDeque<Node> q = new ArrayDeque<>();
        for (int i = 0; i < sel.length; i++) {
            if (sel[i] != 0) {
                int y = cand.get(i)[0];
                int x = cand.get(i)[1];
                time[y][x] = 0;
                color[y][x] = sel[i];
                q.add(new Node(y, x, sel[i], 0));
            }
        }

        int flowers = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (color[cur.y][cur.x] == 3) continue;
            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (map[ny][nx] == 0) continue;

                if (time[ny][nx] == -1) {
                    time[ny][nx] = cur.t + 1;
                    color[ny][nx] = cur.c;
                    q.add(new Node(ny, nx, cur.c, cur.t + 1));
                } else if (time[ny][nx] == cur.t + 1 && color[ny][nx] != cur.c && color[ny][nx] != 3) {
                    color[ny][nx] = 3;
                    flowers++;
                }
            }
        }
        return flowers;
    }
}
