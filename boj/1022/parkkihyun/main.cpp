// 규칙에 맞게 실제로 배열의 인덱스로 회전하다가
// 원하는 공간의 인덱스가 되면 배열을 직접 채우는 방식으로 문제를 해결했습니다.
#include <bits/stdc++.h>
using namespace std;

int dx[] = {0, -1, 0, 1};
int dy[] = {1, 0, -1, 0};

int r1, c1, r2, c2;
vector<vector<int>> board;

bool inBound(int x, int y) {
    return r1<=x&&x<=r2 && c1<=y&&y<=c2;
}

void init() {
    int x = 0, y = 0;
    int fillMax = (r2 - r1 + 1) * (c2 - c1 + 1);

    int number = 1, moveMax = 1, fillCnt = 0, dir = 0;

    while (true) {
        for (int cnt = 0; cnt < 2; cnt++) {
            for (int move = 0; move < moveMax; move++) {                
                if (inBound(x, y)) {
                    board[x-r1][y-c1] = number;
                    if (++fillCnt == fillMax) return;
                }
                number++;
                x += dx[dir];
                y += dy[dir];
            }

            dir = (dir + 1) % 4;
        }

        moveMax++;
    }
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> r1 >> c1 >> r2 >> c2;
    
    board.resize(r2-r1+1, vector<int> (c2-c1+1));

    init();

    int maxNumber = 0;
    for (int i = 0; i < r2-r1+1; i++) {
        for (int j = 0; j < c2-c1+1; j++) {
            maxNumber = max(maxNumber, board[i][j]);
        }
    }

    int cnt = 0;
    while (maxNumber > 0) {
        cnt++;
        maxNumber /= 10;
    }

    for (int i = 0; i < r2-r1+1; i++) {
        for (int j = 0; j < c2-c1+1; j++) {
            cout << setw(cnt) << board[i][j] << ' ';
        }
        cout << '\n';
    }

    return 0;
}
