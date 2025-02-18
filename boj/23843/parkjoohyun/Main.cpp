//Memory 2024KB
//TIme 4ms

#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

vector<int> devices;
priority_queue<int, vector<int>, greater<int>> pq;

int main() {
	int N, M;
	int max_time = 0;
	int device;

	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		pq.push(0);
	}
	for (int i = 0; i < N; i++) {
		cin >> device;
		devices.push_back(device);
	}
	sort(devices.begin(), devices.end(), greater<int>());

	for (int i = 0; i < N; i++) {
		int time = pq.top() + devices[i];
		pq.pop();
		pq.push(time);

		max_time = max(time, max_time);
	}

	cout << max_time;
}