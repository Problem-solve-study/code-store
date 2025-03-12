//Memory 2024kb
//Time 0ms

#include <iostream>
#include <algorithm>

using namespace std;

int length;
char str[1001];
int dp[1001];

void input() {
	cin >> length;
	cin >> str;
}

bool check(char prev, char cur) {
	if (prev == 'B' && cur == 'O') return true;
	if (prev == 'O' && cur == 'J') return true;
	if (prev == 'J' && cur == 'B') return true;
	return false;
}

void solution() {
	for (int i = 1; i < length; i++) {
		dp[i] = 1'0'000'000;
	}
	dp[0] = 0;
	for (int i = 0; i < length; i++) {
		for (int j = i + 1; j < length; j++) {
			if (check(str[i], str[j])) {
				dp[j] = min(dp[j], (j - i)*(j - i) + dp[i]);
			}
		}
	}
	if (dp[length - 1] == 1'0'000'000)
		cout << -1;
	else
		cout << dp[length - 1];
}


int main() {
	input();
	solution();
}