#include <iostream>
#include <unordered_map>
#include <vector>
#include <queue>
using namespace std;

#define INF 1e18
#define X first
#define Y second
using pii = pair<long long, long long>;

pii points[10];
unordered_map<long long, long long> tp;
vector<pii> adj[10];

long long calc(pii p1, pii p2) {
	return abs(p1.X - p2.X) + abs(p1.Y - p2.Y);
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> points[0].X >> points[0].Y;
	cin >> points[7].X >> points[7].Y;

	for (long long i = 0; i < 3; i++) {
		cin >> points[i << 1 | 1].X >> points[i << 1 | 1].Y;
		cin >> points[(i + 1) << 1].X >> points[(i + 1) << 1].Y;

		tp[i << 1 | 1] = (i + 1) << 1;
		tp[(i + 1) << 1] = i << 1 | 1;
	}

	for (long long i = 0; i < 8; i++) {
		pii p1 = points[i];

		for (long long j = 0; j < 8; j++) {
			if (i == j) continue;
			pii p2 = points[j];

			// 바로 가는 경우를 추가
			long long dist = calc(p1, p2);

			adj[i].push_back({ j, dist });

			if (tp.find(i) != tp.end()) {
				adj[i].push_back({ tp[i], 10});
			}
		}
	}

	vector<long long> dist(10, INF);
	priority_queue<pii, vector<pii>, greater<pii>> pq;

	dist[0] = 0;
	pq.push({ dist[0], 0 });

	while (!pq.empty()) {
		long long cost, cur;
		tie(cost, cur) = pq.top(); pq.pop();

		if (dist[cur] != cost) continue;

		for (pii& path : adj[cur]) {
			long long nxt, fee;
			tie(nxt, fee) = path;

			if (dist[nxt] <= cost + fee) continue;

			dist[nxt] = cost + fee;
			pq.push({ dist[nxt], nxt });
		}
	}

	cout << dist[7];

	return 0;
}
