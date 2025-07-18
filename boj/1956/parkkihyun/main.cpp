#include <bits/stdc++.h>
using namespace std;

#define INF 0x3f3f3f
using pii = pair<int, int>;

int V, E, a, b, c;
int dist[404][404];

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);
    
    for (int i = 0; i < 404; i++) {
        for (int j = 0; j < 404; j++) {
            dist[i][j] = INF;
        }
    }

    cin >> V >> E;
    for (int i = 0; i < E; i++) {
        cin >> a >> b >> c;
        dist[a][b] = c;
    }

    for (int k = 1; k <= V; k++) {
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }

    int ans = INF;
    for (int i = 1; i <= V; i++) {
        ans = min(ans, dist[i][i]);
    }

    if (ans == INF) cout << -1;
    else cout << ans;

    return 0;
}
