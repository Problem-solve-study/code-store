//Memory : 2412kb
//Time : 40ms

#include <iostream>

using namespace std;

int sz;
int testcase;
long long int res;


int main() {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);

	res = 0;
	cin >> sz >> testcase;
	int* number = new int[sz];
	int n;
	for (int i = 0; i < sz; i++) {
		cin >> n;
		res += n;
		number[i] = res;
	}
	int start, end;
	for (int i = 0; i < testcase; i++) {
		cin >> start >> end;
		if (start == 1) {
			cout << number[end - 1] << '\n';
		}
		else {
			cout << number[end - 1] - number[start - 2] << '\n';
		}
	}
}