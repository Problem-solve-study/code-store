#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

using namespace std;

/*
S->T Smallest Fuel Case

Fuel 1leter = 10km

Landing Distance = sqrt((x1 - x2)^2 + (y1 - y2)^2)
*/
struct Point {
	int x, y;
};

using ll = long long;

inline ll sqdist(const Point& a, const Point& b) {
	ll dx = 1ll * a.x - b.x;
	ll dy = 1ll * a.y - b.y;
	return dx * dx + dy * dy;
}

bool feasible(const vector<Point>& pts, int c, int k) {
	const int N = (int)pts.size();
	const int limit = 1ll * (10ll* c) * (10ll * c); 

	vector<int> dist(N, -1);
	queue<int> q;
	dist[0] = 0;
	q.push(0);
	while (!q.empty()) {
		int u = q.front();
		q.pop();

		if (dist[u] > k + 1) continue;
		if (u == N - 1) return dist[u] <= k+1;

		for (int v = 0; v < N; ++v) {
			if (dist[v] == -1 && sqdist(pts[u], pts[v]) <= limit) {
				dist[v] = dist[u] + 1;
				q.push(v);
			}
		}
	}
	return false;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	Point start = {0, 0};
	Point target = {10000, 10000};

	int n, k;
	cin >> n >> k;
	vector<Point> points(n+2);
	points[0] = start;
	points[n+1] = target;
	for (int i = 1; i <= n; ++i) {
		cin >> points[i].x >> points[i].y;
	}

	long double dx = (long double)target.x - start.x;
	long double dy = (long double)target.y - start.y;
	long double dist = sqrt(dx * dx + dy * dy);
	int hi = (int)ceill(dist / 10.0);
	int lo = 0;

	while (lo < hi) {
		int mid = (lo + hi) / 2;        // 연료탱크 용량(ℓ)
		if (feasible(points, mid, k)) hi = mid;
		else lo = mid + 1;
	}

	cout << lo << '\n';
}