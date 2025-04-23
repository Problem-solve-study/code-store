#include <bits/stdc++.h>
using namespace std;

// 배열의 값: 인형에 대해 선호하는 사람의 수
// 근데 인덱스를 구해야하나?
// 필요없음. 그냥 값만 있으면 됨. 왜냐? 선호하느 사람의 표준편차이기 때문
// 근데 N이 500임. 슬라이딩 윈도우를 생각했는데
// 그냥 N^2하면 될듯

int N, K;
int arr[505];

// sIdx부터 eIdx까지의 평균을 구하는 함수
double getMean(int sIdx, int eIdx) {
	double sum = 0;
	for (int i = sIdx; i <= eIdx; i++) {
		sum += arr[i];
	}
	return sum / (double)(eIdx - sIdx + 1);
}

double getDistribute(int sIdx, int eIdx) {
	double m = getMean(sIdx, eIdx);

	double ret = 0;
	for (int i = sIdx; i <= eIdx; i++) {
		ret += pow((double)arr[i] - m, 2);
	}

	return sqrt(ret / (double) (eIdx - sIdx + 1));
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	cin >> N >> K;
	for (int i = 0; i < N; i++) cin >> arr[i];

	double ans = (double) DBL_MAX;
	for (int i = 0; i < N - K + 1; i++) {
		for (int j = i + K-1; j < N; j++) {
			ans = min(ans, getDistribute(i, j));
		}
	}

	cout << fixed << setprecision(11) << ans;

	return 0;
}
