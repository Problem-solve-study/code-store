// k번 불을 붙일 수 있다는 말은 곧 k-1번 불을 끌 수 있다는 말
// 휴지기가 가장 긴 시간부터 끈다고 하면 됨
#include <bits/stdc++.h>
using namespace std;

int N, K;
vector<int> times, deltas;

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N >> K;
    times.assign(N+1, 0);

    for (int i = 1; i <= N; i++) {
        cin >> times[i];
    }

    int total = times[N] - times[1] + 1;

    int leaveTime = times[1] + 1;

    for (int i = 2; i <= N; i++) {
        deltas.push_back(times[i] - leaveTime);

        leaveTime = times[i] + 1;
    }

    sort(deltas.rbegin(), deltas.rend());

    for (int i = 0; i < K-1; i++) {
        total -= deltas[i];
    }

    cout << total;

    return 0;
}
