#include <bits/stdc++.h>
using namespace std;

bool isPrime[333333];
vector<int> primes;

void solve() { 
    int n; cin >> n;

    cout << primes[primes[n]] << '\n';
}

void init() {
    fill(isPrime, isPrime+333333, true);

    for (int i = 2; i * i < 333333; i++) {
        if (!isPrime[i]) continue;

        for (int j = (i << 1); j < 333333; j += i) isPrime[j] = false;
    }

    primes.push_back(1);
    for (int i = 2; i < 333333; i++) {
        if (isPrime[i]) primes.push_back(i);
    }
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    init();
    
    int T; cin >> T;
    while (T--) {
        solve();
    }

    return 0;
}
