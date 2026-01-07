//package BOJ.상어중학교;

import java.io.*;
import java.util.*;

public class Main {
	static class Group implements Comparable<Group>{
		int[] standardBlock;
		int rainbowCount;
		int count;
		
		public Group(int[] standardBlock, int rainbowCount, int count) {
			super();
			this.standardBlock = standardBlock;
			this.rainbowCount = rainbowCount;
			this.count = count;
		}

		@Override
		public int compareTo(Main.Group o) {
			if (o.count == this.count) {
				if(o.rainbowCount==this.rainbowCount) {
					if(o.standardBlock[0]==this.standardBlock[0]) return o.standardBlock[1] - this.standardBlock[1];
					return o.standardBlock[0] - this.standardBlock[0];
				}
				return o.rainbowCount - this.rainbowCount;
			}
			return o.count - this.count;
		}

		@Override
		public String toString() {
			return "Group [standardBlock=" + Arrays.toString(standardBlock) + ", rainbowCount=" + rainbowCount
					+ ", count=" + count + "]";
		}
		
	}
	static final int EMPTY = -10;
	static int N, M;
	static int[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //맵 크기
		M = Integer.parseInt(st.nextToken()); //색상 개수
		
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int res = 0;
		while(true) {
			Group big = findBigGroup();
//			System.out.println(big);
			if (big == null) break;
			
			//그룹 돌면서 지우기
			int count = remove(big);
			res += count*count;
			
//			for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
//			System.out.println();
			
			//중력 작용
			gravity();
			
			//90도 회전
			turn();
			
			//중력 작용
			gravity();
			
//			for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
//			System.out.println();
		}
		System.out.println(res);
	}
	public static void turn() {
		int[][] tmp = new int[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				tmp[j][i] = map[i][N-j-1];
			}
		}
		map = tmp;
	}
	
	
	public static void gravity() {
		for (int j=0; j<N; j++) {
			for (int i=N-1; i>=0; i--) {
				if (map[i][j]!= -1 && map[i][j]!=EMPTY) {
					int cur = i;
					while(true) {
						int nxt = cur+1;
						if(nxt==N || map[nxt][j]!=EMPTY) {
							break;
						}
						
						map[nxt][j] = map[cur][j];
						map[cur][j] = EMPTY;
						
						cur = nxt;
					}
				}
			}
		}
	}
	
	public static int remove(Group g) {
		int startY = g.standardBlock[0];
		int startX = g.standardBlock[1];
		
		int color = map[startY][startX];
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {startY, startX});
		map[startY][startX] = EMPTY;
		int count = 1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx] == EMPTY) continue;
				if(map[ny][nx]==color || map[ny][nx] == 0) {
					map[ny][nx] = EMPTY;
					que.add(new int[] {ny, nx});
					count++;
				}
			}
		}
		return count;
	}
	
	public static Group findBigGroup() {
		boolean[][] v = new boolean[N][N];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		PriorityQueue<Group> pq = new PriorityQueue<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] < 1 || v[i][j] || map[i][j]==EMPTY) continue;
				int color = map[i][j];
				int count = 1;
				int rainbowCount = 0;
				int[] standardBlock = new int[] {i, j};
				v[i][j] = true;
				que.add(new int[] {i, j});
				ArrayDeque<int[]> rainBowPos = new ArrayDeque<>();
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					
					for (int k=0; k<4; k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						
						if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx] == -1 || map[ny][nx] == EMPTY) continue;
						if(map[ny][nx]!=0 && map[ny][nx] != color) continue;
						if(v[ny][nx]) continue;
						v[ny][nx] = true;
						
						rainbowCount += map[ny][nx]==0?1:0;
						count++;
						que.add(new int[] {ny, nx});
						
						if(map[ny][nx]==0) {
							rainBowPos.add(new int[] {ny, nx});
							continue;
						}
						
						if(standardBlock[0] > ny) {
							standardBlock[0] = ny;
							standardBlock[1] = nx;
						} else if(standardBlock[0] == ny) {
							if (standardBlock[1] > nx) {
								standardBlock[1] = nx;
							}
						}
						
					}
				}
				while(!rainBowPos.isEmpty()) {
					int[] now = rainBowPos.poll();
					int y = now[0];
					int x = now[1];
					v[y][x] = false;
				}
				
				if(count>=2) {
					pq.add(new Group(standardBlock, rainbowCount, count));
				}
			}
		}
		
		if(pq.isEmpty()) return null;
		
		return pq.peek();
	}
	
	
}
/*
	검은색 블록 -1
	무지개 블록 0
	일반 블록 M가지 색상으로 표현
	
	그룹
	 - 일반 블록 최소 하나
	 - 일반 블록의 색은 모두 같아야 함
	 - 검은색 블록 포함 x
	 - 블록의 개수는 2이상
	 - 임의의 한 블록에서 다른 모든 칸을 이동할 수 있어야 함
	 - 기준 블록 = 무지개 블록이 아닌 블록 중 행번호가 가장 작은 블록(여러 개면 열의 번호가 가장 작은 블록)
	
	오토 플레이 기능
	1. 크기가 가장 큰 블록 그룹 찾기
      우선순위 
       무지개 블록의 수가 많은 블록 그룹
       기준 블록의 행이 가장 큰 그룹
       기준 블록의 열이 가장 큰 그룹
    2. 1에서 찾은 블록 그룹의 모든 블록을 제거
    - 블록의수의 제곱만큼 점수를 획득
    3. 격자에 중력 작용
    4. 격자가 90도 반시계 방향으로 회전
    5. 다시 격자에 중력 작용 
    
    중력 작용 시 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동
    
*/