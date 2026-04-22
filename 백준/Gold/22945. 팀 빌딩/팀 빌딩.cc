#include <iostream>
#define MAX 1000000000

using namespace std;

int main() {
	int N, arr[100000];

	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int l = 0, r = N - 1, res = 0;

	while (l < r) {
		int min = arr[l];
		if (min > arr[r]) min = arr[r];
		int cur = (r - l- 1) * min;
		if (cur > res) res = cur;

		if (arr[l] < arr[r]) {
			l++;
		}
		else {
			r--;
		}
	}
	cout << res;
}