#include <iostream>
using namespace std;

bool isPow(int n) {
	for (int i = 1; i * i <= n; ++i) {
		if (i * i == n) return true;
	}
	return false;
}

int main() {
	int INF = 100000000;
	int N, M;
	cin >> M >> N;

	int sum = 0;
	int min = INF;
	for (int i = M; i <= N; i++) {
		if (isPow(i)) {
			sum += i;
			if (min > i) {
				min = i;
			}
		}
	}

	if (min == INF) cout << -1;
	else cout << sum << '\n' << min;

	return 0;
}

