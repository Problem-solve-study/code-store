// 우상으로만 이동 가능하다.
// 즉, 어느 한 점에서 오른쪽 위에서 가져올 수 있는 사탕의 최적해는 동일하다.
// 좌표에서 가져오면 된다.
// 사탕의 개수는 어쨌든 (0, 0)에서 시작하므로 좌표의 거리가 사탕의 개수다.

#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>
#include <tuple>
using namespace std;

#define X first
#define Y second

using Point = pair<int, int>;		// x, y

int N, M;
Point points[305];
vector<vector<int>> dp;

int getDistance(Point& p) {
	return p.X + p.Y;
}

int getCandy(Point& p) {
	return max(0, M - getDistance(p));
}
int solve(Point& cur) {
	int& ret = dp[cur.X][cur.Y];
	if (ret != -1) return ret;


	ret = 0;
	for (int i = 0; i < N; i++) {
		Point& nxt = points[i];
		if (cur != nxt && (cur.X <= nxt.X && cur.Y <= nxt.Y)) {
			int tmp = solve(nxt);
			ret = max(ret, tmp);
		}
	}

	ret += getCandy(cur);

	return ret;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> points[i].first >> points[i].second;
	}

	dp.assign(305, vector<int>(305, -1));

	Point origin(0, 0);
	int ans = solve(origin) - getCandy(origin);

	cout << ans;

	return 0;
}
