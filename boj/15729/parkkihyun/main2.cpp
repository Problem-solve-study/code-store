#include <iostream>
using namespace std;

int N;
bool btn[1010101], btn2[1010101];

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N;
	for (int i = 0; i < N; i++) cin >> btn[i];

	int ans = 0;
	for (int i = 0; i < N; i++) {
		if (btn[i] != btn2[i]) {
			ans++;
			btn2[i] = !btn2[i];
			if (i + 1 < N) btn2[i + 1] = !btn2[i + 1];
			if (i + 2 < N) btn2[i + 2] = !btn2[i + 2];
		}
	}
	cout << ans;

	return 0;
}
