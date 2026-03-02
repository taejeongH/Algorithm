//package BOJ.책정리;
import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> lis = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			int idx = binarySearch(lis, num);
			if (idx == lis.size()) {
				lis.add(num);
			} else {
				lis.set(idx, num);
			}
		}
		
		System.out.println(N-lis.size());
	}
	
	static int binarySearch(ArrayList<Integer> arr, int key) {
		int l = 0;
		int r = arr.size();
		
		while(l < r) {
			int mid = (l+r)/2;
			if (arr.get(mid) <= key) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		
		return r;
	}
	
}