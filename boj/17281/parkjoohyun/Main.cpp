//MEMORY		2020KB
//TIME		288ms

#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> player = vector<vector<int>>(50, vector<int>(9));
int order[9];
bool sel[9] = { false, false, false};
int max_score = 0;
int max_ining;

int Play() {
	int score = 0;
	int out = 0;
	bool base[3] = { false, false, false };
	int ining = 0;
	while (true) {
		for (int i = 0;i < 9;i++) {
			int hit = player[ining][order[i]];
			switch (hit) {
			case 0: //OUT
				out++; //OUT count 증가
				if (out == 3) {
					for (int b = 0;b < 3;b++) { //base 초기화
						base[b] = false;
					}
					ining++; //다음 이닝

					if (ining == max_ining)
						return score;
					out = 0;
				}
				break;
			case 1: //1st base hit
				if (base[2]) {
					score++;
					base[2] = false;
				}
				if (base[1]) {
					base[2] = true;

					base[1] = false;
				}
				if (base[0]) {
					base[1] = true;
					base[0] = false;
				}
				base[0] = true;
				break;
			case 2: //2nd base hit
				if (base[2]) {
					score++;
					base[2] = false;
				}
				if (base[1]) {
					score++;
					base[1] = false;
				}
				if (base[0]) {
					base[2] = true;
					base[0] = false;
				}
				base[1] = true;
				break;
			case 3: //3rd base hit
				if (base[2]) {
					score++;
					base[2] = false;
				}
				if (base[1]) {
					score++;
					base[1] = false;
				}
				if (base[0]) {
					score++;
					base[0] = false;
				}
				base[2] = true;
				break;
			case 4: //HOMERUN
				int plus_score = 1;
				for (int b = 0;b < 3;b++) {
					if (base[b])
						plus_score++;
					base[b] = false;
				}
				score += plus_score;
				break;
			}
		}
	}
}
//1. 순열
void DFS(int cnt) {
	if (cnt == 9) {
		int score = Play();
		if (score > max_score)
			max_score = score;
		return;
	}
	for (int i = 0;i < 9;i++) {
		if (sel[i])continue;
		sel[i] = true;
		order[i] = cnt;
		DFS(cnt + 1);
		sel[i] = false;
	}
}


//2. 게임 플레이


//3. 솔루션
void Solution() {
	order[3] = 0;
	sel[3] = true;
	DFS(1);
	cout << max_score << endl;
}

int main() {
	ios::sync_with_stdio(false);

	cin.tie(NULL);
	cin >> max_ining;

	for (int i = 0;i < max_ining;i++) {
		for (int p = 0;p < 9;p++) {
			cin >> player[i][p];
		}
	}
	Solution();
}