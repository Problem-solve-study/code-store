#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, times[10101], inD[10101], dist[10101];
vector<int> adj[10101];

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> times[i];
		int t; cin >> t;
		for (int j = 0; j < t; j++) {
			int prv; cin >> prv;
			inD[i]++;
			adj[prv].push_back(i);
		}
	}

	queue<int> q;

	for (int i = 1; i <= N; i++) {
		if (inD[i] == 0) {
			q.push(i);
			dist[i] = times[i];
		}
	}

	while (!q.empty()) {
		int cur = q.front(); q.pop();

		for (int& nxt : adj[cur]) {
			dist[nxt] = max(dist[nxt], dist[cur] + times[nxt]);
			if (--inD[nxt] == 0) {
				q.push(nxt);
			}
		}
	}

	int ans = 0;
	for (int i = 1; i <= N; i++) {
		ans = max(ans, dist[i]);
	}

	cout << ans;

	return 0;
}
