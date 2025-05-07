// 바텀업 DP로 풀고 싶었으나 능력의 한계로 탑다운으로 해결
// 자두는 현재 위치가 1이므로 2로 가기 위해선 무조건 한 번 움직여야함
// 즉, w가 홀수일때만 2가 가능함
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int T, W;
vector<int> zadu;
vector<vector<int>> dp;

int solve(int t, int w) {
	if (t > T) return 0;
	if (w > W) return 0;

	if (dp[t][w] == -1) {
		// 현재 칸에서 자두를 먹음
		int cnt = 0;
		if (w % 2 == 0) cnt += (zadu[t] == 1);
		else cnt += (zadu[t] == 2);

		cnt += max(solve(t + 1, w + 1), solve(t + 1, w));

		dp[t][w] = cnt;
	}

	return dp[t][w];
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> T >> W;
	zadu.assign(T + 1, 0);
	dp.assign(T + 1, vector<int>(W + 1, -1));

	for (int i = 1; i <= T; i++) cin >> zadu[i];

	cout << solve(0, 0);

	return 0;
}
