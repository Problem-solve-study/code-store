#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int N, M, S, E, x, y;
vector<int> adj[300'001], vis;

bool inBound(int x) {
	return 1 <= x && x <= N;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M >> S >> E;

	vis.assign(N + 1, -1);
	for (int i = 0; i < M; i++) {
		cin >> x >> y;

		adj[x].push_back(y);
		adj[y].push_back(x);
	}

	queue<int> q;

	vis[S] = 0;
	q.push(S);

	while (!q.empty()) {
		int cur = q.front(); q.pop();

		if (inBound(cur - 1) && (vis[cur - 1] == -1 || vis[cur - 1] > vis[cur] + 1)) {
			vis[cur - 1] = vis[cur] + 1;
			q.push(cur - 1);
		}

		if (inBound(cur + 1) && (vis[cur + 1] == -1 || vis[cur + 1] > vis[cur] + 1)) {
			vis[cur + 1] = vis[cur] + 1;
			q.push(cur + 1);
		}

		for (int& nxt : adj[cur]) {
			if (vis[nxt] == -1) {
				vis[nxt] = vis[cur] + 1;
				q.push(nxt);
			}
		}
	}

	cout << vis[E];

	return 0;
}
