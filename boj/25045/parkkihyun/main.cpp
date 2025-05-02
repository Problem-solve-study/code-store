#include <iostream>
#include <queue>
using namespace std;

using ll = long long;

int N, M;
priority_queue<int, vector<int>, greater<int>> minHeap;
priority_queue<int, vector<int>, less<int>> maxHeap;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int a; cin >> a;
		maxHeap.push(a);
	}

	for (int i = 0; i < M; i++) {
		int b; cin >> b;
		minHeap.push(b);
	}

	ll ans = 0;

	while (!maxHeap.empty() && !minHeap.empty()) {
		int a = maxHeap.top();
		int b = minHeap.top();

		if (a - b < 0) break;

		maxHeap.pop();
		minHeap.pop();

		ans += (a - b);
	}

	cout << ans;

	return 0;
}
