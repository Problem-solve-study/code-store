#include <iostream>
#include <tuple>
#include <queue>
using namespace std;

int N;
vector<int> pre, in;

void solve(int pl, int pr, int il, int ir) {
	int root = pre[pl];
	
	if (pl >= pr) {
		cout << root << ' ';
		return;
	}

	// inorder 에서 root를 찾음
	int ic = il;
	for (; in[ic] != root; ic++);

	int lcnt = ic - il;

	if (lcnt != 0) solve(pl + 1, pl + lcnt, il, ic - 1);

	int rcnt = ir - ic;

	if (rcnt != 0) solve(pr - rcnt + 1, pr, ic + 1, ir);

	cout << root << ' ';
}

void init() {
	cin >> N;

	pre.assign(N+1, 0), in.assign(N+1, 0);

	for (int i = 1; i <= N; i++) cin >> pre[i];
	for (int i = 1; i <= N; i++) cin >> in[i];
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int T; cin >> T;
	while (T--) {
		init();
		solve(1, N, 1, N);
		cout << '\n';
	}
	return 0;
}
