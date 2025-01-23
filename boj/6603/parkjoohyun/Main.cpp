#include <iostream>

using namespace std;

//DFS 함수
// nums : 입력받은 수 배열 / lotto : 현재까지 선택한 수 / start : 현재 index / end : 배열의 끝 index / cnt : 현재까지 고른 갯수
void DFS(int* nums, int* lotto, int start, int end, int cnt) { 
	if (cnt == 6) {
		for (int i = 0; i < 6; i++)
			printf("%d ", lotto[i]);
		printf("\n");
		return;
	}
	// 사전 순서대로 고르기
	for (int i = start; i < end; i++) {
		lotto[cnt] = nums[i];
		DFS(nums, lotto, i + 1, end, cnt + 1);
	}
}


int main() {
	int* nums;
	int* lotto = new int[6];
	int num_size;
	while(true) {
		scanf_s("%d", &num_size);
		if (num_size == 0) break;
		nums = new int[num_size];

		for (int i = 0; i < num_size; i++) {
			scanf_s("%d", &nums[i]);
		}
		DFS(nums, lotto, 0, num_size, 0);
		printf("\n");
	}
}