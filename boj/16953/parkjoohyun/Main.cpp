//Memory : 2020kb
//Time : 0ms

#include <iostream>
#include <algorithm>

using namespace std;


int A, B;

void input() {
	cin >> A >> B;
}

int solution() {
	int result = 1;
	while (true) {
		if (A == B)
			break;
		if (A > B)
			return -1;
		else if (B % 2 == 0)
			B /= 2;
		else if (B % 10 == 1)
			B = (B - 1) / 10;
		else
			return -1;
		result++;
	}

	return result;
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	cout << solution() << "\n";
}