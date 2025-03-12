//Memory : 33288kb
//Time : 92ms

/*
시작 조각과 끝 조각이 정해지면 상대방이 먹을 조각은 고정 => 내가 먹을 조각만 지정
시작 조각과 끝 조각이 같은 경우, 결과도 동일
*/

#include <iostream>
#include <algorithm>

using namespace std;

int N;
long long pieces[2000];
long long dp[2000][2000]; //Start Piece - End Piece
void input() {
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> pieces[i];
	}
}

int rightPiece(int idx) {
	return (idx + 1) % N;
}

int leftPiece(int idx) {
	return (idx + N -1) % N;
}

long long takePiece(int start , int end, int cnt) {
	int leftIdx = leftPiece(start);
	int rightIdx = rightPiece(end);
	if (cnt%2 == 0) {
		if (start == rightIdx) return 0;
		if (pieces[leftIdx] < pieces[rightIdx])
			return takePiece(start, rightIdx, cnt + 1);
		else return takePiece(leftIdx, end, cnt + 1);
	}
	else {
		if (start == rightIdx) {
			return dp[start][end] = 0;
		}
		if (dp[start][end] >= 0)
			return dp[start][end];

		return dp[start][end] = max(takePiece(start, rightIdx, cnt + 1)+pieces[rightIdx], takePiece(leftIdx, end, cnt + 1)+pieces[leftIdx]);
	}
}


void solution() {
	long long ret = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			dp[i][j] = -1;
		}
	}

	for (int i = 0; i < N; i++) {
		ret = max(ret, takePiece(i, i, 2) + pieces[i]);
	}

	cout << ret;
}

int main() {
	input();
	solution();
}