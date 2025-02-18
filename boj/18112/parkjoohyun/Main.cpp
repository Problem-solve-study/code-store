#include <iostream>
#include <string>
#include <queue>

using namespace std;

int start = 0;
int purpose = 0;
int min = 1024;

int BFS() {
	queue<int> que;
	int cost[1024];
	for (int i = 0; i < 1024; i++) {
		cost[i] = -1;
	}
	cost[start] = 0;
	que.push(start);
	while (!que.empty()) {
		int cur = que.front();
		que.pop();
		int nxt_cost = cost[cur] + 1;
		for (int i = 0, n = cur; n >> 1; i++, n >>= 1) { //XOR
			int nxt = cur ^ 1 << i;
			if (cost[nxt] == -1) {
				cost[nxt] = nxt_cost;
				que.push(nxt);
			}
		}
		if (cur && cost[cur - 1] == -1) { //MINUS
			cost[cur - 1] = nxt_cost;
			que.push(cur-1);
		}
		if (cur+1 < 1024 && cost[cur + 1] == -1) { //ADD
			cost[cur + 1] = nxt_cost;
			que.push(cur + 1);
		}
	}
	return cost[purpose];
}

int main() {
	string s;
	string p;
	cin >> s >> p;
	for (int i = 0; i < s.length(); i++) {
		start = (start << 1) | (s[i]-'0' & 1);
	}
	for (int i = 0; i < p.length(); i++) {
		purpose = (purpose << 1) | (p[i]-'0' & 1);
	}
	
	cout << BFS();
	
}