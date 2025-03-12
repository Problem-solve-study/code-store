//Memory : 2020kb
//Time : 0ms

#include <iostream>
#include <algorithm>
using namespace std;

int P, Q, D;
int ans;
void input() {
	cin >> D >> P >> Q;

	if (P > Q) {
		int temp = P;
		P = Q;
		Q = temp;
	}
	ans = D / Q * Q + Q;
}

void solution() {
	int tmp = ans;
	int len = tmp / Q;
	for (int i = 1; i <= len; i++) {
		int temp = tmp - Q * i;

		if ((D - temp) % P == 0) {
			ans = D;
			break;
		}
		
		temp += ((D - temp) / P)*P + P;

		if (ans == temp)
			break;

		ans = min(ans, temp);
	}

	cout << ans;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	solution();
}