// gcd(x, y) = a, x + y = b
// 1. gcd(x, y) = a
// x mod a = y mod a -> x = ka, y = k'a

// 2. x + y = b
// 1. 의 조건에 의해 x + y = a(k + k') = b
// a와 b는 자연수이다. 따라서 k + k'는 정수이므로 b % a = 0 이어야 한다.
// 또한, k > 0, k' > 0 이므로 b != a 이어야 한다.

#include <iostream>
using namespace std;

using ll = long long;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int Q; cin >> Q;
	while (Q--) {
		ll a, b; cin >> a >> b;
		if (b == a) cout << 0 << '\n';
		else cout << (b %a == 0) << '\n';
	}

	return 0;
}
