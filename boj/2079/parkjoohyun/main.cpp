#include <iostream>
#include <vector>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	string s;
	cin >> s;
	int n = s.size();

	vector<vector<char>> pal(n, vector<char>(n, false));

	for (int i = 0; i < n; ++i) {
		pal[i][i] = true; // Single character is a palindrome
	}

	for (int i = 0; i < n - 1; ++i) {
		pal[i][i + 1] = (s[i] == s[i + 1]); // Two consecutive characters
	}

	for (int len = 3; len <= n; ++len) {
		for (int i = 0; i <= n - len; ++i) {
			int j = i + len - 1;
			pal[i][j] = (s[i] == s[j]) & pal[i + 1][j - 1];
		}
	}

	const int INF = 1e9;

	vector<int> dp(n, INF);
	for (int i = 0; i < n; i++) {
		if (pal[0][i]) {
			dp[i] = 1;
		}
		else {
			for (int j = 1; j <= i; j++) {
				if(pal[j][i]) dp[i] = min(dp[i], dp[j - 1] + 1);
			}
		}
	}

	cout << dp[n - 1] << '\n';
	return 0;
}