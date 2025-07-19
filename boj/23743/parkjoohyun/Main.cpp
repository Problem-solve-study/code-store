#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>

using namespace std;
using ll = long long;

struct DSU {
    // p[i] < 0 : i가 루트이며, |p[i]|가 집합 크기
    // p[i] >=0 : i의 부모 노드 번호
    vector<int> p;

    DSU(int n) : p(n, -1) {}

    // 1) 대표 찾기 (경로 압축 적용)
    int find(int x) {
        if (p[x] < 0)
            return x;
        // 재귀 호출 후 값을 갱신해 경로를 평탄화
        return p[x] = find(p[x]);
    }

    // 2) 합치기 (크기 기준)
    bool unite(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return false;  // 이미 같은 집합

        // 크기가 더 큰 쪽을 a로 둔다
        if (p[a] > p[b])
            swap(a, b);

        // b를 a 아래로 합치고, a의 집합 크기 증가
        p[a] += p[b];
        p[b] = a;
        return true;
    }
};


int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    // 1) warp 간선을 읽어서 (cost, u, v) 형태로 저장
    vector<tuple<int, int, int>> edges;
    edges.reserve(m + n);  // warp + escape

    for (int i = 0; i < m; i++) {
        int u, v, c;
        cin >> u >> v >> c;
        edges.emplace_back(c, u, v);
    }

    // 2) 각 방 i에서 출구(0)로 가는 “비상탈출구” 간선 추가
    //    (cost = t_i, u=0, v=i)
    for (int i = 1; i <= n; i++) {
        int t;
        cin >> t;
        edges.emplace_back(t, 0, i);
    }

    // 3) 비용 오름차순 정렬
    sort(edges.begin(), edges.end(),
        [](auto& A, auto& B) {
            return get<0>(A) < get<0>(B);
        });

    // 4) Kruskal + DSU
    DSU dsu(n + 1);  // 0번부터 n번까지 총 n+1개
    ll answer = 0;
    int used = 0;
    for (auto& e : edges) {
        int cost, u, v;
        tie(cost, u, v) = e;
        if (dsu.unite(u, v)) {
            answer += cost;
            if (++used == n) break;
            // MST가 완성되려면 n개의 정점이 하나로 합쳐져야 하므로
            // 실제로는 간선 n개를 쓰지 않고 “정점 개수-1”개 간선을 사용하므로
            // used == n 이 되면 충분히 멈출 수 있습니다.
        }
    }

    cout << answer << "\n";
    return 0;
}
