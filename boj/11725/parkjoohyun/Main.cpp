//Memory : 8060kb
//Time : 40ms

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N;

vector<int> connect[100'001];
int parent[100'001];
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	int u, v;
	for (int i = 1; i < N; i++) {
		cin >> u >> v;
		connect[u].push_back(v);
		connect[v].push_back(u);
	}
	queue<int> visit;
	visit.push(1);

	while (!visit.empty()) {
		int k = visit.front();
		visit.pop();
		int len = connect[k].size();
		for (int i = 0; i < len; i++) {
			int child = connect[k][i];
			if (parent[child] == 0) {
				parent[child] = k;
				visit.push(child);
			}
		}
	}

	for (int i = 2; i <= N; i++) {
		cout << parent[i]<<"\n";
	}
}