#include <iostream>
#include <algorithm>
#include <numeric>
#include <vector>
using namespace std;

int N;
vector<int> p, d;

int getDistance(int x) {
	if (p[x] == x) return 0;
	d[x] += getDistance(p[x]);
	p[x] = p[p[x]];
	return d[x];
}

int find(int x) {
	if (p[x] == x) return x;
	return find(p[x]);
}

void uni(int u, int v) {
	int nv = find(v);
	d[u] = getDistance(v) + abs(u - v) % 1000;
	p[u] = nv;
}

void solve() {
	while (1) {
		char op; cin >> op;
		if (op == 'O') break;

		if (op == 'E') {
			int I; cin >> I;
			cout << getDistance(I) << '\n';
		}

		else if (op == 'I') {
			int I, J; cin >> I >> J;
			uni(I, J);
		}
	}
}

void init() {
	cin >> N;

	p.assign(N + 1, 0);
	iota(p.begin(), p.end(), 0);
	
	d.assign(N + 1, 0);
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int T; cin >> T;
	while (T--) {
		init();
		solve();
	}
	return 0;
}
