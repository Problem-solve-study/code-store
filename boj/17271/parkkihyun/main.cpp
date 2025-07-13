#include <iostream>
#include <vector>
using namespace std;

constexpr int MOD = 1'000'000'007;
int N, M;
vector<vector<int>> dp;

int C(int n, int r) {
	if (n == r) return 1;
	if (r == 0) return 1;
	if (r == 1) return n;

	int& ret = dp[n][r];
	if (ret != -1) return ret % MOD;

	return ret = (C(n - 1, r - 1) % MOD + C(n - 1, r) % MOD) % MOD;
}

int H(int n, int r) {
	return C(n + r - 1, r) % MOD;
}

int getComb(int bCnt) {
	// b 스킬을 bCnt번 쓰면, 그만큼 A 스킬은 M * bCnt만큼 줄어듬
	// 그렇다면 A 스킬의 빈칸에 B를 bCnt만큼 분배해야함
	// 중복 조합으로 풀이

	int ret = 0;

	int aCnt = N - M * bCnt;

	return H(aCnt + 1, bCnt) % MOD;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M;

	dp.assign(N + 1, vector<int>(N / M + 1, -1));

	long long ans = 0;
	// B 스킬을 시전할 수 있는 횟수는 N초를 M초로 나눈것만큼 가능함
	for (int i = 0; i <= N / M; i++) {
		// B스킬을 i번 쓰고 가능한 경우의 수를 구함
		ans += getComb(i) % MOD;
	}

	cout << ans % MOD;

	return 0;
}
