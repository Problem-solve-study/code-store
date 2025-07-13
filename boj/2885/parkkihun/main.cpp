#include <iostream>
#include <algorithm>
#include <numeric>
#include <vector>
using namespace std;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int K; cin >> K;

	int S = 1;
	while (K > S) S <<= 1;

	for (int i = 0; i < 32; i++) {
		if (K & (1 << i)) {
			int cnt = 0;
			while (S != (1 << i)) {
				i++;
				cnt++;
			}

			cout << S << ' ' << cnt << '\n';
			return 0;
		}
	}

	return 0;
}
