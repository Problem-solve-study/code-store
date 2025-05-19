// 진화할 때마다 사탕 2개를 반환한다.
// 진화한 마리의 총 숫자와 가장 많이 진화한 id가 빠른 포켓몬을 출력하면 되는 문제

// map을 쓰려다가 n이 70이라 그냥 string[] 으로 해결.
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

string names[70];
vector<pair<int, int>> v;

bool cmp(pair<int, int>& p1, pair<int, int>& p2) {
	if (p1.first == p2.first) return p1.second < p2.second;
	return p1.first > p2.first;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int N; cin >> N;

	int cnt = 0;
	for (int i = 0; i < N; i++) {
		string name; cin >> name;
		int k, m; cin >> k >> m;

		names[i] = name;

		int headCnt = 0;
		while (m >= k) {
			m -= (k - 2);
			headCnt++;
		}

		cnt += headCnt;
		v.push_back({ headCnt, i });
	}

	sort(v.begin(), v.end(), cmp);

	cout << cnt << '\n' << names[v[0].second];

	return 0;
}
