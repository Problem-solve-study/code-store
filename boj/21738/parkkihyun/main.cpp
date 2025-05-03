#include <bits/stdc++.h>
using namespace std;

// 펭귄은 지지대와 2개만 연결되면 된다.
// 가장 가까운 지지대 2개만 연결되면 펭귄은 떨어지지 않음
// 그래서 각 지지대까지 거리를 계산한 후, (가장 가까운 2개의 거리 + 자기 자신 1칸)을 빼주면 정답

#define INF 0x3f3f3f

int N, S, P;

vector<vector<int>> adj;
vector<int> dist;

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N >> S >> P;
    adj.assign(N+1, vector<int>());
    dist.assign(N+1, INF);

    for (int i = 0; i < N-1; i++) {
        int a, b; cin >> a >> b;

        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    queue<int> q;

    dist[P] = 0;
    q.push(P);

    while (!q.empty()) {
        int cur = q.front(); q.pop();

        for (int nxt : adj[cur]) {
            if (dist[nxt] <= dist[cur] + 1) continue;

            dist[nxt] = dist[cur] + 1;
            q.push(nxt);
        }
    }

    sort(dist.begin()+1, dist.begin()+S+1);
    
    cout << N - dist[1] - dist[2] - 1;

    return 0;
}
