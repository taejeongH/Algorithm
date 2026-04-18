#include <iostream>
using namespace std;

int can(int map[], int N, int M, int max) {

	int sum = 0;

	int remain = M;
	int res = 0;
	for (int i = 0; i < N; i++) {
		if (sum + map[i] <= max) {
			sum += map[i];
		}
		else {
			if (--remain <= 0) return -1;
			if (res < sum) res = sum;
			sum = map[i];
		}
	}
	if (res < sum) res = sum;
	return res;
}


int main() {
	
	int N, M;

	cin >> N >> M;

	int map[100000];
	int sum = 0;
	for (int i = 0; i < N; i++) {
		cin >> map[i];

		sum += map[i];
	}
	int l = 1;
	int r = sum;
	
	int res = 0;
	while (l <= r) {
		int mid = (l + r) / 2;
		
		int now = can(map, N, M, mid);
		if (now == -1) {
			l = mid + 1;
		}
		else {
			res = now;
			r = mid-1;
		}

	}

	cout << res;
	return 0;
}