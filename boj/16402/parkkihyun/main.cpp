#include <bits/stdc++.h>
using namespace std;

int N, M;
map<string, int> kingdoms;
vector<int> p;

int find(int x) {
    if (p[x] == -1) return x;
    return p[x] = find(p[x]);
}

void uni(int loser, int winner) {
    int pLoser = find(loser);
    int pWinner = find(winner);

    if (pLoser == pWinner) {
        p[loser] = winner;
        p[winner] = -1;
    } else {
        p[pLoser] = pWinner;
    }
}

vector<string> split(string& result) {
    vector<string> ret;

    string tmp = "";

    for (char& c : result) {
        if (c == ',') {
            ret.push_back(tmp);
            tmp = "";
        } else {
            tmp += c;
        }
    }

    ret.push_back(tmp);

    return ret;
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N >> M; cin.ignore();

    p.assign(N, -1);

    int idx = 0;
    for (int i = 0; i < N; i++) {
        string name; getline(cin, name);
        kingdoms[name] = idx++;
    }

    for (int i = 0; i < M; i++) {
        string result; getline(cin, result);

        vector<string> v = split(result);

        string name1 = v[0];
        string name2 = v[1];

        if (v[2] == "1") {
            uni(kingdoms[name2], kingdoms[name1]);
        } else {
            uni(kingdoms[name1], kingdoms[name2]);
        }
    }

    vector<string> king;
    for (auto& kingdom : kingdoms) {
        const string& name = kingdom.first;
        const int& idx = kingdom.second;

        if (p[idx] == -1) {
            king.push_back(name);
        }
    }

    sort(king.begin(), king.end());

    cout << king.size() << '\n';
    for (string& k : king) {
        cout << k << '\n';
    }

    return 0;
}
