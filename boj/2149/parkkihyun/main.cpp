// 암호문을 만들 때, 세로로 문자열을 이어붙이는 것을 놓쳐서 오래 걸렸던 문제

#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

vector<pair<char, int>> key;
vector<vector<char>> secret, plain;

bool cmp(pair<char, int>& p1, pair<char, int>& p2) {
	if (p1.first == p2.first) return p1.second < p2.second;
	return p1.first < p2.first;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	string line;

	cin >> line;
	for (int i = 0; i < (int)line.length(); i++) {
		key.push_back({ line[i], i });
	}

	// 암호문 정렬
	sort(key.begin(), key.end());

	cin >> line;
	secret.assign((int)line.length() / (int)key.size(), vector<char>((int)key.size()));
	plain.assign((int)line.length() / (int)key.size(), vector<char>((int)key.size()));

	auto it = line.begin();
	for (int j = 0; j < (int)key.size(); j++) {
		for (int i = 0; i < (int)line.length() / (int)key.size(); i++) {
			secret[i][j] = *it++;
		}
	}

	for (int i = 0; i < (int)line.length() / (int)key.size(); i++) {
		for (int j = 0; j < (int)key.size(); j++) {
			int idx = key[j].second;
			plain[i][idx] = secret[i][j];
		}
	}

	for (int i = 0; i < (int)line.length() / (int)key.size(); i++) {
		for (int j = 0; j < (int)key.size(); j++) {
			cout << plain[i][j];
		}
	}

	return 0;
}
