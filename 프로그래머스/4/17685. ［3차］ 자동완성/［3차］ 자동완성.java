class Solution {
    class Trie{
        class Node{
            int[] cnt;
            Node[] words;
            boolean endsOfWord;
            
            public Node() {
                words = new Node[26];
                cnt = new int[26];
            }
        }
        
        Node head;
        
        public Trie() {
            head = new Node();
        }
        
        void insert(Node cur, String s, int idx) {
            if (idx >= s.length()) return;
            
            int c = s.charAt(idx) - 'a';
            if (cur.words[c] == null) {
                cur.words[c] = new Node();
            }
            cur.cnt[c]++;
            
            if (idx == s.length()-1) {
                cur.endsOfWord = true;  
            } 
            
            insert(cur.words[c], s, idx+1);
        }
        
        int find(Node cur, String s, int idx) {
            if (s.length()==idx) return idx;
            
            int c = s.charAt(idx) - 'a';
            if (cur.cnt[c] == 1) return idx+1;
            
            
            return find(cur.words[c], s, idx+1);
        }
        
    }    

    public int solution(String[] words) {
        int answer = 0;
        
        Trie t = new Trie();
        
        for (int i=0; i<words.length; i++) {
            t.insert(t.head, words[i], 0);
        }
        
        for (int i=0; i<words.length; i++) {
            answer += t.find(t.head, words[i], 0);
        }
        return answer;
    }
}