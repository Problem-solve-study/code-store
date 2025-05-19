#include <bits/stdc++.h>
using namespace std;

using pii = pair<int, int>;

int dx[] = {-1, -1, -1, 0, 1, 1, 1, 0};
int dy[] = {-1, 0, 1, 1, 1, 0, -1, -1};

int N, M;
vector<vector<int>> board;

bool inBound(int x, int y) {
    return 0<=x&&x<N && 0<=y&&y<M;
}

int getDistance(int x, int y) {
    vector<vector<int>> dist(N, vector<int>(M, -1));
    queue<pii> q;

    dist[x][y] = 0;
    q.push({x, y});

    while (!q.empty()) {
        int cx, cy;
        tie(cx, cy) = q.front(); q.pop();

        if (board[cx][cy] == 1) {
            return dist[cx][cy];
        }

        for (int dir = 0; dir < 8; dir++) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];

            if (!inBound(nx, ny) || dist[nx][ny] != -1) continue;

            dist[nx][ny] = dist[cx][cy] + 1;
            q.push({nx, ny});
        }
    }

    return 0;
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N >> M;

    board.assign(N+1, vector<int>(M, 0));
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < M; y++) {
            cin >> board[x][y];
        }
    }

    int ans = 0;
    for (int x = 0; x < N; x++) {
        for (int y = 0; y < M; y++) {
            int distance = getDistance(x, y);
            ans = max(ans, distance);
        }
    }

    cout << ans;

    return 0;
}
