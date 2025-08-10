#include <bits/stdc++.h>
using namespace std;

int M, N;
vector<vector<int>> board, dp2;
vector<vector<vector<int>>> dp;

int getMaxCandy(int x, int y, bool skip) {
	if (y == N) return 0;

	int& ret = dp[x][y][skip];
	if (ret != -1) return ret;

	if (skip) {
		ret = max(getMaxCandy(x, y + 1, 0), getMaxCandy(x, y + 1, 1));
	}
	else {
		ret = getMaxCandy(x, y + 1, 1) + board[x][y];
	}

	return ret;
}

int solve(int x, bool skip) {
	if (x == M) return 0;

	int& ret = dp2[x][skip];
	if (ret != -1) return ret;

	if (skip) {
		ret = max(solve(x + 1, 0), solve(x + 1, 1));
	}
	else {
		ret = solve(x + 1, 1) + max(getMaxCandy(x, 0, 0), getMaxCandy(x, 0, 1));
	}

	return ret;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	while (1) {
		cin >> M >> N;
		if (M == 0 && N == 0) break;

		board.assign(M, vector<int>(N, 0));
		for (int x = 0; x < M; x++) {
			for (int y = 0; y < N; y++) {
				cin >> board[x][y];
			}
		}
		
		dp.assign(M + 1, vector<vector<int>> (N+1, vector<int> (2, -1)));
		dp2.assign(M + 1, vector<int>(2, -1));
		cout << max(solve(0, 0), solve(0, 1)) << '\n';
	}

	return 0;
}
