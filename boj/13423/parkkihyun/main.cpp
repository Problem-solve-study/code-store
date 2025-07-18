#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
vector<int> X;

bool binarySearch(int target) {
	int l = 0, r = X.size() - 1, c;

	while (l <= r) {
		c = (l + r) >> 1;

		if (X[c] == target) return true;
		else if (X[c] < target) l = c + 1;
		else r = c - 1;
	}

	return false;
}

int solve() {
	int ans = 0;

	cin >> N;

	X.assign(N, 0);
	for (int i = 0; i < N; i++) cin >> X[i];
	sort(X.begin(), X.end());

	for (int i = 0; i < N - 1; i++) {
		for (int j = i + 1; j < N; j++) {
			int xb = (X[i] + X[j]) >> 1;

			if (xb == X[i] || xb == X[j]) continue;

			if (binary_search(X.begin(), X.end(), xb)) {
				if (abs(xb - X[i]) == abs(X[j] - xb)) ans++;
			}
		}
	}

	return ans;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int T; cin >> T;
	while (T--) {
		cout << solve() << '\n';
	}

	return 0;
}
