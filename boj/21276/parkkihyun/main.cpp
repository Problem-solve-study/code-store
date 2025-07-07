// string -> int 압축 후에
// 위상정렬하는 문제
#include <bits/stdc++.h>
using namespace std;

int N, M;
map<string, int> nameIdMap;
map<int, string> idNameMap;

vector<int> inD;
vector<vector<int>> adj;

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N;
    for (int i = 0; i < N; i++) {
        string name; cin >> name;
        nameIdMap[name] = i;
        idNameMap[i] = name;
    }

    adj.assign(N, vector<int>());
    inD.assign(N, 0);

    cin >> M;
    for (int i = 0; i < M; i++) {
        string X, Y; cin >> X >> Y;

        int x = nameIdMap[X];
        int y = nameIdMap[Y];

        adj[y].push_back(x);
        inD[x]++;
    }

    queue<int> q;
    map<string, set<string>> answer;
    for (int i = 0; i < N; i++) {
        if (inD[i] == 0) {
            q.push(i);
            answer[idNameMap[i]] = set<string>();
        }
    }

    cout << answer.size() << '\n';
    for (auto& a : answer) {
        cout << a.first << ' ';
    }
    cout << '\n';

    while (!q.empty()) {
        int cur = q.front(); q.pop();

        string& name = idNameMap[cur];
        if (answer.find(name) == answer.end()) answer[name] = set<string>();

        for (int nxt : adj[cur]) {
            string& nxtName = idNameMap[nxt];
            if (--inD[nxt] == 0) {
                answer[name].insert(nxtName);
                q.push(nxt);
            }
        }
    }

    for (auto& entry : answer) {
        cout << entry.first << ' ' << entry.second.size();

        for (const string& name : entry.second) {
            cout << ' ' << name;
        }

        cout << '\n';
    }

    return 0;
}
