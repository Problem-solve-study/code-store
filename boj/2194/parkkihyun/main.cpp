#include <iostream>
#include <algorithm>
#include <numeric>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;

int dx[] = { -1, 0, 1, 0 };
int dy[] = { 0, 1, 0, -1 };

// NxM 크기의 맵, AxB 크기의 유닛
int N, M, A, B, K, X, Y, sX, sY, eX, eY;
vector<vector<bool>> board;
vector<vector<int>> vis;

bool inBound(int x, int y) {
	return (1 <= x and x + A - 1 <= N) && (1 <= y && y + B - 1 <= M);
}

bool canGo(int x, int y) {
	for (int i = 0; i < A; i++) {
		for (int j = 0; j < B; j++) {
			if (board[x + i][y + j]) return false;
		}
	}
	return true;
}

int solve() {
	queue<pair<int, int>> q;

	q.push({ sX, sY });
	vis[sX][sY] = 0;

	while (!q.empty()) {
		int x, y;
		tie(x, y) = q.front(); q.pop();

		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (!inBound(nx, ny) || !canGo(nx, ny)) continue;
			if (vis[nx][ny] != -1) continue;

			vis[nx][ny] = vis[x][y] + 1;
			q.push({ nx, ny });
		}
	}

	return vis[eX][eY];
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M >> A >> B >> K;

	board.assign(N+1, vector<bool>(M+1, 0));
	vis.assign(N + 1, vector<int>(M + 1, -1));

	while (K--) {
		cin >> X >> Y;
		board[X][Y] = true;
	}

	cin >> sX >> sY >> eX >> eY;
	
	cout << solve();

	return 0;
}
