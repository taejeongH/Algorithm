import java.util.*;
class Solution {
    final int INF = 1_000_000_000;
    public int[] solution(int target) {
        int[] answer = {0, 0};
        
        int[][] v = new int[target+1][2];
        for (int i=0; i<=target; i++) {
            v[i][0] = INF;
        }
        ArrayDeque<int[]> que = new ArrayDeque<>();
        
        que.add(new int[] {0, 0, 0});
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int sum = now[0];
            int dis = now[1];
            int single = now[2];

            if (v[sum][0] < dis || (v[sum][0] == dis && v[sum][1] > single)) continue;

            if (sum + 50 <= target) {
                if (v[sum+50][0] == dis+1 && v[sum+50][1] < single+1) {
                    v[sum+50][0] = dis+1;
                    v[sum+50][1] = single+1;
                    que.add(new int[] {sum+50, dis+1, single+1});
                } else if(v[sum+50][0] > dis+1) {
                    v[sum+50][0] = dis+1;
                    v[sum+50][1] = single+1;
                    que.add(new int[] {sum+50, dis+1, single+1});
                }
            }
            
            //싱글 먼저
            for (int i=1; i<=20; i++) {
                if (sum + i <= target) {
                    que.add(new int[] {sum+i, dis+1, single+1});
                    
                    if (v[sum+i][0] == dis+1 && v[sum+i][1] < single+1) {
                        v[sum+i][0] = dis+1;
                        v[sum+i][1] = single+1;
                        que.add(new int[] {sum+i, dis+1, single+1});
                    } else if(v[sum+i][0] > dis+1) {
                        v[sum+i][0] = dis+1;
                        v[sum+i][1] = single+1;
                        que.add(new int[] {sum+i, dis+1, single+1});
                    }
                    
                }
            }
            
            for (int i=1; i<=20; i++) {
                for (int j=2; j<=3; j++) {
                    if (sum + (i*j) <= target) {
                        if (v[sum + (i*j)][0] == dis+1 && v[sum + (i*j)][1] < single) {
                            v[sum + (i*j)][0] = dis+1;
                            v[sum + (i*j)][1] = single;
                            que.add(new int[] {sum + (i*j), dis+1, single});
                        } else if(v[sum + (i*j)][0] > dis+1) {
                            v[sum + (i*j)][0] = dis+1;
                            v[sum + (i*j)][1] = single;
                            que.add(new int[] {sum + (i*j), dis+1, single});
                        }
                    }
                }
            }
            
        }
        // for (int i=1; i<=target; i++) System.out.println(Arrays.toString(v[i]));
        return v[target];
    }
    
    
}