#include <iostream>
using namespace std;
using ll = long long;

int N, M, K;		// 농장의 층수, 비가 오는 횟수, 버틸 수 있는 빗물의 양

ll solve() {
	ll sum = 0;

	for (int i = 1; i <= M; i++) {
		ll t, r; cin >> t >> r;
		if (sum + r > K) {
			return i;
		}
		sum += r;
	}

	return -1;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M >> K;

	ll ans = solve();

	if (ans == -1) {
		cout << -1;
	}
	else {
		cout << ans << ' ' << 1;
	}

	return 0;
}
