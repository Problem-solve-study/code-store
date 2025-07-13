#include <iostream>
#include <queue>
#include <string>
#include <vector>
using namespace std;

int N, K, S, E;
vector<string> hrr;
vector<int> adj[1010], prv, vis;

// 해밍 거리를 구하는 함수
int getDistance(string a, string b) {
	int ret = 0;

	for (int i = 0; i < K; i++) {
		int bitA = a[i] - '0';
		int bitB = b[i] - '0';

		if (bitA != bitB) ret++;
	}

	return ret;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> K;

	for (int i = 0; i < N; i++) {
		string a; cin >> a;
		hrr.push_back(a);
	}
	
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (i == j) continue;
			int distance = getDistance(hrr[i-1], hrr[j-1]);

			if (distance == 1) {
				adj[i].push_back(j);
			}
		}
	}

	cin >> S >> E;

	prv.assign(N + 1, 0), vis.assign(N + 1, -1);

	queue<int> q;

	vis[S] = 0;
	q.push(S);

	while (!q.empty()) {
		auto cur = q.front(); q.pop();

		for (auto& nxt : adj[cur]) {

			if (vis[nxt] == -1) {
				vis[nxt] = vis[cur] + 1;
				prv[nxt] = cur;
				q.push(nxt);
			}
		}
	}

	if (vis[E] == -1) cout << -1;
	else {
		vector<int> ans;

		while (E != 0) {
			ans.push_back(E);
			E = prv[E];
		}

		for (auto it = ans.rbegin(); it != ans.rend(); it++) {
			cout << *it << ' ';
		}
	}

	return 0;
}
