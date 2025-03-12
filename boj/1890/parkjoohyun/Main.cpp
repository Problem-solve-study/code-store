//Memory : 2136kb
//Time : 0ms

#include <iostream>

using namespace std;

int N;
int map[100][100];  //K개를 사용해서 N인 경우
long long dp[100][100];
void input() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
		}
	}
}
bool checkPos(int y, int x) {
	if (y > -1 && y < N && x > -1 && x < N) return true;
	return false;
}

void solution() {
	dp[0][0] = 1;
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			if (dp[y][x] == 0 || (y == N - 1 && x == N - 1)) continue;
			int jump = map[y][x];

			if (y + jump < N) dp[y + jump][x] = dp[y + jump][x] + dp[y][x];
			if (x + jump < N) dp[y][x + jump] = dp[y][x + jump] + dp[y][x];
		}
	}

	cout << dp[N - 1][N - 1];
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	solution();
}