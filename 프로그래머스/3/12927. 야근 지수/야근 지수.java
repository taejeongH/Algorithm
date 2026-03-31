import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long total = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int w : works) {
            total += w;
            pq.offer(w);
        }

        if (total <= n) return 0;

        for (int i = 0; i < n; i++) {
            int cur = pq.poll();
            pq.offer(cur - 1);
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            long x = pq.poll();
            answer += x * x;
        }

        return answer;
    }
}