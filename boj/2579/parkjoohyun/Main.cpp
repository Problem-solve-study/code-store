//Memory : 2020kb
//Time : 0ms

#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	int scores[301];
	int stairs[301];
	
	int n, score;
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> score;
		stairs[i] = score;
	}

	scores[1] = stairs[1];
	scores[2] = scores[1] + stairs[2];
	scores[3] = max(scores[1] + stairs[3], stairs[2] + stairs[3]);

	for (int i = 4; i <= n; i++) {
		scores[i] = max(scores[i - 2] + stairs[i], scores[i - 3] + stairs[i - 1] + stairs[i]);
	}

	cout << scores[n];
}