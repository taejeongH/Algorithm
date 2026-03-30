import java.util.*;
class Solution {
    int[] sales;
    int[][] dp;
    List<Integer>[] g;
    final int INF = 1_000_000_000;
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        this.sales = sales;
        g = new List[sales.length+1]; for (int i=1; i<=sales.length; i++) g[i] = new ArrayList<>();
        for (int i=0; i<links.length; i++) {
            int p = links[i][0];
            int c = links[i][1];
            g[p].add(c);
        }
        dp = new int[sales.length+1][2];
        dfs(1);
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    void dfs(int node) {
        for (int child : g[node]) {
            dfs(child);
        }
        
        dp[node][0] = sales[node-1];
        dp[node][1]=0;
        int min = INF;
        for (int child : g[node]) {
            //1. 부모가 선택되는 경우
            dp[node][0] += Math.min(dp[child][0], dp[child][1]);
            
            
            //2. 자식이 선택되는 경우
            int others = 0;
            for (int c : g[node]) {
                if (child==c) continue;
                others += Math.min(dp[c][0], dp[c][1]);
            }
            min = Math.min(dp[child][0] + others, min);
        }
        dp[node][1] += min==INF?0:min;
        

    }
}

/*
    부모를 선택했다면 -> 책임은 자식에게로? 만약 자식이 없는 경우?
*/