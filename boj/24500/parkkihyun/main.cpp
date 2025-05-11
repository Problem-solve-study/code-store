// 비트마스킹 문제
// N보다 큰 (2의 배수 - 1)을 만들면 되는 문제
#include <bits/stdc++.h>
using namespace std;

using ll = long long;

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    ll N; cin >> N;

    ll X = 1;
    while (N >= X) X <<= 1;
    X--;

    if (N == X) {
        cout << 1 << '\n' << X;
    } else {
        cout << 2 << '\n' << (N^X) << ' ' << N;
    }

    return 0;
}
