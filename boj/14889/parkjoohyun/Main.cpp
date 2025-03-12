//Memory : 2024kb
//Time : 144ms

#include <iostream>

using namespace std;

int players[20][20];
bool selected[20];
int N, diff = 200000;

void input() {
	cin >> N;

	for (int p = 0; p < N; p++) {
		for (int o = 0; o < N; o++) {
			cin >> players[p][o];
		}
	}
}

void sumScore() {
	int oppo = 0;
	int frie = 0;
	for (int i = 0; i < N; i++) {
		int sum = 0;
		for (int j = 0; j < N; j++) {
			if (selected[i] == selected[j]) {
				sum += players[i][j];
			}
		}
		if (selected[i] == true)
			frie += sum;
		else
			oppo += sum;
	}
	int minus = frie - oppo;
	if (minus < 0)
		minus *= -1;
	
	if (diff > minus)
		diff = minus;
}

void permutation(int cnt, int idx) {
	if (cnt == N / 2) {
		sumScore();
		return;
	}

	for (int i = idx; i < N; i++) {
		if (selected[i]) continue;
		selected[i] = true;
		permutation(cnt + 1, i);
		selected[i] = false;
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	permutation(0,0);

	cout << diff;
}