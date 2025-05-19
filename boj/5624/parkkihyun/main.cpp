// A[i] + A[j] + A[k] = A[idx] 일때, A[i] + A[j] = A[idx] - A[k] 임을 이용하는 문제

// 다만, 미리 계산해서 하면 안 됨. 왜냐하면 idx보다 앞에있는 수로만 i, j, k를 구성해야 함
// 그래서 idx가 이동할 때마다 두 수의 합을 구해놓은 set을 갱신시켰음
// 그렇다면 idx이상인 수들의 조합은 만들어지지 않음
#include <bits/stdc++.h>
#include <unordered_set>
using namespace std;

int N;
int A[5050];

unordered_set<int> nums;

bool isGood(int idx) {
	int target = A[idx];

	for (int i = 0; i < idx; i++) nums.insert(A[i] + A[idx - 1]);

	for (int k = 0; k < idx; k++) {
		if (nums.find(target - A[k]) != nums.end()) return 1;
	}

	return 0;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N;
	for (int i = 0; i < N; i++) cin >> A[i];

	int cnt = 0;
	for (int i = 1; i < N; i++) {
		int target = A[i];

		cnt += isGood(i);
	}

	cout << cnt;

	return 0;
}
