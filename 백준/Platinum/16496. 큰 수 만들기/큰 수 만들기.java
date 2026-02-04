//package BOJ.큰수만들기;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = st.nextToken();
        }

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        if (arr[0].equals("0")) {
            System.out.println("0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String s : arr) sb.append(s);

        System.out.println(sb.toString());
    }
}
