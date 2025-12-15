//package BOJ.이진검색트리;

import java.io.*;
import java.util.*;

public class Main {
	public static class Tree{
		static class Node{
			int value;
			Node right;
			Node left;
			public Node(int value) {
				this.value = value;
			}
		}
		
		static Node root;
		
		static void add(int value) {
			if (root == null) {
				root = new Node(value);
				return;
			}
			
			Node newNode = new Node(value);
			
			Node cur = root;
			while(true) {
				if (cur.value > newNode.value) {
					if (cur.left == null) break;
					cur = cur.left;
				} else {
					if (cur.right == null) break;
					cur = cur.right;
				}
			}
			if (cur.value > newNode.value) {
				cur.left = newNode;
			} else {
				cur.right = newNode;
			}
		}
		
		static void postOrder(Node cur) {
			if (cur == null) return;
			postOrder(cur.left);
			postOrder(cur.right);
			System.out.println(cur.value);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		Tree t = new Tree();
		while((input = br.readLine()) != null){
			if (input.isEmpty()) break;
			int num = Integer.parseInt(input);
			t.add(num);
		}
		
		t.postOrder(t.root);
	}
	
}
