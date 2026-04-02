import java.util.*;
class Solution {
    int N;
    int[][] triangle, dp;
    int[] dx = {0, 1};
    public int solution(int[][] triangle) {
        this.triangle = triangle;
        N = triangle.length;
        dp = new int[N][N];
        for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
        int answer = dfs(0, 0);
        return answer;
    }
    
    int dfs(int y, int x) {
        if (y == N) return 0;
        if (dp[y][x] != -1) return dp[y][x];
        
        int res = 0;
        for (int i=0; i<2; i++) {
            int nx = x+dx[i];
            if (nx<0) continue;
            res = Math.max(dfs(y+1, nx) + triangle[y][x], res);
        }

        return dp[y][x]=res;
    }
}