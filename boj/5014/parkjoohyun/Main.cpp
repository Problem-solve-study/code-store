//Memory : 5928kb
//Time : 12ms

#include <iostream>
#include <queue>
using namespace std;

int floors;
int goal;
int start;
int up;
int down;
int visited[1'000'001];

void input() {
	cin >> floors >> start >> goal >> up >> down;
}

bool solution() {
	queue<int> visit;
	visit.push(start);
	visited[start] = 1;
	while (!visit.empty()) {
		int cur = visit.front();
		visit.pop();
		if (cur - down > 0 && !visited[cur-down]) {
			visit.push(cur - down);
			visited[cur - down] = visited[cur]+1;
		}
		if (cur + up <= floors && !visited[cur+up]) {
			visit.push(cur + up);
			visited[cur + up] = visited[cur] + 1;
		}

		if (visited[goal])
			return true;
	}
	return false;
}

int main() {
	input();

	if (solution())
		cout << visited[goal]-1;
	else
		cout << "use the stairs";
}