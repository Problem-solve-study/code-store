//11956KB / 120MS

#include <iostream>
#include <vector>
#include <queue>


using namespace std;
using ll = long long;
const ll INF = (ll)1e18;

// ���� ���� ����Ʈ (1-indexed)
int N, M;
vector<vector<pair<int, int>>> adj;

/**
 * @brief ���ͽ�Ʈ�� �˰���: start���� ��� �������� �ִܰŸ� ���
 * @param start    ��� ���� ��ȣ
 * @param ban_node �� ������ ��ο��� ������� �������� �ش� ��ȣ (�⺻�� 0�� ��� �� ��)
 * @return �� ���������� �ִܰŸ� �迭 (1..N)
 */
vector<ll> dijkstra(int start, int ban_node = 0) {
    // 1) �Ÿ� �迭 �ʱ�ȭ
    vector<ll> dist(N + 1, INF);
    dist[start] = 0;

    // 2) �ּ� ��(�켱���� ť): (������� �˷��� �ִܰŸ�, ������ȣ)
    priority_queue<pair<ll, int>,
        vector<pair<ll, int>>,
        greater<pair<ll, int>>> pq;
    pq.emplace(0, start);

    // 3) ���� ����
    while (!pq.empty()) {
        // -- pq.top()���� ���� ���� ��������� ������ ����
        auto top = pq.top(); pq.pop();
        ll curDist = top.first;   // ��������� �Ÿ�
        int u = top.second;  // ���� ����

        // �̹� �� ª�� ��ΰ� �߰ߵ� ��� ����
        if (curDist > dist[u])
            continue;

        // ���� ������ �� �̻� Ȯ������ ����
        if (u == ban_node)
            continue;

        // 4) ���� ���� relaxation
        for (auto& edge : adj[u]) {
            int v = edge.first;   // ���� ����
            int w = edge.second;  // ���� ����ġ

            // ���� ������ ban_node��� �ǳʶڴ�
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

    // 1) X��Y, Y��Z �ִ� �Ÿ� ��
    vector<ll> distX = dijkstra(X);
    vector<ll> distY = dijkstra(Y);
    ll ans1 = -1;
    if (distX[Y] < INF && distY[Z] < INF) {
        ans1 = distX[Y] + distY[Z];
    }

    // 2) X��Z (��, Y�� ��ο��� ����)
    vector<ll> distX_noY = dijkstra(X, Y);
    ll ans2 = (distX_noY[Z] < INF ? distX_noY[Z] : -1);

    cout << ans1 << " " << ans2 << "\n";
    return 0;
}