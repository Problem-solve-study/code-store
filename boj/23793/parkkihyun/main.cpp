#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;

#define INF 0x3f3f3f3f3f3f
using pii = pair<long long, long long>;

long long N, M, U, V, W, X, Y, Z;
vector<pii> adj[101010];
vector<long long> vis;

void dijik(long long S) {
	priority_queue<pii, vector<pii>, greater<pii>> pq;

	vis[S] = 0;
	pq.push({ vis[S], S });

	while (!pq.empty()) {
		long long cost, cur;
		tie(cost, cur) = pq.top(); pq.pop();
		
		if (vis[cur] != cost) continue;

		for (auto& path : adj[cur]) {
			long long fee, nxt;
			tie(fee, nxt) = path;

			if (vis[nxt] <= cost + fee) continue;

			vis[nxt] = cost + fee;
			pq.push({ vis[nxt], nxt });
		}
	}
}

void dijik2(long long S) {
	priority_queue<pii, vector<pii>, greater<pii>> pq;

	vis[S] = 0;
	pq.push({ vis[S], S });

	while (!pq.empty()) {
		long long cost, cur;
		tie(cost, cur) = pq.top(); pq.pop();

		if (vis[cur] != cost) continue;

		for (auto& path : adj[cur]) {
			long long fee, nxt;
			tie(fee, nxt) = path;

			if (nxt == Y) continue;
			if (vis[nxt] <= cost + fee) continue;

			vis[nxt] = cost + fee;
			pq.push({ vis[nxt], nxt });
		}
	}
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M;
	for (long long i = 0; i < M; i++) {
		cin >> U >> V >> W;
		adj[U].push_back({ W, V });
	}

	cin >> X >> Y >> Z;

	vis.assign(N + 1, INF);
	dijik(X);

	long long x2y = vis[Y];

	vis.assign(N + 1, INF);
	dijik(Y);

	long long y2z = vis[Z];

	if (x2y == INF || y2z == INF) cout << -1 << ' ';
	else cout << x2y + y2z << ' ';

	vis.assign(N + 1, INF);
	dijik2(X);

	if (vis[Z] == INF) cout << -1;
	else cout << vis[Z];

	return 0;
}
