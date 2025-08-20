#include <bits/stdc++.h>
using namespace std;

using pii = pair<int, int>;

constexpr int dx[] = { -1, 0, 1, 0 }, dy[] = { 0, 1, 0, -1 };
int N, M, board[1010][1010], spreadTime[1010][1010];
queue<pii> q1, q2;

bool inBound(int x, int y) {
	return 0 <= x && x < N && 0 <= y && y < M;
}

bool spread1(int t) {
	int qSize = q1.size();

	if (qSize == 0) return false;

	for (int i = 0; i < qSize; i++) {
		int cx, cy;
		tie(cx, cy) = q1.front(); q1.pop();

		if (board[cx][cy] == 3) continue;

		for (int dir = 0; dir < 4; dir++) {
			int nx = cx + dx[dir];
			int ny = cy + dy[dir];

			// 범위를 벗어나거나 이미 1이면 패스
			if (!inBound(nx, ny) || board[nx][ny] != 0 || board[nx][ny] == -1) continue;

			spreadTime[nx][ny] = t;
			board[nx][ny] = 1;
			q1.push({ nx, ny });
		}
	}

	return true;
}

bool spread2(int t) {
	int qSize = q2.size();

	if (qSize == 0) return false;

	for (int i = 0; i < qSize; i++) {
		int cx, cy;
		tie(cx, cy) = q2.front(); q2.pop();


		for (int dir = 0; dir < 4; dir++) {
			int nx = cx + dx[dir];
			int ny = cy + dy[dir];

			if (!inBound(nx, ny) || board[nx][ny] == -1) continue;

			if (board[nx][ny] == 0) {
				spreadTime[nx][ny] = t;
				board[nx][ny] = 2;
				q2.push({ nx, ny });
			}
			else if (board[nx][ny] == 1 && spreadTime[nx][ny] == t) {
				board[nx][ny] += 2;
			}
		}
	}

	return true;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M;
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < M; y++) {
			cin >> board[x][y];

			if (board[x][y] == 1) q1.push({ x, y });
			else if (board[x][y] == 2) q2.push({ x, y });
		}
	}

	for (int t = 1; ; t++) {
		bool r1 = spread1(t);
		bool r2 = spread2(t);
		if (r1 == false && r2 == false) break;
	}

	int cnt1 = 0, cnt2 = 0, cnt3 = 0;
	for (int x = 0; x < N; x++) {
		for (int y = 0; y < M; y++) {
			if (board[x][y] == 1) cnt1++;
			else if (board[x][y] == 2) cnt2++;
			else if (board[x][y] == 3) cnt3++;
		}
	}

	cout << cnt1 << ' ' << cnt2 << ' ' << cnt3;

	return 0;
}
