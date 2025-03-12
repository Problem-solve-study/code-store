//Memory : 2040kb
//Time : 100ms

#include <iostream>

using namespace std;

int N, K;

int dp[5001];

void input() {
	cin >> N >> K;
}

void solution() {
	if (N == 0) {
		cout << 1;
		return;
	}
	for (int j = 1; j <= K; j++) {
		dp[j] = j;
	}
	for (int i = 2; i <= N; i++) {
		for (int j = 2; j <= K; j++) {
			dp[j] =  (dp[j] + dp[j - 1]) % 1'000'000'000;
		}
	}

	cout << dp[K];
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	solution();
}