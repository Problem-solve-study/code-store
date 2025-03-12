//Memory : 2028kb
//Time : 0ms

#include <iostream>
#include <algorithm>

using namespace std;

int happy[20];
int dmg[20];
int num;
int dp[20][101];

void input() {
	cin >> num;
	for (int i = 0; i < num; i++) {
		cin >> dmg[i];
	}
	for (int i = 0; i < num; i++) {
		cin >> happy[i];
	}
}

int dfs(int cnt, int hp) {
	if (hp <= 0) return 0;
	if (cnt == num) return 0;

	int &ret = dp[cnt][hp];
	if (ret != 0)return ret;
	
	ret = dfs(cnt + 1, hp);
	if (hp > dmg[cnt])
		ret = max(ret, dfs(cnt + 1, hp - dmg[cnt]) + happy[cnt]);

	return ret;
}

int main() {
	input();
	cout << dfs(0, 100);
}