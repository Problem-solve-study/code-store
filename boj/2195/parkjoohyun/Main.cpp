//Memory : 2024kb
//Time : 0ms

#include <iostream>
#include <string>
#include <algorithm>
using namespace std;



string S;
string P;

void input() {
	cin >> S;
	cin >> P;
}

void solution() {
	int cnt = 0;
	for (int i = 0; i < P.length();) {
		int max_len = 0;
		for (int j = 0; j < S.length(); j++) {
			int tmp = 0;
			while (S[j + tmp] == P[i + tmp])
				tmp++;
			max_len = max(max_len, tmp);
		}
		i += max_len;
		cnt++;
	}
	cout << cnt;
}

int main() {
	input();
	solution();
	
}