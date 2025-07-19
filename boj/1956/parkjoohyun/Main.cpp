#include <iostream>
#include <vector>

using namespace std;

const int INF = 1e9;



int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	// Input number of vertices and edges
	int V, E;
	cin >> V >> E;

	// Initialize the distance matrix with INF
	vector<vector<int>> dist(V + 1, vector<int>(V + 1, INF));
	for (int i = 1; i <= V; ++i) {
		dist[i][i] = INF;
	}

	//Edge Input
	for (int i = 0; i < E; ++i) {
		int a, b, c;
		cin >> a >> b >> c;
		dist[a][b] = min(dist[a][b], c);
	}

	// Floyd-Warshall algorithm
	for (int k = 1; k <= V; ++k) {
		for (int i = 1; i <= V; ++i) {
			if (dist[i][k] == INF) continue;
			for (int j = 1; j <= V; ++j) {
				if (dist[k][j] == INF) continue;
				dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
			}
		}
	}

	// Min Cycle Dist
	int answer = INF;

	for (int i = 1; i <= V; ++i) {
		answer = min(answer, dist[i][i]);
	}

	if (answer == INF)
		cout << -1 << endl;
	else
		cout << answer << endl;

	return 0;
}