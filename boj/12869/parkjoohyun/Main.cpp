#include <iostream>

using namespace std;

int dmg[6][3] = { {9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,9,3},{1,3,9}};
int dp[61][61][61] = {  };


int Attack(int a, int b, int c) {
	if (a < 0) return Attack(0, b, c);
	if (b < 0) return Attack(a, 0, c);
	if (c < 0) return Attack(a, b, 0);
	if (a == 0 && b == 0 && c == 0) return 0;
	int& ret = dp[a][b][c];
	if (ret != 0) return ret;
	ret = 1000000000;
	for (int i = 0; i < 6; i++) {
		ret = min(ret, Attack(a - dmg[i][0], b - dmg[i][1], c - dmg[i][2]));
	}
	return ++ret;
}

int main() {
	int num;
	cin >> num;
	int hp[3] = { 0, };
	for (int i = 0; i < num; i++) {
		cin >> hp[i];
	}
	for (int i = num; i < 3; i++) {
		hp[i] = 0;
	}
	cout << Attack(hp[0], hp[1], hp[2]);
}