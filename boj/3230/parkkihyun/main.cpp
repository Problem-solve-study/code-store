#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;
vector<int> f, s;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		int n; cin >> n;

		if (f.size() == n - 1) f.push_back(i);
		else f.insert(f.begin() + n - 1, i);
	}

	for (int i = M-1; i >= 0; i--) {
		int n; cin >> n;

		if (s.size() == n - 1) s.push_back(f[i]);
		else s.insert(s.begin() + n - 1, f[i]);
	}

	for (int i = 0; i < 3; i++) {
		cout << s[i] << '\n';
	}

	return 0;
}
