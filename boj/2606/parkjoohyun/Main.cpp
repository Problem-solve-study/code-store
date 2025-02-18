//Memory 2028KB
//Time 0ms

#include <iostream>
#include <vector>
#include <stack>

using namespace std;

vector<int> coms[101];
bool visit[101];

void input() {
	int u, v;
	cin >> u >> v;
	coms[v].push_back(u);
	coms[u].push_back(v);
}

int DFS(int n) {
	int cnt = -1;
	stack<int> wait;

	wait.push(n);
	while (!wait.empty()) {
		int parent = wait.top();
		wait.pop();
		cnt++;
		int len = coms[parent].size();
		for (int i = 0; i < len; i++) {
			int next = coms[parent][i];
			if (!visit[next]) {
				wait.push(next);
				visit[next] = true;
			}
		}
	}
	return cnt;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int N, K;
	cin >> N >> K;
	for (int i = 0; i < K; i++) {
		input();
	}
	visit[1] = true;
	cout << DFS(1);
}