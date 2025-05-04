#include <bits/stdc++.h>
using namespace std;

int N, M;
int board[10][10];

int ans = 1e9;

int dy[] = {-1, 0, 1};

void goMoon(int x, int y, int pDir, int cost) {
    if (y == 0 || y == M+1) return;
    if (x == N+1) {
        ans = min(ans, cost);
        return;
    }

    for (int dir = 0; dir < 3; dir++) {
        if (dir == pDir) continue;
        int nx = x + 1;
        int ny = y + dy[dir];

        goMoon(nx, ny, dir, cost + board[nx][ny]);
    }
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N >> M;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            cin >> board[i][j];
        }
    }

    for (int y = 1; y <= M; y++) goMoon(0, y, -1, 0);

    cout << ans;

    return 0;
}
