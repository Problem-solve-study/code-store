//Memory 2036KB
//Time 0ms

#include <iostream>
#include <math.h>
#include <vector>
#include <algorithm>

//   UCLID : A*B= GCD(A, B)*LCM(A, B)
//   LCM(A,B) * c = GCD(LCM(A,B),C) * L
//   c = GCD(LCM(A,B),C) * L / LCM(A,B)
using namespace std;

/// a > b
long long GCD(long long a, long long b) { //최대 공약수
	if (b == 0)
		return a;
	return GCD(b, a%b);
}
long long LCM(long long a, long long b) { //최소 공배수
	return a * b / GCD(a, b);
}

int main() {
	long long a, b, c, L, K;
	vector<long long> saves;
	c = -1;
	cin >> a >> b >> L;

	long long abLCM = LCM(a, b); // abLCM = LCM(a,b)
	K = L / abLCM; //K = L / LCM(A,B)
	//   c = GCD(abLCM,C) * K
	if (L % abLCM != 0)
		cout << -1;
	else {
		int len = sqrt(abLCM);
		for (int i = 1; i <=len; i++) { //saves[i] = GCD(abLCM, t)
			if (abLCM%i == 0) {
				saves.push_back(i);
				saves.push_back(abLCM / i);
			}
		}
		sort(saves.begin(), saves.end());
		bool is_found = false;
		for (int i = 0; i < saves.size(); i++) { 
			c = K * saves[i]; //t = GCD(abLCM, t) * K
			if (GCD(abLCM, c) == saves[i]) { // c==t ?
				is_found = true;
				break;
			}
		}

		if (is_found)
			cout << c;
		else
			cout << -1;
	}
}