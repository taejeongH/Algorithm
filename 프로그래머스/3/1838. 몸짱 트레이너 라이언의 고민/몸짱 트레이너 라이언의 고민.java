import java.util.*;

class Solution {

    public int solution(int n, int m, int[][] timetable) {
        int maxPeople = getMaxOverlap(timetable);

        if (maxPeople <= 1) return 0;

        for (int dist = 2 * n - 2; dist >= 1; dist--) {
            if (maxPlacable(n, dist) >= maxPeople) {
                return dist;
            }
        }

        return 0;
    }

    private int getMaxOverlap(int[][] timetable) {
        // 600 ~ 1320 inclusive, diff[end + 1]까지 써야 해서 722칸
        int[] diff = new int[722];

        for (int[] t : timetable) {
            int start = t[0] - 600;
            int end = t[1] - 600;
            diff[start]++;
            diff[end + 1]--;
        }

        int cur = 0;
        int max = 0;
        for (int i = 0; i <= 720; i++) {
            cur += diff[i];
            max = Math.max(max, cur);
        }
        return max;
    }

    private int maxPlacable(int n, int dist) {
        if (dist == 1) {
            return n * n;
        }

        if (dist == 2) {
            // 체커보드
            return (n * n + 1) / 2;
        }

        int best = 0;

        // 첫 행의 가장 왼쪽 선택 열 sx를 전부 시도
        for (int sx = 0; sx < n; sx++) {
            List<int[]> placed = new ArrayList<>();
            placed.add(new int[]{0, sx});

            for (int y = 0; y < n; y++) {
                int startX = (y == 0) ? sx + 1 : 0;

                for (int x = startX; x < n; x++) {
                    if (canPlace(placed, y, x, dist)) {
                        placed.add(new int[]{y, x});
                    }
                }
            }

            best = Math.max(best, placed.size());
        }

        return best;
    }

    private boolean canPlace(List<int[]> placed, int y, int x, int dist) {
        for (int[] p : placed) {
            int d = Math.abs(p[0] - y) + Math.abs(p[1] - x);
            if (d < dist) {
                return false;
            }
        }
        return true;
    }
}