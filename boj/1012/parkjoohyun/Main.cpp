//Memory 2036KB
//Time 0ms

#include <iostream>
#include <cmath>
#include <algorithm>
#include <queue>
#include <string.h>

using namespace std;
int warm = 0;
int field[50][50];
bool visit[50][50];
int K, M, N, T, x, y;
int posx[4] = { 0,0,-1,1 };
int posy[4] = { -1,1,0,0 };

void empty() {
	memset(field, 0, sizeof(field));
	memset(visit, false, sizeof(visit));
	warm = 0;
}

void bfs(int x, int y) {
	queue<pair<int, int>> bfs_queue;
	bfs_queue.push(make_pair(x, y));
	while (!bfs_queue.empty()) {
		int x = bfs_queue.front().first;
		int y = bfs_queue.front().second;

		bfs_queue.pop();

		for (int i = 0; i < 4; i++) {
			int n_x = x + posx[i];
			int n_y = y + posy[i];

			if (n_x < M && n_y < N && n_x >= 0 && n_y >= 0) {
				if (field[n_x][n_y] == 1 && !visit[n_x][n_y]) {
					bfs_queue.push(make_pair(n_x, n_y));
					visit[n_x][n_y] = true;
				}
			}
		}
	}
}

int main() {
	
	cin >> T;
	while (T--) {
		empty();
		cin >> M >> N >> K;
		queue<pair<int, int>> pos;
		//배추 위치 입력
		for (int i = 0; i < K; i++) {
			cin >> x >> y;
			field[x][y] = 1;
			pos.push(make_pair(x, y));
		}
		//BFS
		while (!pos.empty()) {
			int posx = pos.front().first;
			int posy = pos.front().second;
			pos.pop();
			if (!visit[posx][posy]) {
				warm++;
				visit[posx][posy] = true;
				bfs(posx, posy);
			}
		}
		cout << warm << endl;
	}
	
}