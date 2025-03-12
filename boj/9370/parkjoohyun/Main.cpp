//Memory : 41452kb
//Time : 740ms

#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <queue>
/*
교차로 2~2000	도로 1~50000	목적지 1~100
출발지 S		거쳐야하는 교차로 G,H

교차로를 지나서 가는 최단경로(G->H / H->G) == 출발지부터 목적지까지의 최단경로 -> 정답
*/

using namespace std;

typedef pair<int, int> Edge; // (정점, 거리)
typedef pair<int, int> Path; // (출발점, 도착점)

int pos_num, road_num, target_num;
int start, G, H;
map<int, vector<Edge>> graph;
map<int, vector<Path>> paths;
vector<int> targets;
int dist[2001];

bool checkPath(int pos);

void init() {
	graph.clear();
	paths.clear();
	targets.clear();
}

void input() {
	cin >> pos_num	>> road_num >> target_num;
	cin >> start	>> G		>> H;
	for (int i = 1; i <= pos_num; i++) {
		vector<Edge> e;
		graph[i] = e;
		vector<Path> p;
		paths[i] = p;
	}


	for (int i = 0; i < road_num; i++) {
		int u, v, c;
		cin >> u >> v >> c;
		graph[u].push_back({ v, c });
		graph[v].push_back({ u, c });
	}

	for (int i = 0; i < target_num; i++) {
		int a;
		cin >> a;
		targets.push_back(a);
	}
}


void find_path() {
	for (int i = 1; i <= 2000; i++) {
		dist[i] = INT32_MAX;
	}
	dist[start] = 0;
	priority_queue<int> q;

	q.push(start);
	while (!q.empty()) {
		int p = q.top();
		vector<Path> path = paths[p];
		q.pop();

		int len = graph[p].size();

		for (int i = 0; i < len; i++) {
			int cost = graph[p][i].second;
			int pos = graph[p][i].first;

			if (dist[pos] > dist[p] + cost || (dist[pos]== dist[p]+cost && !checkPath(pos))) {
				dist[pos] = dist[p] + cost;
				paths[pos] = path;
				paths[pos].push_back(Path(pos, p));
				q.push(pos);
			}
		}
	}
}

bool checkPath(int pos) {
	vector<Path> path = paths[pos];
	int len = path.size();
	for (int i = 0; i < len; i++) {
		if (path[i].first == G && path[i].second == H)
			return true;
		if (path[i].first == H && path[i].second == G)
			return true;
	}
	return false;
}


bool checkTarget(int t) {
	vector<Path> path = paths[t];
	int len = path.size();

	for (int i = 0; i < len; i++) {
		if (path[i].first == G && path[i].second == H)
			return true;
		if (path[i].first == H && path[i].second == G)
			return true;
	}
	return false;
}


void solution() {
	init();
	input();
	find_path();
	vector<int> answer;
	for (int i = 0; i < target_num; i++) {
		if (checkTarget(targets[i]))
			answer.push_back(targets[i]);
	}

	sort(answer.begin(), answer.end());
	for (int i = 0; i < answer.size(); i++) {
		cout << answer[i] << " ";
	}
	cout << "\n";
}

int main() {
	int T;
	cin >> T;

	for (int i = 0; i < T; i++) {
		solution();
	}
}