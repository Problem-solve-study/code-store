//Memory : 19600kb
//Time : 200ms

#include <iostream>
#include <algorithm>

using namespace std;


int N;
int day[1'500'001]; //상담에 걸리는 일수
int price[1'500'001]; //상담 가격

int dp[1'500'501]; //일자까지의 최대 가격

void input() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> day[i] >> price[i];
	}
}

void DP() {
	int fin;
	for (int i = 1; i <= N; i++) {
		dp[i] = max(dp[i], dp[i - 1]);
		fin = i + day[i] - 1;
		if (fin <= N) {
			dp[fin] = max(dp[fin],dp[i-1] + price[i]);
		}
	}
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	DP();
	cout << dp[N];

}