# 체커와 굉장히 유사한 문제
# 체커가 플래티넘4인데 이 문제가 골드3...?
# 말이 안 된다고 생각합니다.

# 트램펄린이 설치될 수 있는 좌표 후보군을 모두 가져온 다음에
# 직접 설치해보며 별들위 위치와 비교해보면 됨
#include <iostream>
#include <set>
#include <vector>
#include <algorithm>
#include <tuple>
using namespace std;

using pii = pair<int, int>;

const int dx[] = { 1, 1, -1, -1 };
const int dy[] = { 1, -1, 1, -1 };

int N, M, L, K, X, Y;
vector<pii> star, point;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> M >> L >> K;
	for (int i = 0; i < K; i++) {
		cin >> X >> Y;
		star.push_back({ X, Y });
	}

	for (int i = 0; i < K; i++) {
		for (int j = 0; j < K; j++) {
			point.push_back({ star[i].first, star[j].second });
		}
	}

	int ans = 0;
	// 좌표 후보군에서 모두 비교해봄
	for (int i = 0; i < point.size(); i++) {
		int x, y;
		tie(x, y) = point[i];

		int cnt = 0;
		// i번째 별이 모서리일 경우를 확인
		// i번째 별이 왼쪽, 위 모서리에 있을 경우
		for (int j = 0; j < K; j++) {
			int nx, ny;
			tie(nx, ny) = star[j];

			if ((x <= nx and nx <= x + L) && (y <= ny and ny <= y + L)) cnt++;
		}
		ans = max(ans, cnt);
	}

	cout << K - ans;

	return 0;
}
