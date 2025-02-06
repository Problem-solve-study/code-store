//MEMORY		3192KB
//TIME		52ms

#include <iostream>
#include <vector>

#define MAX 100001

using namespace std;

int parent[MAX];
int clear[MAX];
int dirty[MAX];

void init() {
	for (int i = 0;i < MAX;i++) {
		parent[i] = i;
	}
}
int find_parent(int n) {
	if (parent[n] == n)
		return n;
	parent[n] = find_parent(parent[n]);
	return parent[n];
}
void union_group(int u, int v) {
	int parentU = find_parent(u);
	int parentV = find_parent(v);
	if (parentU != parentV) {
		if (parentU > parentV) {
			swap(parentU, parentV);
		}
		clear[parentU] += clear[parentV];
		dirty[parentU] += dirty[parentV];
		parent[parentV] = parentU;
	}
}
void find_query(int q) {

	int parent = find_parent(q);
	if (clear[parent] > dirty[parent])
		cout << "1\n";
	else
		cout << "0\n";
}
int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	init();
	int N, M, Q;
	cin >> N >> M >> Q;
	int n;
	for (int i = 1;i <= N;i++) {
		cin >> n;
		if (n == 1)
			clear[i]++;
		else
			dirty[i]++;
	}
	int u, v;
	for (int i = 0;i < M;i++) {
		cin >> u >> v;
		union_group(u, v);
	}
	int q;
	for (int i = 0;i < Q;i++) {
		cin >> q;
		find_query(q);
	}
}