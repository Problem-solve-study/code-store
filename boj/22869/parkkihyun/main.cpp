#include <iostream>
#include <algorithm>
using namespace std;

int N, K;
int A[5050], vis[5050];

int calc(int i, int j) {
	return (j - i) * (1 + abs(A[i] - A[j]));
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> K;
	for (int i = 1; i <= N; i++) cin >> A[i];

	vis[1] = true;
	for (int i = 1; i < N; i++) {
		if (!vis[i]) continue;

		for (int j = i + 1; j <= N; j++) {
			if (calc(i, j) <= K) {
				vis[j] = true;
			}
		}
	}

	if (vis[N]) cout << "YES";
	else cout << "NO";

	return 0;
}
