// DP임을 직감했으나 DP로 풀고싶지 않아서 LCS로 접근했지만
// 결국 반례를 못 찾고 DP로 풀어버린 문제
// 바텀업은 도저히 못 하겠어서 그냥 탑다운으로 풂

// 공백을 추가한다고 했지만 진짜 넣을 필요는 없고
// 관념적으로 '넣었다' 고 생각하면 됨
// 그렇게 되면 문제가 굉장히 단순해짐
#include <bits/stdc++.h>
using namespace std;

#define INF 1'000'000'000

int A, B, C;
string X, Y;
vector<vector<int>> dp;

// i: X 문자열의 index, j: Y 문자열의 index
// i, j일 떄, 뒤의 값은 Pure 하므로 DP가 적용될 수 있다.
int solve(int i, int j) {
    if (i == X.length()) {
        return (Y.length() - j) * B;
    }

    if (j == Y.length()) {
        return (X.length() - i) * B;
    }

    int& ret = dp[i][j];
    if (ret != -INF) return ret;

    // 두 문자가 같을 때는 뒤에서 만들 수 있는 최대 점수에서 무조건 A를 더하면 된다.
    if (X[i] == Y[j]) {
        return ret = solve(i + 1, j + 1) + A;
    } else {
        // 같지 않을 때는, Y에 공백을 추가해서 보는 경우(solve(i+1, j)), X에 공백을 추가하는 경우, C를 적용하는 경우
        // 3개의 경우에서 최대값으로 가져오면 된다.
        return ret = max({solve(i + 1, j) + B, solve(i, j + 1) + B, solve(i + 1, j + 1) + C});
    }
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> A >> B >> C;
    cin >> X >> Y;

    dp.assign(3010, vector<int>(3010, -INF));

    int ans = solve(0, 0);

    cout << ans;

    return 0;
}
