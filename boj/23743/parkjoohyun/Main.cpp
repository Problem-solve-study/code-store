#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>

using namespace std;
using ll = long long;

struct DSU {
    // p[i] < 0 : i�� ��Ʈ�̸�, |p[i]|�� ���� ũ��
    // p[i] >=0 : i�� �θ� ��� ��ȣ
    vector<int> p;

    DSU(int n) : p(n, -1) {}

    // 1) ��ǥ ã�� (��� ���� ����)
    int find(int x) {
        if (p[x] < 0)
            return x;
        // ��� ȣ�� �� ���� ������ ��θ� ��źȭ
        return p[x] = find(p[x]);
    }

    // 2) ��ġ�� (ũ�� ����)
    bool unite(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return false;  // �̹� ���� ����

        // ũ�Ⱑ �� ū ���� a�� �д�
        if (p[a] > p[b])
            swap(a, b);

        // b�� a �Ʒ��� ��ġ��, a�� ���� ũ�� ����
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

    // 1) warp ������ �о (cost, u, v) ���·� ����
    vector<tuple<int, int, int>> edges;
    edges.reserve(m + n);  // warp + escape

    for (int i = 0; i < m; i++) {
        int u, v, c;
        cin >> u >> v >> c;
        edges.emplace_back(c, u, v);
    }

    // 2) �� �� i���� �ⱸ(0)�� ���� �����Ż�ⱸ�� ���� �߰�
    //    (cost = t_i, u=0, v=i)
    for (int i = 1; i <= n; i++) {
        int t;
        cin >> t;
        edges.emplace_back(t, 0, i);
    }

    // 3) ��� �������� ����
    sort(edges.begin(), edges.end(),
        [](auto& A, auto& B) {
            return get<0>(A) < get<0>(B);
        });

    // 4) Kruskal + DSU
    DSU dsu(n + 1);  // 0������ n������ �� n+1��
    ll answer = 0;
    int used = 0;
    for (auto& e : edges) {
        int cost, u, v;
        tie(cost, u, v) = e;
        if (dsu.unite(u, v)) {
            answer += cost;
            if (++used == n) break;
            // MST�� �ϼ��Ƿ��� n���� ������ �ϳ��� �������� �ϹǷ�
            // �����δ� ���� n���� ���� �ʰ� ������ ����-1���� ������ ����ϹǷ�
            // used == n �� �Ǹ� ����� ���� �� �ֽ��ϴ�.
        }
    }

    cout << answer << "\n";
    return 0;
}
