// 개.. 어려운데...? 이게 왜 골드 5...?

// 조화 수열이라는 개념이라고 함
#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

#define MOD 1'000'000

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int N; cin >> N;

	int sum = 0;
	for (int i = 2; i <= N / 2; i++) {
		sum += i * ((N / i) - 1);
		sum %= MOD;
	}

	cout << sum % MOD;

	return 0;
}
