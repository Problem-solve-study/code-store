#include <bits/stdc++.h>
using namespace std;

int N, M, K;
vector<string> S;

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N >> M >> K;

    S.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> S[i];

        sort(S[i].begin(), S[i].end());
    }

    sort(S.begin(), S.end());

    string ans = "";
    for (int i = 0; i < K; i++) {
        ans += S[i];
    }

    sort(ans.begin(), ans.end());

    cout << ans;

    return 0;
}
