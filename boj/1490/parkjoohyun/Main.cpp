//Memory : 2024kb
//Time : 0ms

#include <iostream>
#include <set>

using namespace std;

long long N;
set <int> elements;

void input() {
	cin >> N;
	int temp = N;
	while (temp) {
		elements.insert(temp % 10);
		temp /= 10;
	}
}

int GCD(int a, int b) {
	if (b == 0) return a;
	else return GCD(b, a%b);
}

int LCM(int a, int b) {
	return a * b / GCD(a, b);
}

long long getAnswer(long long lcm, long long expo) {
	long long num = N * expo;
	for (int i = 0; i < expo; i++) {
		long long cnd = num + i;
		if (cnd % lcm == 0)
			return cnd;
	}
	return getAnswer(lcm, expo * 10);
}



void solution() {
	long long lcm =1;
	for(int element : elements){
		if (element == 0) continue;
		lcm = LCM(lcm, element);
	}
	if (N % lcm == 0) {
		cout << N;
		return;
	}

	long long ans = getAnswer(lcm, 10);
	cout << ans;
	return;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	input();
	solution();

	return 0;
}