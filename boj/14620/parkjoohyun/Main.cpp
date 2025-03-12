//Memory : 2020kb
//Time : 0ms

#include <iostream>
#include <algorithm>

using namespace std;

int land[10][10];
int N;
int min_pos, max_pos;
int dirY[] = { 0,0,1,-1 };
int dirX[] = { 1,-1,0,0 };
int min_value = 3'001;
bool valiable[10][10];


void input() {
	min_pos = 1;
	cin >> N;
	max_pos = N - 2;
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			cin >> land[y][x];
		}
	}
}

bool checkPos(int y, int x) {
	if (x > max_pos || x<min_pos || y> max_pos || y < min_pos) return false;

	for (int i = 0; i < 4; i++) {
		if (valiable[y + dirY[i]][x + dirX[i]]) return false;
	}
	return true;
}

void changeValiable(int y, int x) {
	valiable[y][x] = !valiable[y][x];
	for (int i = 0; i < 4; i++) {
		valiable[y + dirY[i]][x + dirX[i]] = !valiable[y + dirY[i]][x + dirX[i]];
	}
}

int sumGold(int y, int x) {
	int gold = 0;
	gold += land[y][x];
	for (int i = 0; i < 4; i++) {
		gold += land[y + dirY[i]][x + dirX[i]];
	}
	return gold;
}

void solution(int cnt, int gold, int y, int x) {
	if (cnt == 3) {
		min_value = min(min_value, gold);
		return;
	}

	if (gold >= min_value)
		return;

	for (int dy = 1; dy <= max_pos; dy++) {
		for (int dx = 1; dx <= max_pos; dx++) {
			if (valiable[dy][dx] || !checkPos(dy, dx))	continue;
			changeValiable(dy, dx);
			solution(cnt + 1, gold + sumGold(dy, dx), dy ,dx);
			changeValiable(dy, dx);
		}
	}
}

int main() {
	input();
	solution(0,0,min_pos, min_pos);
	cout << min_value;
}