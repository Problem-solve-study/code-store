//Memory 2020KB
//Time 0ms

#include <iostream>

using namespace std;

long long dp[36];

long long DP(int cnt) {
	if (dp[cnt]!= 0)
		return dp[cnt];
	long long res = 0;
	for (int i = 1; i <= cnt; i++) {
		res += (DP(cnt - i)*DP(i-1));
	}
	dp[cnt] = res;
	return dp[cnt];
}

int main() {
	dp[0] = 1;
	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 5;
	int N;
	cin >> N;
	DP(N);
	cout << dp[N];
}
