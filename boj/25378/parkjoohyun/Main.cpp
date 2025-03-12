//Memory : 2040kb
//Time : 0ms

#include <iostream>
#include <algorithm>


using namespace std;

int space[2500];
int dp[2500]; //index까지 1번 연산을 통해 줄인 횟수
int N;



int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> space[i];
	}
	for (int i = 0; i < N; i++) {
		int value = space[i];
		dp[i] = max(dp[i], dp[i - 1]);
		for (int j = i + 1; j < N; j++) { // i~j까지의 최솟값 구하기
			value = space[j] - value;
			if (value < 0)break; //오른쪽이 더 큰 경우
			if (value == 0)dp[j] = dp[i - 1] + 1; 
		}
	}



	cout << N - dp[N-1];
}