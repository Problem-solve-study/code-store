#include <iostream>
#include <string>
#include <vector>
using namespace std;

int N;
string s;
vector<vector<int>> dp1;
vector<int> dp2;

bool isPalindrome(int l, int r) {
	int& ret = dp1[l][r];
	if (ret != -1) return ret;

	for (int i = l, j = r; i <= j; i++, j--) {
		if (s[i] != s[j]) return ret = 0;
	}
	return ret = 1;
}

// idx 기준으로 뒤에 있는 팰린드롬을 나눈 최소 개수를 반환함
int solve(int idx) {
	if (idx == N) return 0;

	int& ret = dp2[idx];
	if (ret != -1) return ret;

	vector<int> possible;
	for (int i = idx; i < N; i++) {
		if (isPalindrome(idx, i)) possible.push_back(i+1);
	}

	int cnt = (int) 1e9;
	for (int nxt : possible) {
		cnt = min(cnt, solve(nxt));
	}

	return ret = cnt + 1;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> s;
	N = s.size();

	dp1.assign(N + 1, vector<int>(N + 1, -1));
	dp2.assign(N + 1, -1);

	cout << solve(0);

	return 0;
}
