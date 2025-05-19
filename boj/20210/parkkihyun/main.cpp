// 진짜 빡.구.현 문제
// 처음에는 정렬도 짜고 있었지만 곧 이어 그럴 필요는 없다는 걸 깨닫고
// 그냥 하라는대로 짰음

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

using ll = long long;

char toUpper(char c) {
	if ('a' <= c && c <= 'z') c -= abs('a' - 'A');
	return c;
}

int numberCompare(string& num1, string& num2) {
	auto it1 = num1.begin();
	auto it2 = num2.begin();

	// 앞자리 0은 무시
	while (it1 != num1.end() && *it1 == '0') it1++;
	while (it2 != num2.end() && *it2 == '0') it2++;

	string newNum1 = "";
	string newNum2 = "";

	while (it1 != num1.end()) newNum1 += *(it1++);
	while (it2 != num2.end()) newNum2 += *(it2++);

	// 둘의 사이즈가 같다면, 
	if (newNum1.size() == newNum2.size()) {
		for (int i = 0; i < newNum1.size(); i++) {
			// num1 > num2
			if (newNum1[i] > newNum2[i]) return -1;

			// num1 < num2
			if (newNum1[i] < newNum2[i]) return 1;
		}

		// 그럼 0의 개수가 많은 것이 더 크다.
		if (num1.size() == num2.size()) return 0;
		else if (num1.size() > num2.size()) return -1;
		return 1;
	}
	// 둘의 사이즈가 다르다면,

	if (newNum1.size() > newNum2.size()) return -1;
	return 1;
}

bool isNumber(char& c) {
	return '0' <= c && c <= '9';
}

bool cmp(string& s1, string& s2) {
	auto it1 = s1.begin();
	auto it2 = s2.begin();

	while (it1 != s1.end() && it2 != s2.end()) {
		// 둘 다 숫자일 경우
		if (isNumber(*it1) && isNumber(*it2)) {
			string num1 = "";
			string num2 = "";

			while (it1 != s1.end() && isNumber(*it1)) num1 += *(it1++);
			while (it2 != s2.end() && isNumber(*it2)) num2 += *(it2++);

			int result = numberCompare(num1, num2);

			// s1이 더 큼
			if (result == -1) {
				return false;
			}

			if (result == 1) {
				return true;
			}
		}
		else if (isNumber(*it1)) {
			return true;
		}
		else if (isNumber(*it2)) {
			return false;
		}
		// 둘 다 문자인 경우
		else {
			// 같은 알파벳일 경우
			if (toUpper(*it1) == toUpper(*it2)) {
				// 앞의 글자가 소문자일 경우
				if (*it1 != *it2) {
					return *it1 < *it2;
				}
			}
			// 다른 문자인 경우
			else {
				if (toUpper(*it1) < toUpper(*it2)) {
					return true;
				}
				else {
					return false;
				}
			}

			it1++;
			it2++;
		}
	}

	return s1.size() < s2.size();
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int N; cin >> N;

	vector<string> v;
	while (N--) {
		string s; cin >> s;
		v.push_back(s);
	}

	sort(v.begin(), v.end(), cmp);

	for (auto& s : v) {
		cout << s << '\n';
	}

	return 0;
}
