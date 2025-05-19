// 대각선이 겹치는 부분이 없기때문에
// n개의 점에서 4개를 이용해서 사각형 하나를 만들면 교점이 생긴다.
// 그래서 nC4를 하면 된다.
#include <bits/stdc++.h>
using namespace std;


int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int N; cin >> N;
	cout << (N * (N - 1) * (N - 2) * (N - 3)) / 24;

	return 0;
}
