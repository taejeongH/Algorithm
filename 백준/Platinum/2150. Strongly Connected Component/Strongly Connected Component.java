//package BOJ.StronglyConnectedComponent;

import java.io.*;
import java.util.*;

public class Main {
	static int V, E, order;
	static List<Integer>[] g;
	static int[] visit;
	static boolean[] finished;
	static Stack<Integer> stk;
	static List<List<Integer>> sccList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		g = new List[V+1]; for(int i=1; i<V+1; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
		}
		
		order = 1;
		visit = new int[V+1];
		finished = new boolean[V+1];
		stk = new Stack<>();
		sccList = new ArrayList<>();
		
		for (int i=1; i<V+1; i++) {
			if(visit[i]==0) dfs(i);
		}
		
        for (List<Integer> scc : sccList)
            Collections.sort(scc);
        Collections.sort(sccList, (o1, o2) -> o1.get(0) - o2.get(0));

        StringBuilder sb = new StringBuilder();
        sb.append(sccList.size()).append("\n");
        for (List<Integer> scc : sccList) {
            for (int v : scc) sb.append(v).append(" ");
            sb.append(-1).append("\n");
        }
        System.out.println(sb);
	}
	
	public static int dfs(int cur) {
		visit[cur] = order++;
		int parent = visit[cur];
		stk.push(cur);
		
		for (int nxt : g[cur]) {
			if(visit[nxt]==0) {
				parent = Math.min(parent, dfs(nxt));
			} else if(!finished[nxt]) {
				parent = Math.min(parent, visit[nxt]);
			}
		}
		
		if(parent == visit[cur]) {
			List<Integer> scc = new ArrayList<>();
			while(true) {
				int t = stk.pop();
				finished[t] = true;
				scc.add(t);
				if(t==cur) break;
			}
			sccList.add(scc);
		}
		return parent;
	}
}


/*
	방향 그래프에셔 순환 그래프를 찾기
	
	
	int visited 써서 방문 2회 이하라면 가능? 
	만약 자기 자신으로 돌아오면 depth 높은 것을 정답으로..
*/