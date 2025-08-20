#include <bits/stdc++.h>
using namespace std;

long long a, b;

long long gcd(long long aa, long long bb) {
	if (bb == 0) return aa;
	return gcd(bb, aa % bb);
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> a >> b;
	
	long long s = 1;
	while (a >= s * s) s++;

	long long cnt = 0;
	while (s * s <= b) {
		//cout << s << '\n';
		s++;
		cnt++;
	}

	long long g = gcd(cnt, b - a);
	if (cnt == 0) cout << 0;
	else cout << cnt / g << '/' << (b - a) / g;

	return 0;
}
