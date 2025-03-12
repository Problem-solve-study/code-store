//Memory : 2020kb
//Time : 16ms

#include <iostream>

using namespace std;


int N, M;

int arr[8];
bool visit[8];

void input() {
	cin >> N >> M;
}

void dfs(int cnt) {
	if (cnt == M) {
		for (int i = 0; i < M; i++)
			cout << arr[i]<<" ";
		cout << "\n";
		return;
	}

	for (int i = 1; i <= N; i++) {
		if (visit[i] == false) {
			visit[i] = true;
			arr[cnt] = i;
			dfs(cnt + 1);
			visit[i] = false;
		}
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);


	input();
	dfs(0);
}