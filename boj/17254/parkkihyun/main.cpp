#include <iostream>
#include <tuple>
#include <queue>
using namespace std;

using tt = tuple<int, int, char>;

int N, M;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M;

	priority_queue<tt, vector<tt>, greater<tt>> pq;
	while (M--) {
		int a, b; char c;
		cin >> a >> b >> c;

		pq.push({ b, a, c });
	}

	while (!pq.empty()) {
		int a, b; char c;
		tie(a, b, c) = pq.top(); pq.pop();

		cout << c;
	}

	return 0;
}
