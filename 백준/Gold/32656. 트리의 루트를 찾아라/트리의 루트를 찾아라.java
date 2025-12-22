//package BOJ.트리의루트를찾아라;

import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] g;
    static int N, a, b, x;
    static int[] belong;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        
        g = new List[N+1]; 
        for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
        
        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            g[s].add(e);
            g[e].add(s);
        }
        
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        
        belong = new int[N+1];
        int idx = 0;
        
        // x를 기준으로 각 서브트리에 번호(ID) 부여
        // x 자신은 belong[x] = 0 유지
        for (int nxt : g[x]) {
            idx++;
            dfs(nxt, x, idx);
        }
        
        //a -> 1, b-> 2
        
        if (belong[a] != 0 && belong[b] != 0 && belong[a] == belong[b]) {
            System.out.println(0);
            return;
        }
        
        int res = 0;
        for (int r=1; r<=N; r++) {
            // [수정된 부분] a나 b가 x인 경우를 고려하여 조건 분기
            boolean checkA = (a == x) || (belong[r] != belong[a]);
            boolean checkB = (b == x) || (belong[r] != belong[b]);
            
            if (checkA && checkB) {
                res++;
            }
        }
        
        System.out.println(res);
    }
    
    public static void dfs(int cur, int parent, int id) {
        belong[cur] = id;
        for (int nxt : g[cur]) {
            if (nxt == parent) continue;
            dfs(nxt, cur, id);
        }
    }
}