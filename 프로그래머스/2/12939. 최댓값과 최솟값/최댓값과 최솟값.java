class Solution {
    public String solution(String s) {
        String answer = "";
        String[] numbers = s.split(" ");
        
        int min = Integer.MAX_VALUE;
        int max = -Integer.MAX_VALUE;
        for (int i=0; i<numbers.length; i++) {
            int num = Integer.parseInt(numbers[i]);
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        return min + " " + max;
    }
}