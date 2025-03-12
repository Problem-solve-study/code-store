//Memory : 2056kb
//Time : 0ms

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<int> edges[1001];
int parent_cnt[1001];
int order[1001];
bool update[1001];
int K, M, P;

void input() {
	cin >> K >> M >> P;
	int u, v;
	for (int i = 0; i < P; i++) {
		cin >> u >> v;
		edges[u].push_back(v);
		parent_cnt[v]++;
	}
}

void solution() {
	queue<int> q;
	for (int i = 1; i <= M; i++) {
		if (parent_cnt[i] == 0) {
			q.push(i);
			order[i] = 1;
		}
	}
	while (!q.empty()) {
		int cur = q.front();
		q.pop();

		int l = edges[cur].size();
		for (int i = 0; i < l; i++) {
			int idx = edges[cur][i];
			if (order[idx] < order[cur]) {
				order[idx] = order[cur];
				update[idx] = false;
			}
			else if (order[idx] == order[cur] && !update[idx]) {
				order[idx]++;
				update[idx] = true;
			}
			else if(order[idx] == order[cur]){
				update[idx] = false;
			}
			parent_cnt[idx]--;
			if (parent_cnt[idx] == 0)q.push(idx);
		}
	}
}

void init() {
	for (int i = 1; i <= M; i++) {
		edges[i].clear();
		order[i] = 0;
		parent_cnt[i] = 0;
		update[i] = false;
	}
}

int main() {
	int T;
	cin >> T;
	for (int i = 1; i <= T; i++) {
		init();
		input();
		solution();
		cout << K << " "<<order[M] << "\n";
	}
}