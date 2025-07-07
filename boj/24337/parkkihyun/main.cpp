#include <iostream>
#include <algorithm>
using namespace std;

int N, a, b;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> a >> b;

	if (a + b > N + 1) {
		cout << -1;
		return 0;
	}

	if (a == 1 && b == 1) {
		for (int i = 0; i < N; i++) cout << 1 << ' ';
		return 0;
	}

	if (a == 1) {
		cout << b << ' ';
		for (int i = 0; i <= N - (a + b); i++) cout << 1 << ' ';
		for (int i = b - 1; i >= 1; i--) cout << i << ' ';
		return 0;
	}

	if (b == 1) {
		for (int i = 0; i <= N - (a + b); i++) cout << 1 << ' ';
		for (int i = 1; i <= a - 1; i++) cout << i << ' ';
		cout << a << ' ';
		return 0;
	}

	if (a > b) {
		for (int i = 0; i <= N - (a + b); i++) cout << 1 << ' ';
		for (int i = 1; i <= a; i++) cout << i << ' ';
		for (int i = b - 1; i >= 1; i--) cout << i << ' ';
		return 0;
	}

	if (a < b) {
		for (int i = 0; i <= N - (a + b); i++) cout << 1 << ' ';
		for (int i = 1; i <= a - 1; i++) cout << i << ' ';
		for (int i = b; i >= 1; i--) cout << i << ' ';
		return 0;
	}

	return 0;
}
