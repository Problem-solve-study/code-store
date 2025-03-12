//Memory : 2424kb
//Time : 0ms

#include <iostream>
#include <unordered_set>
#include <algorithm>
#include<string>

using namespace std;

int cards[10];
int N, K;
unordered_set<string> res;
vector<int> cases;
bool visit[10];

void input() {
	cin >> N;
	cin >> K;
	for (int i = 0; i < N; i++) {
		cin >> cards[i];
	}
}

void dfs() {
	if (cases.size() == K) {
		string result = "";
		for (int i = 0; i < K; i++) {
			result += to_string(cases[i]);
		}
		res.insert(result);
		return;
	}
	for (int i = 0; i < N; i++)
	{
		if (visit[i])continue;
		visit[i] = true;
		cases.push_back(cards[i]);
		dfs();
		cases.pop_back();
		visit[i] = false;
	}

}
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	dfs();
	cout << res.size();

}