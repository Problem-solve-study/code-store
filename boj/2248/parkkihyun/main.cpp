#include <iostream>
#include <algorithm>
#include <numeric>
#include <vector>
#include <queue>
#include <tuple>
#include <unordered_map>
using namespace std;

using ll = long long;

ll N, L, I;
vector<vector<ll>> dp;

// 조합
ll comb(ll n, ll r) {
	if (n < r) return 0;
	if (n == r) return 1;
	if (r == 1) return n;
	if (r == 0) return 1;

	ll& ret = dp[n][r];
	if (ret != -1) return ret;

	return ret = comb(n - 1, r) + comb(n - 1, r - 1);
}

// n: 현재 남아있는 자리 수
// l: 남은 1의 개수
void solve(ll n, ll l) {
	if (l == 0 || I == 1) {
		while (n--) {
			cout << '0';
		}
		return;
	}

	ll sum = 0;
	for (ll i = 0; i <= l; i++) {
		sum += comb(n - 1, i);
	}

	if (sum >= I) {
		cout << '0';
		solve(n - 1, l);
	}
	else {
		cout << '1';
		I -= sum;
		solve(n - 1, l - 1);
	}
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> L >> I;

	dp.assign(32, vector<ll>(32, -1));

	solve(N, L);

	return 0;
}
