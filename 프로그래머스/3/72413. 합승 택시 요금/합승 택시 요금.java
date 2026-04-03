import java.util.*;
class Solution {
    final int INF = 1_000_000_000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        
        List<int[]>[] g = new List[n+1]; for(int i=1; i<=n; i++) g[i] = new ArrayList<>();
        
        int[][] distance = new int[n+1][n+1];
        for (int i=1; i<=n; i++) Arrays.fill(distance[i], INF);
        
        for (int i=0; i<fares.length; i++) {
            int f = fares[i][0];
            int t = fares[i][1];
            int c = fares[i][2];
            
            distance[f][t] = c;
            distance[t][f] = c;
        }
        
        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (i==j) {
                        distance[i][i] = 0; 
                        continue;
                    }
                    if (distance[i][k] == INF || distance[k][j] == INF) continue;
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        
        for (int i=1; i<=n; i++) {
            // if(i == s) continue;
            //s -> i 까지 같이 가기
            if (distance[s][i]==INF || distance[i][a]==INF || distance[i][b]==INF) continue;
            int dis = distance[s][i] + distance[i][a] + distance[i][b]; 
            answer = Math.min(dis, answer);
        }
        
        return answer;
    }
}