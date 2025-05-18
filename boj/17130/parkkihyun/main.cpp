#include <bits/stdc++.h>
using namespace std;

int dx[] = { 0, 1, -1 };
int dy[] = { -1, -1, -1 };

int N, M;
vector<vector<char>> board;
vector<vector<int>> dp;

bool inBound(int x, int y) {
	return 0 <= x && x < N and 0 <= y && y < M;
}

int getCnt(int x, int y) {
	if (!inBound(x, y) || board[x][y] == '#') return -1;
	if (board[x][y] == 'R') return 0;

	int& ret = dp[x][y];
	if (ret != -2) return ret;

	for (int dir = 0; dir < 3; dir++) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		ret = max(ret, getCnt(nx, ny));
	}

	if (ret != -1 && board[x][y] == 'C') ret++;

	return ret;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M;

	int rX, rY;
	board.resize(N, vector<char>(M));
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < M; y++) {
			cin >> board[x][y];

			if (board[x][y] == 'R') {
				rX = x;
				rY = y;
			}
		}
	}

	dp.resize(N, vector<int>(M, -2));

	int ans = -1;
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < M; y++) {
			if (board[x][y] == 'O') {
				int cnt = getCnt(x, y);
				ans = max(ans, cnt);
			}
		}
	}

	cout << ans;

	return 0;
}
