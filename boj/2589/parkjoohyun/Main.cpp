//Memory : 2036kb
//Time : 76ms

#include <iostream>
#include <queue>
#include <memory.h>
#include <vector>
using namespace std;

int width;
int height;
char map[50][50];
int visit[50][50];
vector<pair<int, int>> v;

int dirX[] = { 0,0,1,-1 };
int dirY[] = { 1,-1,0,0 };

inline int bigger(int a, int b) {
	if (a > b)
		return a;
	else
		return b;
}

void input() {
	cin >> height >> width;
	for (int y = 0; y < height; y++) {
		for (int x = 0; x < width; x++) {
			cin >> map[y][x];
		}
	}
}

bool checkPosition(int y, int x) {
	if (y < 0 || y >= height || x < 0 || x >= width) return false;
	return true;
}

int bfs(int y, int x) {
	queue<pair<int, int>> wait;
	wait.push(make_pair(y, x));
	visit[y][x] = 0;
	int far = 0;
	while (!wait.empty()) {
		int py = wait.front().first;
		int px = wait.front().second;
		wait.pop();

		far = bigger(far, visit[py][px]);

		for (int i = 0; i < 4; i++) {
			int dy = py + dirY[i];
			int dx = px + dirX[i];
			if (dy == y && dx == x) continue;
			if (checkPosition(dy, dx) && visit[dy][dx] == 0 && map[dy][dx] == 'L') {
				visit[dy][dx] = visit[py][px] + 1;
				wait.push(make_pair(dy, dx));
			}
		}
	}
	memset(visit, 0, sizeof(visit));
	return far;
}

void solution() {
	int ans = 0;
	for (int y = 0; y < height; y++) {
		for (int x = 0; x < width; x++) {
			int cnt = 0;
			if (map[y][x] == 'W') continue;
			for (int i = 0; i < 4; i++) {
				int dy = y + dirY[i];
				int dx = x + dirX[i];
				if (checkPosition(dy, dx) && visit[dy][dx] == 0 && map[dy][dx] == 'L') {
					cnt++;
				}
			}
			if (cnt != 4) {
				v.push_back(make_pair(y, x));
			}
		}
	}
	int sz = v.size();
	for (int i = 0; i < sz; i++) {
		ans = bigger(ans, bfs(v[i].first, v[i].second));
	}
	cout << ans;
}

int main() {
	input();
	solution();
}