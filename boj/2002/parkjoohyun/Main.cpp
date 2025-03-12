//Memory : 2024kb
//Time : 0ms

#include <iostream>
#include <string>
#include <list>

using namespace std;

list<string> enter;
int N;

void input() {
	cin >> N;

	string c;
	for (int i = 0; i < N; i++) {
		cin >> c;
		enter.push_back(c);
	}
	
}

void solution() {
	string c;
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		cin >> c;
		if (c == enter.front()) {
			enter.pop_front();
		}
		else {
			cnt++;
			enter.remove(c);
		}
	}
	cout << cnt;
}

int main() {
	input();
	solution();
}