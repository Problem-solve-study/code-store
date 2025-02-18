//Memory 2148KB
//Time 0ms

#include <iostream>

using namespace std;

int dishes[30001]; //접시 수
int ate[3001]; //먹은 요리
int dish_num; //최대 접시 수
int dish_kind; //요리의 종류
int combo; // 연속으로 먹어야하는 요리의 수
int coupon; //쿠폰으로 제공되는 요시

int eat(int n) {
	int idx = dishes[(n + combo + dish_num) % dish_num];
	ate[idx]++;
	if (ate[idx] == 1) //이전에 먹은 음식이 아니면 +1
		return 1;
	return 0;
}

int eat_first(int n) {
	int idx = dishes[n];
	ate[idx]++;
	if (ate[idx] == 1) //이전에 먹은 음식이 아니면 +1
		return 1;
	return 0;
}

int not_eat(int n) {
	int idx = dishes[n];
	
	ate[idx]--;
	if (ate[idx] == 0) //이전에 먹은 음식이라면 1
		return -1;
	return 0;
}

void solution() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> dish_num >> dish_kind >> combo >> coupon;
	int cnt = 1;
	int max = 1;
	ate[coupon] = 999999;
	for (int i = 0; i < dish_num; i++) { 
		cin >> dishes[i];
	}
	for (int i = 0; i < combo; i++) {
		cnt += eat_first(i);
	}
	if (max < cnt)
		max = cnt;
	for (int i = 0; i < dish_num; i++) {
		cnt += (eat(i) + not_eat(i));
		if (max < cnt)
			max = cnt;
	}
	
	cout << max;
}

int main() {
	solution();
}