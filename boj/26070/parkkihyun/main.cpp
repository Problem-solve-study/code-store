// 3X -> Y, 3Y -> Z, 3Z -> X
// 이렇게 바로 오른쪽으로만 티켓을 교환할 수 있음
// 처음에는 양방향인줄 알고 BFS를 고려했으나 단방향임을 깨닫고 그리디하게 접근

// 최초에 해당 음식 티켓으로 배불리 먹인 다음
// 오른쪽으로 계속 티켓을 교환해나감
// 그렇게 교환할 티켓이 없어질 시점에서 
// 티켓이 부족하다면 부족한 티켓만큼 곰곰이가 밥을 못 먹음
#include <bits/stdc++.h>
using namespace std;

using ll = long long;

ll A, B, C, X, Y, Z;

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> A >> B >> C >> X >> Y >> Z;

    // 최초에 해당 음식 티켓으로 배불리 먹인 다음   
    ll A2 = X - A;
    ll B2 = Y - B;
    ll C2 = Z - C;

    ll ans = A + B + C;
    // 오른쪽으로 계속 티켓을 교환해나감
    while (true) {
        bool propagate = false;
        if (A2 > 2) {
            propagate = true;
            B2 += A2 / 3;
            A2 %= 3;
        }

        if (B2 > 2) {
            propagate = true;
            C2 += B2 / 3;
            B2 %= 3;
        }

        if (C2 > 2) {
            propagate = true;
            A2 += C2 / 3;
            C2 %= 3;
        }
        
        // 그렇게 교환할 티켓이 없어질 시점에서 
        if (!propagate) break;
    }
    
    // 티켓이 부족하다면 부족한 티켓만큼 곰곰이가 밥을 못 먹음 (음수기때문에 덧셈)
    if (A2 < 0) ans += A2;
    if (B2 < 0) ans += B2;
    if (C2 < 0) ans += C2;

    cout << ans;

    return 0;
}
