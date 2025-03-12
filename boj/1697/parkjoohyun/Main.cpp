//Memory : 2252KB
//Time : 0ms

#include <iostream>
#include <queue>


using namespace std;
int N, K;
bool visit[100'001];

bool checkPos(int x) {
	if (x > 100000 || x < 0) return false;
	return true;
}

int main() {
	cin >> N >> K;
	queue<pair<int, int>> que;
	que.push(make_pair(N, 0));

	while (!que.empty()) {
		int x = que.front().first;
		int time = que.front().second;
		que.pop();

		if (x == K) {
			cout << time;
			break;
		}
		if (checkPos(x + 1) && !visit[x + 1]) {
			visit[x + 1] = true;
			que.push(make_pair(x + 1, time + 1));
		}
		if (checkPos(x - 1) && !visit[x - 1]) {
			visit[x - 1] = true;
			que.push(make_pair(x - 1, time + 1));
		}
		if (checkPos(x * 2) && !visit[x * 2]) {
			visit[x * 2] = true;
			que.push(make_pair(x * 2, time + 1));
		}
	}
}