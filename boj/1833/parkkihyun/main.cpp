#include <bits/stdc++.h>
using namespace std;

int N;
vector<vector<int>> board;
vector<tuple<int, int, int>> edges;

vector<int> p;

int find(int x) {
	if (p[x] < 0) return x;
	return p[x] = find(p[x]);
}

bool uni(int u, int v) {
	u = find(u);
	v = find(v);

	if (u == v) return false;
	if (p[u] == p[v]) p[u]--;
	if (p[v] < p[u]) p[u] = v;
	else p[v] = u;
	return true;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N;

	p.assign(N + 1, -1);
	board.assign(N + 1, vector<int>(N + 1, 0));


	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> board[i][j];

			if (i <= j) continue;
			edges.push_back({ board[i][j], i, j });
		}
	}

	sort(edges.begin(), edges.end());

	int C = 0, M = 0;
	vector<pair<int, int>> ans;

	for (auto& edge : edges) {
		int cost, u, v;
		tie(cost, u, v) = edge;

		// 기존에 건설된 고속철도
		if (cost < 0) {
			C += -cost;
			uni(u, v);
		}
		else {
			if (uni(u, v)) {
				C += cost;
				ans.push_back({ u, v });
			}
		}
	}

	cout << C << ' ' << ans.size() << '\n';
	for (auto& p : ans) {
		cout << p.first << ' ' << p.second << '\n';
	}

	return 0;
}
