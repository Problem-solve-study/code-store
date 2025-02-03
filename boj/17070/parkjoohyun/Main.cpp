//Memory 2020kb
//time 0ms

#include <iostream>

using namespace std;
// 0: vertical  1: horizontal  2: digonal

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	int dp[17][17][3] = { 0, };
	int room[17][17] = { 0, };
	int N;
	cin >> N;
	
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> room[i][j];
		}
	}
	dp[0][1][0] = 1;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (room[i][j] == 1) continue; //Wall

			if (room[i][j + 1] == 0)
				dp[i][j + 1][0] += dp[i][j][0] + dp[i][j][2]; //vertical & digonal

			if (room[i+1][j] == 0)
				dp[i+1][j][1] += dp[i][j][1] + dp[i][j][2]; //horizontal & digonal

			if ((room[i][j + 1]|room[i+1][j]|room[i+1][j+1])==0) 
				dp[i+1][j + 1][2] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2]; //horizontal & vertical & diagonal
		}
	}

	cout << dp[N][N][2];
}