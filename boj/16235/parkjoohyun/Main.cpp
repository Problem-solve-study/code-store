//Memory : 2424kb
//Time : 164ms

#include <iostream>
#include <algorithm>
#include <vector>

#define endl "\n"
#define MAX 11

using namespace std;

int N, M, K;
int energy[MAX][MAX];
int adding[MAX][MAX];
vector<int> trees[MAX][MAX];

int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

// Function to take input
void Input() {
	cin >> N >> M >> K;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> adding[i][j];
			energy[i][j] = 5;
		}
	}
	for (int i = 0; i < M; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		trees[a][b].push_back(c);
	}
}

// Function to handle spring and summer
void SpringAndSummer() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (trees[i][j].empty()) continue;

			int Die_Tree_Energy = 0;
			vector<int> Temp;
			sort(trees[i][j].begin(), trees[i][j].end());

			for (int k = 0; k < trees[i][j].size(); k++) {
				int Age = trees[i][j][k];
				if (energy[i][j] >= Age) {
					energy[i][j] -= Age;
					Temp.push_back(Age + 1);
				}
				else {
					Die_Tree_Energy += (Age / 2);
				}
			}

			trees[i][j] = Temp;
			energy[i][j] += Die_Tree_Energy;
		}
	}
}

// Function to handle fall
void Fall() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			if (trees[i][j].empty()) continue;

			for (int k = 0; k < trees[i][j].size(); k++) {
				int Age = trees[i][j][k];
				if (Age % 5 == 0) {
					for (int a = 0; a < 8; a++) {
						int nx = i + dx[a];
						int ny = j + dy[a];
						if (nx >= 1 && ny >= 1 && nx <= N && ny <= N) {
							trees[nx][ny].push_back(1);
						}
					}
				}
			}
		}
	}
}

// Function to handle winter
void Winter() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			energy[i][j] += adding[i][j];
		}
	}
}

// Function to solve the problem
void Solution() {
	for (int i = 0; i < K; i++) {
		SpringAndSummer();
		Fall();
		Winter();
	}

	int Answer = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			Answer += trees[i][j].size();
		}
	}
	cout << Answer << endl;
}

// Main function
void Solve() {
	Input();
	Solution();
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//freopen("Input.txt", "r", stdin);
	Solve();

	return 0;
}
