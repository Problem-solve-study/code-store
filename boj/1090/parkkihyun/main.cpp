// N이 50이라 브루트포스로 접근

// 선택된 K개의 점들이 모이는 최단거리를 위한 점은 선택된 점들 중 하나임
// 이를 구하기 위해 처음에는 X축으로 정사하여 단순화하여 문제를 접근

// 도출과정

// 아이디어 1
// 처음에는 좌표들의 평균인 줄 알았음
// 그러나 1번 예제에서 맨 오른쪽 점을 오른쪽으로 계속 밀다보니 평균이 아니라 두 선분이 교차하는 점이라고 생각
// 그러나 이 상황에서 맨 위의 점을 위로 계속 밀다보니 모순 발생
// 그래서 선택된 좌표들의 (X축의 중간 좌표) (Y축의 중간 좌표)가 해임을 발견

// 아이디어 2
// 중간 좌표가 최적해임을 발견했으니 이제 좌표들을 선택해야 함
// 처음에는 정렬해서 앞에서부터 스위핑 생각
// 그러나, (1, 10000), (2, 1), (3, 9000), (4, 2), (5, 3)의 경우 [2, 4, 5]점을 잇는 것이 3개를 고르는 최적의 경우
// 따라서 모순 발생

// 아이디어 3
// 체커가 움직이는 최소 횟수는 결국 체커가 이동하는 맨헤튼 거리가 됨
// 정답이 될 수 있는 모든 좌표는 (X축의 중간 좌표) (Y축의 중간 좌표)임
// 따라서 정답이 될 수 있는 모든 좌표에서 모든 체커까지의 거리를 구하는 방향으로 접근하기로 생각
// 시간 복잡도는 50 * 50 * 50 * log 50(정답이 될 수 있는 좌표와 모든 좌표까지의 거리 + 정렬) 맞는지 모르겠음
// 쌉. 가. 능. 하다고 판단 바로 코드 작성

// 정렬 후, 누적합 형식으로 ans 테이블을 갱신해야하는데 그 이유는
// dist 배열의 index의 합이 곧 (index+1)개의 좌표와 최단 거리가 되기 때문이다.

#include <bits/stdc++.h>
using namespace std;

#define INF 1e8
using Point = pair<int, int>;

int N;
vector<Point> points;
vector<int> ans;

// 두 점간의 맨헤튼 거리를 구하는 함수
int getDistance(Point& p1, Point& p2) {
    return abs(p1.first - p2.first) + abs(p1.second - p2.second);
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N;
    points.resize(N);
    ans.resize(N+1, INF);

    // 좌표의 X좌표, Y좌표를 저장
    for (int i = 0; i < N; i++) {
        cin >> points[i].first >> points[i].second;
    }

    // X좌표들만을 뽑아오기 위한 for loop
    for (Point& canX : points) {
        // Y좌표들만을 뽑아오기 위한 for loop
        for (Point& canY : points) {
            // 정답이 될 수 있는 좌표 하나 생성
            Point nPoint = {canX.first, canY.second};

            // nPoint에서 모든 체커들까지의 거리를 구하는 dist ArrayList
            vector<int> dist;
            for (Point& point : points) {
                // nPoint와 point까지의 거리를 구해서 dist에 추가해준다.
                dist.push_back(getDistance(point, nPoint));
            }

            // 체커의 최소 이동 거리를 찾아야하니 오름차순으로 정렬
            sort(dist.begin(), dist.end());

            // 누적합을 위한 sum
            int sum = 0;
            for (int i = 0; i < dist.size(); i++) {
                sum += dist[i];

                // n-1Point에서 최소라고 생각했는데 nPoint에서 k개를 선택했을 때, 최소가 된다면 그 값으로 갱신
                ans[i+1] = min(ans[i+1], sum);
            }
        }
    }

    for (int i = 1; i <= N; i++) {
        cout << ans[i] << ' ';
    }

    return 0;
}
