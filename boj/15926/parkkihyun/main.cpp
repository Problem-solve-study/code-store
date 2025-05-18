#include <bits/stdc++.h>
using namespace std;

bool chk[200'005];

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    int N; cin >> N;

    stack<int> s;
    
    for (int i = 0; i < N; i++) {
        char c; cin >> c;
        
        if (c == '(') {
            s.push(i);
        } else {
            if (!s.empty()) {
                chk[s.top()] = true;
                chk[i] = true;
                s.pop();
            }
        }
    }
    
    int ans = 0, cnt = 0;
    for (int i = 0; i < N; i++) {
        if (chk[i] == true) {
            cnt++;
            ans = max(ans, cnt);
        } else {
            cnt = 0;
        }
    }

    cout << ans;

    return 0;
}
