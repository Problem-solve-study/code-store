#include <bits/stdc++.h>
using namespace std;

// 방향을 지정하고 그 방향으로만 같은 글자일 때, DFS 탐색 종료

int N;
vector<vector<char>> board;

int dx[] = {0, 1, 1, 1};
int dy[] = {1, 1, 0, -1};

bool inBound(int x, int y) {
    return 0<=x&&x<N && 0<=y&&y<N;
}

bool win(int x, int y, int depth, int dir) {
    if (depth == 3) {
        return true;
    }

    int nx = x + dx[dir];
    int ny = y + dy[dir];

    if (!inBound(nx, ny) || board[nx][ny] != board[x][y]) return false;

    return win(nx, ny, depth+1, dir);
}

void solve() {
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < N; y++) {
            if (board[x][y] != '.') {
                for (int dir = 0; dir < 4; dir++) {
                    if (win(x, y, 1, dir)) {
                        cout << board[x][y];
                        return;
                    }
                }
            }
        }
    }
    cout << "ongoing";
    return;
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N;
    board.assign(N, vector<char>(N));

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> board[i][j];
        }
    }

    solve();

    return 0;
}
