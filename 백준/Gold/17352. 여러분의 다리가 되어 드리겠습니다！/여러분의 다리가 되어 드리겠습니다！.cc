#include<iostream>
#define MAX 300000

using namespace std;

int N, parent[MAX+1];

int find(int a) {
	if (parent[a] == a) return a;
	int aRoot = find(parent[a]);
	return parent[a] = aRoot;
}

void uni(int a, int b) {
	if (parent[a] == parent[b]) return;

	int aRoot = find(a);
	int bRoot = find(b);

	parent[aRoot] = bRoot;
}

int main(int argc, char** argv)
{
	cin >> N;
	for (int i = 1; i <= N; i++) {
		parent[i] = i;
	}

	for (int i = 0; i < N - 2; i++) {
		int s, e;
		cin >> s >> e;
		uni(s, e);
	}

	for (int i = 1; i <= N; i++) {
		if (parent[i] == i) {
			cout << i << ' ';
		}
	} 
}