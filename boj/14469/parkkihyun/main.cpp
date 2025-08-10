#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
using namespace std;

using pii = pair<int, int>;

int N, A, B;
vector<pii> cows;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N;
	cows.resize(N);

	for (int i = 0; i < N; i++) {
		cin >> A >> B;
		cows[i] = { A, B };
	}

	sort(cows.begin(), cows.end());

	int time = 0;
	for (int i = 0; i < N; i++) {
		tie(A, B) = cows[i];

		if (time < A) time = A;
		time += B;
	}

	cout << time;

	return 0;
}
