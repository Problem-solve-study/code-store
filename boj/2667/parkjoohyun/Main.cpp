//Memory 2024KB
//Time 0ms

#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

class position {
public:
	int x;
	int y;
	position(int y, int x) {
		this->x = x;
		this->y = y;
	}
};

int N;
vector<int> apartments;
char map[25][25];
bool visit[25][25];
int dir_x[] = { 0,0,1,-1 };
int dir_y[] = { 1,-1,0,0 };

bool check_pos(int y, int x) {
	if (y >= 0 && x >= 0 && y < N && x < N)
		return true;
	return false;
}

int bfs(int y, int x) {
	int cnt = 0;
	queue<position> poses;
	poses.push(position(y, x));
	visit[y][x] = true;
	while (!poses.empty()) {
		position pos = poses.front();
		poses.pop();
		cnt++;
		for (int i = 0; i < 4; i++) {
			int dx = pos.x + dir_x[i];
			int dy = pos.y + dir_y[i];
			if (!visit[dy][dx] && check_pos(dy, dx)) {
				visit[dy][dx] = true;
				if (map[dy][dx] == '1')
					poses.push(position(dy, dx));
			}
		}
	}
	return cnt;
}

void solution() {
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			if (!visit[y][x]) {
				visit[y][x] = true;
				if (map[y][x] == '1')
					apartments.push_back(bfs(y, x));
			}
				
		}
	}
	sort(apartments.begin(), apartments.end());
	int n = apartments.size();
	cout << n << "\n";
	for (int i = 0; i < n; i++)
		cout << apartments[i] << "\n";
}



int main() {
	
	cin >> N;
	for (int y = 0; y < N; y++) {
		cin >> map[y];
	}
	solution();
	
}