// Shout to @DoDoGaMaRu
#include <bits/stdc++.h>
using namespace std;

#define INF 100'000
using pii = pair<int, int>;

int dx[] = {-1, 0, 1, 0};
int dy[] = {0, 1, 0, -1};

int H, W;
vector<vector<char>> board;
vector<vector<vector<int>>> dist;

// 다익스트라를 위한 최소 힙
priority_queue<tuple<int, int, int, int>, vector<tuple<int, int, int, int>>, greater<tuple<int, int, int, int>>> pq;

bool inBound(int x, int y) {
    return 0<=x&&x<H+2 && 0<=y&&y<W+2;
}

int solve() {
    cin >> H >> W;
    board.assign(H+2, vector<char>(W+2));
    dist.assign(H+2, vector<vector<int>>(W+2, vector<int>(3, INF)));

    int who = 0;
    for (int x = 1; x <= H; x++) {
        for (int y = 1; y <= W; y++) {
            cin >> board[x][y];

            if (board[x][y] == '$') {
                dist[x][y][who] = 0;
                pq.push({ dist[x][y][who], x, y, who++ });
            }
        }
    }

    dist[0][0][2] = 0;
    pq.push({ dist[0][0][2], 0, 0, 2 });

    while (!pq.empty()) {
        int cd, cx, cy, i;
        tie(cd, cx, cy, i) = pq.top(); pq.pop();

        if (cd > dist[cx][cy][i]) continue;

        for (int dir = 0; dir < 4; dir++) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            int nd = cd;

            if (!inBound(nx, ny) || board[nx][ny] == '*') continue;

            if (board[nx][ny] == '#') nd++;

            if (dist[nx][ny][i] <= nd) continue;

            dist[nx][ny][i] = nd;
            pq.push({ nd, nx, ny, i });
        }
    }

    for (int x = 0; x < H+2; x++) {
        for (int y = 0; y < W+2; y++) {
            if (board[x][y] != '*') {
                dist[x][y][0] += dist[x][y][1] + dist[x][y][2];
                if (board[x][y] == '#') dist[x][y][0] -= 2;
            }
        }
    }
    
    int mn = INF;
    for (int x = 0; x < H+2; x++) {
        for (int y = 0; y < W+2; y++) {
            mn = min(mn, dist[x][y][0]);
        }
    }

    return mn;
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    int TC; cin >> TC;
    while (TC--) {
        cout << solve() << '\n';
    }

    return 0;
}
