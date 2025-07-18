#include <iostream>
#include <queue>
using namespace std;

queue<int> q;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int N; cin >> N;
	for (int i = 1; i <= N; i++) q.push(i);


	long long t = 1;
	while (q.size() > 1) {
		long long t3 = t * t * t - 1;

		long long idx = t3 % q.size();

		for (int i = 0; i < idx; i++) {
			q.push(q.front());
			q.pop();
		}
		q.pop();
		t++;
	}

	cout << q.front();

	return 0;
}
