// 장신구를 만들고 가치가 없으므로 정렬 후 피로도가 낮은 순으로 제작하면 됨
// 정렬 + 그리디 문제
#include <bits/stdc++.h>
using namespace std;

int P, N;
int A[1005];

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> P >> N;
    for (int i = 1; i <= N; i++) cin >> A[i];

    sort(A+1, A+1+N);

    int cnt = 0;
    for (int i = 1; i <= N; i++) {
        if (P >= 200) break;

        P += A[i];
        cnt++;
    }

    cout << cnt;

    return 0;
}
