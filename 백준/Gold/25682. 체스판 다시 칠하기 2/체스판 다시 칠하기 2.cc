#include <iostream>
#include <algorithm>
#define MAX 2001
#define INF 1000000000

using namespace std;

int N, M, K;
int sumB[MAX][MAX];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> M >> K;

    char c;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            cin >> c;

            char expected = ((i + j) % 2 == 0) ? 'B' : 'W';

            int diff = (c != expected);

            sumB[i][j] = sumB[i - 1][j]
                + sumB[i][j - 1]
                - sumB[i - 1][j - 1]
                + diff;
        }
    }

    int res = INF;

    for (int i = K; i <= N; i++) {
        for (int j = K; j <= M; j++) {
            int cntB = sumB[i][j]
                - sumB[i - K][j]
                - sumB[i][j - K]
                + sumB[i - K][j - K];

            int cntW = K * K - cntB;

            res = min(res, min(cntB, cntW));
        }
    }

    cout << res << '\n';

    return 0;
}