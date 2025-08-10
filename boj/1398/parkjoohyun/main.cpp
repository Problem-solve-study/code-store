#include <iostream>
#include <vector>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	vector<int> minCoin(100, 1e9);
	minCoin[0] = 0;

	vector<int> coins = { 1, 10, 25 };
	for (int i = 1; i < 100; ++i) {
		for (int coin : coins) {
			if (i - coin >= 0) {
				minCoin[i] = min(minCoin[i], minCoin[i - coin] + 1);
			}
		}
	}


	int testcase;
	cin >> testcase;
	while(testcase--) {
		long long  n;
		cin >> n;

		long long result = 0;
		while (n > 0) {
			int block = n % 100;
			result += minCoin[block];
			n /= 100;
		}
		cout << result << '\n';
	}
}