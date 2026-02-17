//package BOJ.휴대폰자판;

import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		HashMap<Character, Node> nxt;
		boolean endOfWords;
		public Node() {
			nxt = new HashMap<>();
		}
	}
	
	static void add(Node cur, String word, int idx) {
		if(word.length() == idx) {
			cur.endOfWords = true;
			return;
		}
		char c = word.charAt(idx);
		if (!cur.nxt.containsKey(c)) {
			cur.nxt.put(c, new Node());
		}
		add(cur.nxt.get(c), word, idx+1);
	}
	
	static int find(Node cur, String word, int idx) {
		if(word.length() == idx) {
			return 0;
		}
		
		char c = word.charAt(idx);
		int res = 0;
		if (idx == 0 || cur.nxt.size()>1 || cur.endOfWords) {
			res++;
		}
		res += find(cur.nxt.get(c), word, idx+1);
		return res;
	}
	
	static Node head;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input;
		while(true) {
			input = br.readLine();
			if (input == null || input.isEmpty()) break;
			int N = Integer.parseInt(input);
			head = new Node();
			String[] map = new String[N];
			
			for (int i=0; i<N; i++) {
				map[i] = br.readLine();
				add(head, map[i], 0);
			}
			
			int sum = 0;
			for (int i=0; i<N; i++) {
				sum += find(head, map[i], 0);
			}
			double avg = (double)sum/N;
			sb.append(String.format("%.2f", avg)).append("\n");
		}
		System.out.print(sb);
	}
}
