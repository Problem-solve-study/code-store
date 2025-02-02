#include <iostream>
#include <map>
#include <string>
using namespace std;

int main() {
	map<string, int> dict;
	string name;
	int cnt = 0;

	while (getline(cin, name)) {
		cnt++;
		dict[name]++;
	}

	for (auto it = dict.begin(); it != dict.end(); it++) {
		printf("%s %.4f\n", it->first.c_str(), (float)it->second * 100 / cnt);
	}
}