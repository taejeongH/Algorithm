import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int h;
        int cnt;
        Pair(int h, int cnt) {
            this.h = h;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Pair> stk = new Stack<>();
        long res = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            int cnt = 1;

            while (!stk.isEmpty() && stk.peek().h < num) {
                res += stk.peek().cnt;
                stk.pop();
            }

            if (!stk.isEmpty() && stk.peek().h == num) {
                Pair top = stk.pop();
                res += top.cnt;
                cnt += top.cnt;

                if (!stk.isEmpty()) res++;
            } else {
                if (!stk.isEmpty()) res++;
            }

            stk.push(new Pair(num, cnt));
        }

        System.out.println(res);
    }
}