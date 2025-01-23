#include <iostream>

using namespace std;

//DFS �Լ�
// nums : �Է¹��� �� �迭 / lotto : ������� ������ �� / start : ���� index / end : �迭�� �� index / cnt : ������� �� ����
void DFS(int* nums, int* lotto, int start, int end, int cnt) { 
	if (cnt == 6) {
		for (int i = 0; i < 6; i++)
			printf("%d ", lotto[i]);
		printf("\n");
		return;
	}
	// ���� ������� ����
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