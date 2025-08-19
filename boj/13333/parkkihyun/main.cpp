#include <bits/stdc++.h>
using namespace std;

int N;
vector<int> arr;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N;

	arr.assign(N, 0);
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	sort(arr.begin(), arr.end());

	int ans = 0;
	for (int k = 0; k <= 10'000; k++) {
		int idx = lower_bound(arr.begin(), arr.end(), k) - arr.begin();

		if (N - k >= idx) {
			ans = max(ans, k);
		}
	}

	cout << ans;

	return 0;
}
