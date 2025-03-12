//Memory : 2296kb
//Time : 728ms

#include <iostream>
#include <algorithm>
#include <string>
#include <set>
#include <vector>
/*
구간 성분 = 알파벳 순서와 상관없이 종류와 개수가 동일


구간의 알파벳 수가 같으면 동일한 구간 성분 = 알파벳에 대한 hash값이 동일
hash code
a	b	c	d	...	z
2	3	5	7	... 26번째 prime -> 최대 해쉬 값 = 650*1'500 = 975'000


hash 값이 같을 경우, 저장해놓은 해당 구간의 알파벳 개수 비교
*/

using namespace std;

#define MOD 9876543221

typedef unsigned long long ull;
typedef pair<ull, vector<int>> hashcode;
string s1, s2;
ull char_hash[26] = { 2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101 };

set<hashcode> hash_store;


void input() {
	cin >> s1 >> s2;

	if (s1.size() > s2.size()) {
		string temp = s2;
		s2 = s1;
		s1 = temp;
	}
}

int getMaxLen() {
	int len1 = s1.size();
	int len2 = s2.size();
	int max_len = 0;
	vector<int>freq1(26, 0);
	vector<int>freq2(26, 0);
	for (int i = 0; i < len1; i++) {
		freq1[s1[i] - 'a']++;
	}
	for (int i = 0; i < len2; i++) {
		freq2[s2[i] - 'a']++;
	}
	for (int i = 0; i < 26; i++) {
		max_len += min(freq1[i], freq2[i]);
	}
	return max_len;
}

void saveHashCode(int len) {
	int sz = s1.size();
	ull hash = 0;
	vector<int>alpha(26, 0);
	for (int i = 0; i < len; i++) {
		
		hash += char_hash[s1[i] - 'a'];
		alpha[s1[i] - 'a']++;
	}
	hash_store.insert({ hash, alpha });

	for (int i = len; i < sz; i++) {
		vector<int>alpha2;
		hash -= char_hash[s1[i-len] - 'a'];
		hash += char_hash[s1[i] - 'a'];
		alpha[s1[i-len] - 'a']--;
		alpha[s1[i] - 'a']++;

		for (int i = 0; i < 26; i++) {
			alpha2.push_back(alpha[i]);
		}
		hash_store.insert({ hash, alpha2 });
	}
}

bool checkHashCode(int len) {
	int sz = s2.size();
	ull hash = 0;
	vector<int>alpha(26, 0);
	for (int i = 0; i < len; i++) {

		hash += char_hash[s2[i] - 'a'];
		alpha[s2[i] - 'a']++;
	}
	if (hash_store.find({ hash, alpha }) != hash_store.end())
		return true;

	for (int i = len; i < sz; i++) {
		vector<int>alpha2;
		hash -= char_hash[s2[i - len] - 'a'];
		hash += char_hash[s2[i] - 'a'];
		alpha[s2[i - len] - 'a']--;
		alpha[s2[i] - 'a']++;

		for (int i = 0; i < 26; i++) {
			alpha2.push_back(alpha[i]);
		}
		if (hash_store.find({ hash, alpha2 }) != hash_store.end())
			return true;;
	}
	return false;
}

int solution() {
	int len = getMaxLen();
	for (int i = len; i >= 0; i--) {
		saveHashCode(i);
		if (checkHashCode(i))
			return i;
		hash_store.clear();
	}
	return 0;
}

int main() {
	input();
	int ret = solution();
	cout << ret;
}