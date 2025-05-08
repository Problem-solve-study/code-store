// 문제가 조금 애매함
// 해당 부서의 모든 사람이 면담이 끝나야 퇴근할 수 있음
// 어떤 부서의 면담이 끝나면 그 시간은 그 뒤로 계속 더해지니까
// 많이 더해지는 수가 작은 수가 되어야 함

// 따라서 그리디.
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

using ll = long long;

int N;
int trr[1010];

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int headCnt; cin >> headCnt;

		int timeSum = 0;
		for (int j = 0; j < headCnt; j++) {
			int time; cin >> time;
			timeSum += time;
		}

		trr[i] = timeSum;
	}

	sort(trr, trr + N);

	ll ans = 0;
	for (int i = 0; i < N; i++) {
		ans += 1LL * (trr[i] * (N - i));
	}

	cout << ans;

	return 0;
}
