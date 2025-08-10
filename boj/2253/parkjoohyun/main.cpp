#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	int n, m;

	cin >> n >> m;
	vector<bool> stones(n+1, true);
	for(int i=0; i < m; ++i) {
		int x;
		cin >> x;
		stones[x] = false; // Mark the stone as removed
	}

	int jMax = (int)floor(sqrt(2 * n)) + 2;
	vector<vector<int>>dp(n+1, vector<int>(jMax+2, 1e9)); // stone , jump
	if (!stones[2]) {                 // 2번이 금지면 불가능
		cout << -1 << '\n';
		return 0;
	}
	dp[2][1] = 1;
	for (int i = 3; i <= n; i++) {
		if (!stones[i]) continue;

		for (int j = 1; j <= jMax; j++) {
			int prev = i - j;
			if (prev < 1) break;
			int best = 1e9;

			if(j-1 >= 1) best = min(best, dp[prev][j-1]); //가속
			best = min(best, dp[prev][j]); //유지
			if(j+1 <= jMax) best = min(best, dp[prev][j+1]); //감속

			if(best != 1e9) {
				dp[i][j] = min(dp[i][j], best+1);
			}
		}
	}
	int res = 1e9;
	for (int k = 1; k <= jMax; k++) {
		res = min(res, dp[n][k]);
	}

	cout << (res == 1e9 ? -1 : res) << "\n";
}