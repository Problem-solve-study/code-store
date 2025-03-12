//Memory : 2020kb
//Time : 0ms

#include <iostream>
using namespace std;

int num[9];
bool sel[9];
int purpose = 0;
bool finish = false;

void input() {
	for (int i = 0; i < 9; i++) {
		cin >> num[i];
		purpose += num[i];
	}
	purpose -= 100;
}

void print() {
	for (int i = 0; i < 9; i++) {
		if (!sel[i])
			cout << num[i] << "\n";
	}
}

void DFS(int cnt, int sum, int idx) {
	if (cnt == 2 || finish) {
		if (purpose == sum) {
			print();
			finish = true;
		}
		return;
	}
	
	for (int i = idx+1; i < 9; i++) {
		if (!sel[i]) {
			sel[i] = true;
			DFS(cnt + 1, sum + num[i], i);
			sel[i] = false;
		}
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie();
	cout.tie();


	input();
	DFS(0, 0, -1);

}