//11956KB / 120MS

#include <iostream>
#include <vector>
#include <queue>


using namespace std;
using ll = long long;
const ll INF = (ll)1e18;

// 전역 인접 리스트 (1-indexed)
int N, M;
vector<vector<pair<int, int>>> adj;

/**
 * @brief 다익스트라 알고리즘: start에서 모든 정점까지 최단거리 계산
 * @param start    출발 정점 번호
 * @param ban_node 이 정점을 경로에서 사용하지 않으려면 해당 번호 (기본값 0은 사용 안 함)
 * @return 각 정점까지의 최단거리 배열 (1..N)
 */
vector<ll> dijkstra(int start, int ban_node = 0) {
    // 1) 거리 배열 초기화
    vector<ll> dist(N + 1, INF);
    dist[start] = 0;

    // 2) 최소 힙(우선순위 큐): (현재까지 알려진 최단거리, 정점번호)
    priority_queue<pair<ll, int>,
        vector<pair<ll, int>>,
        greater<pair<ll, int>>> pq;
    pq.emplace(0, start);

    // 3) 메인 루프
    while (!pq.empty()) {
        // -- pq.top()에서 꺼낸 값을 명시적으로 변수에 저장
        auto top = pq.top(); pq.pop();
        ll curDist = top.first;   // 현재까지의 거리
        int u = top.second;  // 현재 정점

        // 이미 더 짧은 경로가 발견된 경우 무시
        if (curDist > dist[u])
            continue;

        // 금지 정점은 더 이상 확장하지 않음
        if (u == ban_node)
            continue;

        // 4) 인접 간선 relaxation
        for (auto& edge : adj[u]) {
            int v = edge.first;   // 인접 정점
            int w = edge.second;  // 간선 가중치

            // 도착 정점도 ban_node라면 건너뛴다
            if (v == ban_node)
                continue;

            ll nd = curDist + w;
            if (nd < dist[v]) {
                dist[v] = nd;
                pq.emplace(nd, v);
            }
        }
    }

    return dist;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> M;
    adj.assign(N + 1, {});
    for (int i = 0; i < M; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].emplace_back(v, w);
    }

    int X, Y, Z;
    cin >> X >> Y >> Z;

    // 1) X→Y, Y→Z 최단 거리 합
    vector<ll> distX = dijkstra(X);
    vector<ll> distY = dijkstra(Y);
    ll ans1 = -1;
    if (distX[Y] < INF && distY[Z] < INF) {
        ans1 = distX[Y] + distY[Z];
    }

    // 2) X→Z (단, Y는 경로에서 금지)
    vector<ll> distX_noY = dijkstra(X, Y);
    ll ans2 = (distX_noY[Z] < INF ? distX_noY[Z] : -1);

    cout << ans1 << " " << ans2 << "\n";
    return 0;
}