//Memory : 2024kb
//Time : 0ms

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<int> supply[26];
bool supplied[26];
bool check[26];
bool visit[26];
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int cnt = 0;
	int N, M;
	cin >> N >> M;
	char U, V;
	int u, v;
	for (int i = 0; i < M; i++) {
		cin >> U >> V;
		u = U - 'A';
		v = V - 'A';
		supply[u].push_back(v);
		supplied[v] = true;
	}
	
	int C;
	char c;
	cin >> C;
	for (int i = 0; i < C; i++) {
		cin >> c;
		check[c - 'A'] = true;
	}

	queue<int> drugs;
	for (int i = 0; i < N; i++) {
		if (supplied[i] == 0 && !check[i]) {
			drugs.push(i);
			visit[i] = true;
			cnt--;
		}
	}
	
	while (!drugs.empty()) {
		int p = drugs.front();
		drugs.pop();
		cnt++;
		int len = supply[p].size();
		for (int i = 0; i < len; i++) {
			int child = supply[p][i];
			if (!check[child] && !visit[child])
				drugs.push(child);
		}
	}

	cout << cnt;
}