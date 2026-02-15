//package BOJ.전설;

import java.io.*;
import java.util.*;



public class Main {
	static class Trie{
		Node head;
		
		public Trie() {
			head = new Node();
		}
		
		class Node{
			Node[] child;
			boolean endOfWord;
			
			public Node() {
				child = new Node[26];
				endOfWord = false;
			}
		}
		
		void add(Node cur, String s, int idx) {
			if (idx == s.length()) {
				cur.endOfWord = true;
				return;
			}
			int c = s.charAt(idx) - 'a';
			if(cur.child[c] == null) cur.child[c] = new Node();
			add(cur.child[c], s, idx+1);
		}
		
		boolean isContain(Node cur, String s, int idx) {
			if (s.length() == idx) {
				return false;
			}
			
			if (cur.endOfWord) {
				if (names.contains(s.substring(idx))) return true;
			}
			
			int c = s.charAt(idx) - 'a';
			
			if(cur.child[c] == null) return false;
			
			return isContain(cur.child[c], s, idx+1);
		}
	}
	static HashSet<String> names;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Trie t = new Trie();
		
		for (int i=0; i<N; i++) {
			String color = br.readLine();
			t.add(t.head, color, 0);
		}
		
		names = new HashSet<>();
		for (int i=0; i<M; i++) {
			String name = br.readLine();
			names.add(name);
		}
		
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<Q; i++) {
			String s = br.readLine();
			if(t.isContain(t.head, s, 0)) sb.append("Yes");
			else sb.append("No");
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
