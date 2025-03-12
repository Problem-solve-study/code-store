//Memory : 2412kb
//Time : 24ms

#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

int N;

int dp[100'001];

void input() {
	cin >> N;
}


void solution() {
	dp[1] = 1;
	for (int i = 2; i <= N; i++) {
			dp[i] = INT32_MAX;
			for (int j = 1; j*j <= i; j++) {
				dp[i] = min(dp[i], dp[i - j * j] + 1);
			}
	}
	cout << dp[N];
}

int main() {
	input();
	solution();
}


