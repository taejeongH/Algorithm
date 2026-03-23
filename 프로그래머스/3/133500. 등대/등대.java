import java.util.*;
class Solution {
    boolean[] v;
    List<Integer>[] g;
    int[][] dp;
    public int solution(int n, int[][] lighthouse) {
        g = new List[n+1]; for (int i=1; i<=n; i++) g[i] = new ArrayList<>();
        v = new boolean[n+1];
        dp = new int[n+1][2];
        for (int i=0; i<n-1; i++) {
            int s = lighthouse[i][0];
            int e = lighthouse[i][1];
            
            g[s].add(e);
            g[e].add(s);
        }
        dfs(1);
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    void dfs(int node) {
        v[node] = true;
        
        dp[node][0] = 0;
        dp[node][1] = 1;
        
        for (int nxt : g[node]) {
            if (v[nxt]) continue;
            
            dfs(nxt);
            
            dp[node][0] += dp[nxt][1];
            dp[node][1] += Math.min(dp[nxt][0], dp[nxt][1]);
        }
        return;
    }
}