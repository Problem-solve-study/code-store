// 1000 아래의 소수가 몇 개 안 된다는 점을 이용해서 문제를 접근했습니다.

#include <bits/stdc++.h>
using namespace std;

vector<int> primes;

void init() {
	vector<bool> isPrime(1050, true);

	isPrime[0] = isPrime[1] = false;
	for (int i = 2; i * i < 1050; i++) {
		for (int j = i * 2; j < 1050; j += i) {
			isPrime[j] = false;
		}
	}

	for (int i = 0; i < 1050; i++) {
		if (isPrime[i]) {
			primes.push_back(i);
		}
	}
}

vector<int> solve(int target) {
	vector<int> ans;
	for (int i = 0; i < primes.size(); i++) {
		for (int j = 0; j < primes.size(); j++) {
			for (int k = 0; k < primes.size(); k++) {
				if (primes[i] + primes[j] + primes[k] == target) {
					ans.push_back(i), ans.push_back(j), ans.push_back(k);

					sort(ans.begin(), ans.end());

					return ans;
				}
			}
		}
	}

	return ans;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	init();

	int T; cin >> T;
	while (T--) {
		int K; cin >> K;

		vector<int> ans = solve(K);

		for (int& i : ans) {
			cout << primes[i] << ' ';
		}
		cout << '\n';
	}

	return 0;
}
