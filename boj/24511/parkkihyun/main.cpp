#include <iostream>
#include <algorithm>
#include <numeric>
#include <vector>
#include <queue>
#include <tuple>
#include <unordered_map>
using namespace std;

int N;
deque<int> dq;
bool isStack[100'001];

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N;
	for (int i = 0; i < N; i++) cin >> isStack[i];

	for (int i = 0; i < N; i++) {
		int B; cin >> B;
		if (!isStack[i]) dq.push_front(B);
	}

	int M; cin >> M;
	while (M--) {
		int C; cin >> C;

		dq.push_back(C);
		C = dq.front(); dq.pop_front();

		cout << C << ' ';
	}

	return 0;
}
