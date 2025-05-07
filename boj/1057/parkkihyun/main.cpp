// 공통 조상을 찾는 문제인 줄 알았음
// 우선 서로를 만나기전에는 무조건 이기므로 둘은 무조건 만나니 -1이 나오는 경우는 없음
// 그래서 공통 조상 찾는 코드로 접근했다가 틀렸습니다. 만나고 현수 풀이를 봄

// 이렇게 간단한 것이었다니.
#include <iostream>
#include <algorithm>
using namespace std;

int N, L, R;

int ans = 1;

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> L >> R;
	L--; R--;

	while ((L >>= 1) != (R >>= 1)) {
		ans++;
	}
	
	cout << ans;

	return 0;
}
