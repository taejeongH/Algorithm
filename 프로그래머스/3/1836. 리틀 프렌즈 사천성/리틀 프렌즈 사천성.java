import java.util.*;
class Solution {
    char[][] map;
    int m, n;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    boolean[][] v;
    public String solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        int[][] pos = new int[26][2];
        map = new char[m][n];
        
        int cnt = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                
                map[i][j] = board[i].charAt(j);
                if (map[i][j] < 'A' || map[i][j] > 'Z') continue;
                int idx = map[i][j]-'A';
                if (pos[idx][0] == 0) {
                    pos[idx][0] = i+1;
                    pos[idx][1] = j+1;
                    cnt++;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int res = 0;
        for (int c=0; c<cnt; c++) {
            for (int i=0; i<26; i++) {
                if (pos[i][0]==0) continue;
                
                v = new boolean[m][n];
                
                int y = pos[i][0]-1;
                int x = pos[i][1]-1;
                boolean canRemove = false;
                v[y][x] = true;
                for (int k=0; k<4; k++) {
                    int ny = y+dy[k];
                    int nx = x+dx[k];
                    if(ny<0 || ny>=m || nx<0 || nx>=n) continue;
                    if (map[ny][nx]!='.' && map[ny][nx]!=map[y][x]) continue;
                        
                    if (dfs(y, x, ny, nx, k, 0)) {
                        canRemove = true;
                        pos[i][0] = 0;
                        pos[i][1] = 0;
                        res++;
                        break;
                    }
                }
                
                if (canRemove) {
                    sb.append(Character.toString((char) (i+'A')));
                    break;
                }
            }
            // for (int a=0; a<m; a++) System.out.println(Arrays.toString(map[a]));
            // System.out.println();
        }

        
        
        return res!=cnt?"IMPOSSIBLE":sb.toString();
    }
    
    boolean dfs(int sy, int sx, int y, int x, int dir, int cnt) {
        if (map[y][x]==map[sy][sx]) {
            map[y][x] = '.';
            map[sy][sx] = '.';
            return true;
        }
        
        for (int i=0; i<4; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
            int ncnt = cnt+(dir==i?0:1);
            
            if(ny<0 || ny>=m || nx<0 || nx>=n || ncnt>1 || v[ny][nx]) continue;
            if (map[ny][nx]=='.' || map[ny][nx]==map[sy][sx]) {
                v[ny][nx] = true;
                if (dfs(sy, sx, ny, nx, i, ncnt)) return true;
                v[ny][nx] = false;
            }
        }
        
        return false;
    }
}