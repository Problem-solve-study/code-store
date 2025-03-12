//Memory : 2020kb
//Time : 56ms

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
long long sum;
int max_ = 0;
void input() {
	cin >> N;
	int x;
	
	for (int i = 0; i < N; i++) {
		cin >> x;
		sum += x;
		max_ = max(max_, x);
	}
}

void solution() {
	int cnt = 0;
	sum -= max_;
	if (sum < max_) {
		cout << sum * 2 + 1;
		return;
	}
	cout << sum + max_;
	
}

int main() {
	input();
	solution();
}