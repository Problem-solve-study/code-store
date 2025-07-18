#include <bits/stdc++.h>
using namespace std;

using pii = pair<int, int>;

int n;

void print(vector<int>& v) {
    if (v.empty()) {
        cout << "None";
    } else {
        for (int& i : v) {
            cout << i << ' ';
        }
    }
    cout << '\n';
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> n;

    queue<pii> q;
    vector<int> A, B, C;

    int op, a, b;
    while (n--) {
        cin >> op;

        if (op == 1) {
            cin >> a >> b;
            q.push({a, b});
        } else {
            cin >> b;

            pii cur = q.front(); q.pop();

            if (cur.second == b) A.push_back(cur.first);
            else B.push_back(cur.first);
        }
    }

    while (!q.empty()) {
        C.push_back(q.front().first); q.pop();
    }

    sort(A.begin(), A.end());
    sort(B.begin(), B.end());
    sort(C.begin(), C.end());

    print(A);
    print(B);
    print(C);
    
    return 0;
}
