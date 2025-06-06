#include <bits/stdc++.h>
using namespace std;

using ll = long long;

/**
 * 분할 정복 문제?
 * [x, y]는 x 포함 y 포함까지의 자리수의 합이라고 정의하자
 * [x, y] = [0, y] - [0, x-1] 이 성립한다.
 * 
 * 먼저 [0, 9], [0, 99], [0, 999], ..., [0, 999_999_999] 를 구해보자.
 * [0, 9] = 45
 * [0, 99] = ? 를 구해보자.
 * [0, 99] = [0, 9] + [10, 99] 이다. 그럼 [10, 99]를 구해보자.
 * [10, 99] = 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, ...
 * 관측하면 1이 10번, 0~9가 등장했다. 즉, 1 * 10 + [0, 9] 가 된다.
 * 그럼 [10, 99] 는 1 * 10 + [0, 9] + 2 * 10 + [0, 9] + ... + 9 * 10 + [0, 9] 가 된다.
 * 즉, (1 + 2 + ... + 9) * 10 + [0, 9] * 9 가 된다.
 * 근데 [0, 9] = 0 * 10 + [0, 9] 로 볼 수 있으므로
 * [0, 99] = (0 + 1 + ... + 9) * 10 + [0, 9] * 10 이 된다.
 * 
 * [0, 999] = [0, 99] + [100, 999]
 * [100, 199] => 100, 101, 102, ..., 199 => 1 * 100 + [0, 99]
 * [200, 299] => 2 * 100 + [0, 99]
 * 즉, [0, 999] = (0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9) * 100 + [0, 99] * 10
 * 따라서, [0, 9999] = 45 * 1000 + [0, 999] * 10
 * 
 * dp[i] = [0, 10^i - 1] 이라고 한다면
 * dp[i] = 45 * 10^(i-1) + dp[i-1]*10 이 된다.
 * 
 * dp[3] = 45 * 10^2 + dp[2] * 10;
 * 
 * 그럼 일반적인 상황으로 대치를 해보자.
 * [0, 88] 을 구해보자.
 * [0, 86] = [0, 9] + [10, 86]
 * [10, 86] = [10, 19] + [20, 29] + [30, 39] + [40, 49] + ... + [80, 86]
 * 1 * 10 + [0, 9]
 * 2 * 10 + [0, 9]
 * ...
 * 7 * 10 + [0, 9]
 * 8 * 7 + [0, 6]
 * => ((1 + 2 + ... + 7) * 10 + [0, 9] * 7) + 8 * 7 + [0, 6]
 * 따라서 [0, 86] = (1 + 2 + ... + 7) * 10 + [0, 9] * 8 + [0, 6]
 * 따라서 [0, xy] = (1 + 2 + ... + x-1) * 10 + [0, 9] * x + [0, 6]
 * 
 * digit[i] = [0, i] 라고 한다면, 
 * 
 * [0, 12345] => [0, 9999] + [10000, 12345] 가 된다.
 * [10000, 12345] = 1 * (12345 - 10000 + 1) + [0, 2345]
 * [0, 2345] = [0, 999] + [1000, 2345],
 * [1000, 2345] = [1000, 1999] + [2000, 2345]
 *              = 1 * (1999 - 1000 + 1) + [0, 999] + 2 * (2345 - 2000 + 1) + [0, 345]
 * [0, 345] = [0, 99] + [100, 345]
 * [100, 345] = 1 * 100 + 2 * 100 + [0, 99] * 2 + 3 * (45 - 0 + 1) + [0, 45]
 * (1 + 2) * 100 + dp[3] * 3 + 
 * [0, 45] = [0, 9] + [10, 45]
 *                    [10, 45] = (1 + 2 + 3) * 10 + [0, 9] * 3 + 4 * (5 - 0 + 1) + [0, 5]
 * 
 * i: 자리수
 * [0, y] = digit[y / 10^(i-1) - 1] * 10^(i-1) + dp[i] * (y / 10^(i-1)) + (y / 10^(i-1)) * (y % 10^(i-1) + 1) + [0, y   % 10^(i-1)]
 * 
 * 
 * 이걸 다시 수식으로 표현하면
 * [0, 12345] = dp[5] + [10000, 12345]
 *            = dp[5] + (12345 - 10000 + 1) * 1 + [0, 2345]
 *            = dp[5] + (2346) + dp[4] + [1000, 2345]
 *            = dp[5] + 2346 + dp[4] + 1000 + dp[3] + 2*346 + [0, 345]
 *            = dp[5] + 
 * 
 * [10, xy] = (1 + ... + y + 1) * 10 + [0, 9] * 
 * 
 * 
 * [0, 123] 을 구해보자.
 * [0, 123] = [0, 99] + [100, 123] 이 된다.
 * [100, 123] = [100, 109] + [110, 119] + [120, 123]
 * 
 * [100, 109] = 100, 101, 102, 103, 104, 105, 106, ... => 1 * 10 + 0 * 10 + [0, 9]
 * [110, 119] = 110, 111, 112, 113, 114, 115, 116, ... => 1 * 10 + 1 * 10 + [0, 9]
 * 
 * 1'2'0 기준, [100, 119] = 1 * 10 * 2 + (for i in range(2)) * 10 + [0, 9] * 2
 */

ll dp[10];
ll digit[10] = {0, 1, 3, 6, 10, 15, 21, 28, 36, 45};

int getLength(int n) {
    int ret = 0;
    while (n  > 0) {
        ret++;
        n /= 10;
    }
    return ret;
}

ll getSum(ll N) {
    if (N == 0) return 0;
    
    int i = getLength(N);

    ll f = N / (ll) pow(10, i-1);
    ll b = N % (ll) pow(10, i-1);

    return digit[f-1] * (ll) pow(10, i-1) + dp[i-1] * f + f * (b + 1) + getSum(b);
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    dp[1] = 45;
    for (int i = 2; i < 10; i++) dp[i] = dp[1] * ((ll) pow(10, i-1)) + dp[i-1] * 10;

    int L, U; cin >> L >> U;

    if (L == 0) cout << getSum(U);
    else cout << getSum(U) - getSum(L-1);

    return 0;
}
