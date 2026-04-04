import java.util.*;
class Solution {
    class Result{
        boolean win;
        int cnt;
        
        Result(boolean win, int cnt) {
            this.win = win;
            this.cnt = cnt;
        }
    }
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    int N, M;
    int[][] map;
    final int INF = 1_000_000_000;
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        map = board;
        N = board.length;
        M = board[0].length;
        
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1], true).cnt;
    }
    
    Result dfs(int AY, int AX, int BY, int BX, boolean turn) {
        int y = turn?AY:BY;
        int x = turn?AX:BX;
        if (map[y][x] == 0) return new Result(false, 0);
        int winMin = INF;
        int loseMax = 0;
        boolean canMove = false;
        boolean canWin = false;
        
        for (int i=0; i<4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if (ny < 0 || ny>=N || nx<0 || nx>=M || map[ny][nx] == 0) continue;
            
            map[y][x] = 0;
            Result nxt = dfs(turn?ny:AY, turn?nx:AX, turn?BY:ny, turn?BX:nx, turn?false:true);
            map[y][x] = 1;
            
            canMove = true;
            
            if (nxt.win) {
                loseMax = Math.max(loseMax, nxt.cnt+1);
            } else {
                canWin = true;
                winMin = Math.min(winMin, nxt.cnt+1);
            }
        }
        
        if (!canMove) return new Result(false, 0);
        
        if (canWin) return new Result(true, winMin);
        return new Result(false, loseMax);
    }

}