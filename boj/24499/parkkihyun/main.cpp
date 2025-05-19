// 슬라이딩 윈도우 문제
// 회전 초밥과 유사해서 바로 풀 수 있었음
#include <bits/stdc++.h>
using namespace std;

int N, K;
int pies[100005];

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);
    
    cin >> N >> K;
    for (int i = 0; i < N; i++) cin >> pies[i];

    int sum = 0;
    for (int i = 0 ; i < K; i++) {
        sum += pies[i];
    }

    int ans = sum;

    for (int i = 0; i < N; i++) {
        sum -= pies[i];
        sum += pies[(i+K)%N];
        ans = max(ans, sum);
    }
    cout << ans;
    
    return 0;
}
