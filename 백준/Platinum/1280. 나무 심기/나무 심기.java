//package BOJ.나무심기;

import java.io.*;
import java.util.*;

public class Main {
    
    static final int MOD = 1_000_000_007;
    // 좌표의 최댓값은 200,000입니다.
    static final int MAX_COORD = 200_000;
    
    static long[] cntTree; // 나무의 개수 저장
    static long[] sumTree; // 나무의 좌표 합 저장
    static int S = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 세그먼트 트리 사이즈 조정 (리프 노드가 200001개 필요)
        while (S <= MAX_COORD) {
            S *= 2;
        }
        cntTree = new long[S * 2];
        sumTree = new long[S * 2];
        
        long ans = 1;
        
        // 첫 번째 나무 처리 (비용 0, 트리 업데이트만 수행)
        // 입력은 N개의 줄에 걸쳐 주어집니다.
        
        // 나무들을 순서대로 처리
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            
            // 첫 번째 나무는 비용 계산 없이 심기만 함
            if (i > 0) {
                // 1. x보다 왼쪽에 있는 나무들 (0 ~ x-1)
                // 개수 * 현재좌표 - 좌표합
                long leftCnt = query(cntTree, 0, x - 1);
                long leftSum = query(sumTree, 0, x - 1);
                long leftCost = (leftCnt * x) - leftSum;
                
                // 2. x보다 오른쪽에 있는 나무들 (x+1 ~ MAX)
                // 좌표합 - 개수 * 현재좌표
                long rightCnt = query(cntTree, x + 1, MAX_COORD);
                long rightSum = query(sumTree, x + 1, MAX_COORD);
                long rightCost = rightSum - (rightCnt * x);
                
                long totalCost = (leftCost + rightCost) % MOD;
                
                ans = (ans * totalCost) % MOD;
            }
            
            // 현재 나무 정보를 트리에 업데이트 (Point Update)
            // 좌표 x에 나무 1개 추가
            update(cntTree, x, 1);
            // 좌표 x에 좌표값 x 추가
            update(sumTree, x, x);
        }
        
        System.out.println(ans);
    }
    
    // 점 업데이트 (Point Update)
    // target 인덱스에 val을 더함 (Bottom-Up 방식)
    static void update(long[] tree, int target, long val) {
        int node = S + target;
        while (node > 0) {
            tree[node] += val;
            node /= 2;
        }
    }
    
    // 구간 합 쿼리 (Range Sum Query)
    // [left, right] 구간의 합 (Bottom-Up 방식)
    static long query(long[] tree, int left, int right) {
        long sum = 0;
        int start = S + left;
        int end = S + right;
        
        while (start <= end) {
            if (start % 2 == 1) sum += tree[start++];
            if (end % 2 == 0) sum += tree[end--];
            start /= 2;
            end /= 2;
        }
        return sum;
    }
}