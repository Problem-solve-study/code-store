//MEMORY : 2020KB
//TIME : 0MS

#include <iostream>

using namespace std;


int main() {
	unsigned int monkey;
	unsigned int puppy;
	cin >> monkey >> puppy;
	unsigned int diff = puppy - monkey;
	if (diff == 0) {
		cout << 0;
		return 0;
	}

	unsigned int num = 1;
	while (num * num < diff) {
		num++;
	}

	if (num * num - num < diff)
		cout << num * 2 - 1;
	else
		cout << num * 2 - 2;
	return 0;
}