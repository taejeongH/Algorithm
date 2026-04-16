#include <iostream>
using namespace std;

int main() {
	int INF = 100000000;
	int tc;

	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		int p, n;
		string person;
		cin >> p;


		int max = 0;
		string res;
		for (int i = 0; i < p; i++) {
			cin >> n >> person;
			if (n > max) {
				max = n;
				res = person;
			}
		}

		cout << res << '\n';
	}
}

