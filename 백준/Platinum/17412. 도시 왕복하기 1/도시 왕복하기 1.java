//package BOJ.도시왕복하기1;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] capacity;
    static int[][] flow;
    static List<Integer>[] adj;

    static int maxFlow(int s, int t) {
        int total = 0;

        while (true) {
            int[] parent = new int[N + 1];
            Arrays.fill(parent, -1);
            Queue<Integer> q = new ArrayDeque<>();

            q.add(s);
            parent[s] = s;

            // BFS 증가경로 탐색
            while (!q.isEmpty() && parent[t] == -1) {
                int u = q.poll();

                for (int v : adj[u]) {
                    if (parent[v] == -1 &&
                        capacity[u][v] - flow[u][v] > 0) {
                        parent[v] = u;
                        q.add(v);
                    }
                }
            }

            if (parent[t] == -1) break;

            int pathFlow = 1; // 모든 간선 용량이 1

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow;
            }

            total += pathFlow;
        }

        return total;
    }

    static void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);      // residual
        capacity[u][v] = 1; // 방향 간선
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        capacity = new int[N + 1][N + 1];
        flow = new int[N + 1][N + 1];
        adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            addEdge(u, v);
        }

        System.out.println(maxFlow(1, 2));
    }
}