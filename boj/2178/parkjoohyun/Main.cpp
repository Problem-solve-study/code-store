//Memory 2076KB
//Time 0ms

#include <iostream>
#include <string>
#include <queue>
#include <utility>

using namespace std;

int map_x;
int map_y;
char map[101][101];
int cost[101][101];
queue <pair< int, int >> visit;

bool check_pos(int y, int x) {
	if (x < 0 || x >= map_x || y < 0 || y >= map_y) return false;
	if (map[y][x] == '1' && cost[y][x] == -1) return true;
	return false;
}

int BFS() {
	for (int i = 0; i < map_y; i++) {
		for (int j = 0; j < map_x; j++) {
			cost[i][j] = -1;
		}
	}
	cost[0][0] = 1;
	visit.push(make_pair(0, 0));

	while (!visit.empty()) {
		int visit_x = visit.front().first;
		int visit_y = visit.front().second;
		visit.pop();
		int nxt_cost = cost[visit_y][visit_x] + 1;
		if (check_pos(visit_y - 1, visit_x)) {
			visit.push(make_pair(visit_x, visit_y - 1));
			cost[visit_y - 1][visit_x] = nxt_cost;
		}
		if (check_pos(visit_y, visit_x - 1)) {
			visit.push(make_pair(visit_x - 1, visit_y));
			cost[visit_y][visit_x - 1] = nxt_cost;
		}
		if (check_pos(visit_y, visit_x + 1)) {
			visit.push(make_pair(visit_x + 1, visit_y));
			cost[visit_y][visit_x + 1] = nxt_cost;
		}
		if (check_pos(visit_y + 1, visit_x)) {
			visit.push(make_pair(visit_x, visit_y + 1));
			cost[visit_y + 1][visit_x] = nxt_cost;
		}

		if (cost[map_y - 1][map_x - 1] != -1)
			return cost[map_y - 1][map_x - 1];
	}
	return cost[map_y - 1][map_x - 1];
}



int main() {
	cin >> map_y >> map_x;
	for (int i = 0; i < map_y; i++) {
		cin >> map[i];
	}

	cout << BFS();

}