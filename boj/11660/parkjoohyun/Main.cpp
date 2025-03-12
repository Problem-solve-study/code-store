//Memory : 6124kb
//Time : 236ms

#include <iostream>

using namespace std;

int tc, n;
int map[1025][1025];


void input() {
	cin >> n >> tc;
	n++;
	int sum;
	for (int y = 1; y < n; y++) {
		sum = 0;
		for (int x = 1; x < n; x++) {
			cin >> map[y][x];
			sum += map[y][x];
			map[y][x] = sum;
		}
	}
}

void solution() {
	int xf, xs, yf, ys;
	cin >> yf >> xf >> ys >> xs;

	int res = 0;
	for (int y = yf; y <= ys; y++) {
		res += (map[y][xs] - map[y][xf-1]);
	}
	cout << res << "\n";
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	while (tc) {
		solution();
		tc--;
	}

}