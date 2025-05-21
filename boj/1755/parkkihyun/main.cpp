#include <bits/stdc++.h>
using namespace std;

string toEnglish[10] = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
vector<pair<string, int>> dict;

bool cmp(pair<string, int>& p1, pair<string, int>& p2) {
	return p1.first < p2.first;
}

string makeEnglish(int i) {
	string ret = "";

	stack<string> s;
	while (i > 0) {
		s.push(toEnglish[i % 10]);
		i /= 10;
	}

	ret += s.top(); s.pop();

	while (!s.empty()) {
		ret += " ";
		ret += s.top(); s.pop();
	}

	return ret;
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int M, N; cin >> M >> N;

	for (int i = M; i <= N; i++) {
		string english = makeEnglish(i);

		dict.push_back({ english, i });
	}

	sort(dict.begin(), dict.end(), cmp);

	for (int i = 1; i <= dict.size(); i++) {
		cout << dict[i-1].second << ' ';
		if (i % 10 == 0) cout << '\n';
	}

	return 0;
}
