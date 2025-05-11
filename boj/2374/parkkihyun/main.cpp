// 분할정복을 이용한 풀이
#include <bits/stdc++.h>
using namespace std;

using ll = long long;

int N;
ll A[1010];

// 횟수, 최대값
ll solve(int start, int end, ll mx) {
    if (start == end) {
        return 0;
    }

    ll cnt = 0;

    int l = start, r = start;
    ll nxtMx = 0;
    for (r = start; r < end; r++) {
        nxtMx = max(nxtMx, A[r]);
        if (A[r] == mx) {
            ll tmp = solve(l, r, nxtMx);
            cnt += tmp + (mx - nxtMx);
            l = r + 1;
            nxtMx = 0;
        }
    }
    if (l != r) {
        ll tmp = solve(l, r, nxtMx);
        cnt += tmp + (mx - nxtMx);
    }

    return cnt;
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N;
    ll mx = 0;
    for (int i = 0; i < N; i++) {
        cin >> A[i];
        mx = max(mx, A[i]);
    }

    ll ans = solve(0, N, mx);

    cout << ans;

    return 0;
}

// 스택을 이용한 풀이
#include <bits/stdc++.h>
using namespace std;

using ll = long long;

int N;
stack<ll> s;

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);
    
    cin >> N;
    
    ll ans = 0, mx = 0;
    for (int i = 0; i < N; i++) {
        ll n; cin >> n;
        mx = max(mx, n);

        if (s.empty()) {
            s.push(n);
            continue;
        }

        if (s.top() < n) {
            ans += (n - s.top()); s.pop();
            s.push(n);
        } else if (s.top() > n) {
            s.push(n);
        }
    }

    ans += mx - s.top();
    cout << ans;

    return 0;
}
