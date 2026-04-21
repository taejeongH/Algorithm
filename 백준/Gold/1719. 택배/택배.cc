#include <iostream>
#define MAX 1000000000

using namespace std;

int main() {
	int N, M, g[201][201], p[201][201] = { 0 };

	cin >> N >> M;

	for (int i = 1; i <= 200; i++) {
		for (int j = 1; j <= 200; j++) {
			g[i][j] = MAX;
		}
	}
	
	for (int i = 0; i < M; i++) {
		int s, e, c;
		cin >> s >> e >> c;

		if (g[s][e] > c) {
			g[s][e] = c;
			g[e][s] = c;

			p[s][e] = e;
			p[e][s] = s;
		}
	}


	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (g[i][k] == MAX || g[k][j] == MAX) continue;

				if (g[i][j] > g[i][k] + g[k][j]) {
					g[i][j] = g[i][k] + g[k][j];
					p[i][j] = k;
				}
				
			}
		}
	}

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (i == j) continue;

			int cur = p[i][j];

			while (cur != p[i][cur]) {
				cur = p[i][cur];
			}

			p[i][j] = cur;
		}
	}




	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (i == j) {
				cout << '-';
			}
			else {
				cout << p[i][j];
			}
			cout << ' ';
		}
		cout << '\n';
	}
	return 0;
}