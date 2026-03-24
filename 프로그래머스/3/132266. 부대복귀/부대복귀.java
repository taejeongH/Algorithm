import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        
        int[] distance = new int[n+1];
        Arrays.fill(distance, -1);
        
        List<Integer>[] g = new List[n+1]; for(int i=1; i<=n; i++) g[i] = new ArrayList<>();
        
        for (int i=0; i<roads.length; i++) {
            int s = roads[i][0];
            int e = roads[i][1];
            g[s].add(e);
            g[e].add(s);
        }
        
        HashSet<Integer> org = new HashSet<>();
        for (int i=0; i<sources.length; i++) {
            org.add(sources[i]);
        }
        
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.add(new int[] {destination, 0});
        boolean[] v = new boolean[n+1];
        v[destination] = true;
        int cnt = 0;
        while(!que.isEmpty()) {
            int[] now = que.poll();
            int node = now[0];
            int dis = now[1];
            
            distance[node] = dis;
            if (org.contains(node)) {
                if (++cnt == org.size()) break;
            }
            
            for (int nxt: g[node]) {
                if (v[nxt]) continue;
                v[nxt] = true;
                que.add(new int[] {nxt, dis+1});
            }
        }
        
        int[] answer = new int[sources.length];
        for (int i=0; i<sources.length; i++) {
            answer[i] = distance[sources[i]];
        }
        return answer;
    }
}