#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int n, m;
	cin >> n >> m;
	long long res = 0;
	long long* array = new long long[n];

	// 배열 입력
	for (int i = 0; i < n; i++) {
		cin >> array[i];
	}

	// 배열을 오름차순으로 정렬
	

	// m번 합체 연산 수행
	for(int i =0;i<m;i++) {
		sort(array, array + n);
		// 두 가장 작은 값 더하기
		long long sum = array[0] + array[1];

		// 두 자리에 더한 값을 대입
		array[0] = sum;
		array[1] = sum;
	}

	// 배열의 모든 값을 합산
	for (int i = 0; i < n; i++) {
		res += array[i];
	}

	// 결과 출력
	cout << res;

	// 동적 메모리 해제
	delete[] array;

	return 0;
}