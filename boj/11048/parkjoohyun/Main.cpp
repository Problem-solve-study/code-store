//Memory : 9848kb
//Time : 64ms

#include <iostream>
#include <algorithm>

using namespace std;

int maze[1001][1001];
int dp[1001][1001];
int N, M;

void input() {
	cin >> N >> M;
	for (int y = 1; y <= N; y++) {
		for (int x = 1; x <= M; x++) {
			cin >> maze[y][x];
		}
	}
}

void solution() {
	for (int y = 1; y <= N; y++) {
		for (int x = 1; x <= M; x++) {
			dp[y][x] = max(dp[y - 1][x], dp[y][x - 1]) + maze[y][x];
		}
	}

	cout << dp[N][M];
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	solution();
}