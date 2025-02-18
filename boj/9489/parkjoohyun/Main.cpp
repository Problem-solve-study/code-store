//Memory 2036KB
//Time 144ms

#include <iostream>

using namespace std;

int N, K;
int sibling_cnt;
// 0 : number 1 : group 2: parent_group
// same level => sibling
int tree[1001][3];
//group_size
int group_size[1001];

void input() {
	cin >> N >> K;
	
	for (int i = 1; i <= N; i++) {
		cin >> tree[i][0];
		if (K == tree[i][0])
			K = i;
	}
}

void solution() {
	int group = 1; //sibling_group
	int idx = 0; //parent_idx;
	int prev = tree[1][0];

	tree[1][1] = group;
	for (int i = 2; i <= N; i++) {
		if (prev + 1 != tree[i][0]) {
			group++;
			idx++;
			
		}
		tree[i][1] = group;
		tree[i][2] = tree[idx][1];
		prev = tree[i][0];
	}

	int k = tree[K][2];
	int g = tree[K][1];
	int ret = 0;
	for (int i = 1; i <= N; i++) {
		if (tree[i][2] == k && tree[i][1]!=g) { // different parent same grand parent
			ret++;
		}
	}

	cout << ret << "\n";
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	while (N != 0 && K != 0) {
		solution();

		input();
	}
}