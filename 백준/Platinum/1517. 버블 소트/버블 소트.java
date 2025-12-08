//package BOJ.버블소트;

import java.io.*;
import java.util.*;

public class Main {
	static int[] arr, tmp;
	static long count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		tmp = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		mergeSort(0, N-1);
		
		System.out.println(count);
	}
	static void mergeSort(int start, int end) {
		if (start >= end) return;
		
		int mid = (start+end)/2;
		mergeSort(start, mid);
		mergeSort(mid+1, end);
		merge(start, mid, end);
	}
	
	static void merge(int start, int mid, int end) {
		int i = start;
		int j = mid+1;
		int idx = start;
		
		while(i <= mid && j <= end) {
			if (arr[i] <= arr[j]) {
				tmp[idx++] = arr[i++];
			} else {
				tmp[idx++] = arr[j++];
				count += (mid - i + 1);
			}
		}
		
		while(i<=mid) {
			tmp[idx++] = arr[i++];
		}
		
		while(j<=end) {
			tmp[idx++] = arr[j++];
		}
		
		for (int k=start; k<=end; k++) {
			arr[k] = tmp[k];
		}
	}

}
