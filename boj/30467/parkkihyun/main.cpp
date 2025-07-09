// 세그먼트 트리를 2번 사용해서 풀었습니다.
// 시간복잡도를 생각하지 않고 문제를 푼다면, O(N*S)이 걸립니다.
// 이때, 배열 S의 길이만큼 슬라이딩 윈도우해서 푼다면 시간복잡도를 O(N)으로 줄일 수 있을 것입니다.

// 슬라이딩 윈도우를 이용해 풀이를 할 때 어떤 정보가 필요한지 생각해보면,
// 1. 배열에서 임의의 i 인덱스를 시작으로, S 길이만큼의 임의 배열 속에서, 자신의 값보다 큰 원소의 개수
// 2. 배열에서 임의의 i 인덱스를 끝으로, S 길이만큼의 임의 배열 속에서, 자신의 값보다 작은 원소의 개수

// 각각의 정보를 세그먼트 트리를 Update 하면서 구할 수 있습니다.
// 1번 정보를 얻기 위하여, (A_i, 10억] 의 합을 구하면 됩니다.
// 2번 정보를 얻기 위하여, [0, A_i) 의 합을 구하면 됩니다.
// 1번 정보를 미리 세그먼트 트리를 이용해 구해둔 뒤, 시작부터 슬라이딩 윈도우로 구하면 됩니다.

// 주의점은, 값은 [1, 10억]이지만, 실제 원소의 개수가 10만이므로 좌표 압축을 해야합니다.
#include <iostream>
#include <algorithm>
#include <numeric>
#include <vector>
#include <queue>
#include <tuple>
#include <unordered_map>
using namespace std;

using pii = pair<long long, long long>;

struct SegTree {
	vector<long long> tree;

	SegTree(long long N) {
		long long S = 1;
		while (N > S) S <<= 1;

		tree.assign(S << 1, 0);
	}

	void update(long long node, long long start, long long end, long long idx, long long val) {
		if (start == end) {
			tree[node] += val;
			return;
		}

		long long mid = (start + end) >> 1;

		if (idx <= mid) update(node << 1, start, mid, idx, val);
		else update(node << 1 | 1, mid + 1, end, idx, val);

		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}

	long long get(long long node, long long start, long long end, long long l, long long r) {
		if (r < start || end < l) return 0;
		if (l <= start && end <= r) return tree[node];

		long long mid = (start + end) >> 1;

		return get(node << 1, start, mid, l, r) + get(node << 1 | 1, mid + 1, end, l, r);
	}
};

long long N, S;
vector<pii> arr;
vector<long long> comp, dp;
unordered_map<long long, long long> idMap;

// 좌표 압축 함수
void compress() {
	for (long long i = 0; i < N; i++) {
		comp[i] = arr[i].first;
	}

	sort(comp.begin(), comp.end());
	comp.erase(unique(comp.begin(), comp.end()), comp.end());

	long long id = 0;
	for (const long long& val : comp) {
		idMap[val] = ++id;
	}
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> S;
	
	SegTree tree(N + 1);

	arr.resize(N);
	comp.resize(N);
	dp.resize(N);

	for (long long i = 0; i < N; i++) {
		long long A; cin >> A;
		arr[i] = { A, i + 1 };
	}

  // 좌표 압축
	compress();

	// S 길이에서 본인보다 큰 것이 몇 개 있는지 체크
	long long cnt = 0;
	for (long long i = N - 1; i >= 0; i--) {
		long long val = idMap[arr[i].first];

		if (cnt++ == S) {
			long long pVal = idMap[arr[i + S].first];
			tree.update(1, 0, N, pVal, -1);
			cnt--;
		}

    // i번 인덱스를 시작으로 배열 s에 자신보다 큰 원소가 몇 개 있는지 저장
		dp[i] = tree.get(1, 0, N, val + 1, N);
		tree.update(1, 0, N, val, 1);
	}

  // 트리 초기화
	tree = SegTree(N + 1);
	cnt = 0;
	long long ans = 0, tmp = 0;
	for (long long i = 0; i < N; i++) {
		long long val = idMap[arr[i].first];

		if (cnt++ == S) {
			long long pVal = idMap[arr[i - S].first];
			tree.update(1, 0, N, pVal, -1);
      // 슬라이딩 윈도우에서 사라질 때, 사라지는 원소의 dp 값을 넣어주면 됨
			tmp -= dp[i - S];
			cnt--;
		}

		tmp += tree.get(1, 0, N, 0, val - 1);
		tree.update(1, 0, N, val, 1);
		ans = max(ans, tmp);
	}

	cout << ans;

	return 0;
}
