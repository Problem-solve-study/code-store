//MEMORY		2024KB
//TIME		0ms

#include <iostream>
#include <string>
#include <vector>

using namespace std;

int N, oper_num;
int answer = INT32_MIN;
vector<int> num, oper;
int Max(int a, int b) { return a > b ? a : b; }

int calculate(int n1, int n2, char op) {
	if (op == '+')return n1 + n2;
	if (op == '-')return n1 - n2;
	if (op == '*')return n1 * n2;
}

void DFS(int idx, int result) {
	if (idx >= oper_num)
	{
		answer = Max(answer, result);
		return;
	}
	int cur = calculate(result, num[idx + 1], oper[idx]); //괄호 X
	DFS(idx + 1, cur);
	if (idx + 1 < oper_num) { //괄호 O
		int after = calculate(num[idx + 1], num[idx + 2], oper[idx + 1]);
		int cur = calculate(result, after, oper[idx]);
		DFS(idx + 2, cur);
	}
}

void solution() {
	if (N == 1) {
		cout << num[0] << endl;
		return;
	}
	DFS(0, num[0]);
	cout << answer << endl;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	string expr;
	cin >> N;
	cin >> expr;

	//연산자 & 숫자 분리
	for(int i=0;i<N;i++) {
		if (expr[i] >= '0' && expr[i] <= '9')
			num.push_back(expr[i]-'0');
		else
			oper.push_back(expr[i]);
	}
	oper_num = oper.size();
	solution();
}
