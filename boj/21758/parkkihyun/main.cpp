#include <bits/stdc++.h>
using namespace std;

int N;
vector<int> honey, presum;

// 1 indexed
int get(int l, int r) {
	return presum[r] - presum[l - 1];
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N;

	honey.assign(N + 1, 0), presum.assign(N + 1, 0);
	for (int i = 1; i <= N; i++) {
		cin >> honey[i];
		presum[i] = presum[i - 1] + honey[i];
	}

	int ans = 0;

	// 벌통이 맨 왼쪽에 있는 경우
	for (int i = 2; i < N; i++) {
		int tmp = get(1, N - 1) + get(1, i - 1) - honey[i];
		ans = max(ans, tmp);
	}

	// 벌통이 맨 오른쪽에 있는 경우
	for (int i = 2; i < N; i++) {
		int tmp = get(2, N) + get(i + 1, N) - honey[i];
		ans = max(ans, tmp);
	}

	// 벌이 양쪽에 있는 경우
	for (int i = 2; i < N; i++) {
		int tmp = get(2, i) + get(i, N - 1);
		ans = max(ans, tmp);
	}

	cout << ans;

	return 0;
}
