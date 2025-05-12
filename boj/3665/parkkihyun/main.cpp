// 위상정렬 문제

// 접근법
// 등수를 매길 때, 1등 -> 2등 -> 3등 순으로 출력되는 것에서 위상 정렬임을 착안
// 처음에는 5 -> 4 -> 3 -> 2 -> 1 로만 inDegree를 추가했음
// 하지만 이렇게 할 경우 2번과 4번의 관계를 표현할 수가 없음
// 따라서 i등은 i+1등부터 N등까지 모두 가리키게 표현
// 그렇게 하고 나는 2번과 4번의 관계를 표현할 수 있음

// ?가 나오는 경우: Queue에 요소가 2개가 들어가있는 경우, 순서를 정할 수 없기때문에 ?를 출력
// IMPOSSIBLE이 나오는 경우: 위상정렬이 완수되지 못 하는 경우 불가능이 발생
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>
using namespace std;

int N;
vector<int> ranking;
vector<int> inD;
vector<vector<int>> graph;
vector<vector<int>> adj;

void topology() {
	queue<int> q;

	for (int i = 1; i <= N; i++) {
		if (inD[i] == 0) {
			q.push(i);
		}
	}

	vector<int> answer;
	while (!q.empty()) {
    // Queue에 2명 이상 있는 경우 순서를 매길 수 없다.
		if (q.size() != 1) {
			cout << "?\n";
			return;
		}

		int cur = q.front(); q.pop();
		answer.push_back(cur);

		for (int nxt : adj[cur]) {
			inD[nxt]--;
			if (inD[nxt] == 0) {
				q.push(nxt);
			}
		}
	}

  // 위상정렬이 완수되지 못 한 경우 불가능
	if (answer.size() != N) {
		cout << "IMPOSSIBLE\n";
	}
  // 정상적이라면 순위 출력 가능
	else {
		for (int i : answer) {
			cout << i << ' ';
		}
		cout << '\n';
	}
}

int main() {
	cin.tie(0)->ios::sync_with_stdio(0);

	int TC; cin >> TC;
	while (TC--) {
		cin >> N;

    // 초기화
		ranking.assign(N + 1, 0);
		inD.assign(N + 1, 0);
		graph.assign(N + 1, vector<int>(N + 1, 0));
		adj.assign(N + 1, vector<int>());

    // 등수 입력
		for (int i = 1; i <= N; i++) {
			cin >> ranking[i];
		}

    // i등은 i+1 ~ N등까지 모두 가리킴
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				graph[ranking[i]][ranking[j]] = 1;
				inD[ranking[j]]++;
			}
		}

		int M; cin >> M;
		while (M--) {
			int a, b; cin >> a >> b;
      // a가 b보다 앞선 등수인 경우
			if (graph[a][b] == 1) {
        // 위치를 바꿔준다.
				graph[a][b] = 0;
				graph[b][a] = 1;
				inD[b]--;
				inD[a]++;
			}
      // b가 a보다 앞선 등수인 경우
			else {
        // 위치를 바꿔준다.
				graph[a][b] = 1;
				graph[b][a] = 0;
				inD[b]++;
				inD[a]--;
			}
		}


    // 500 * 500 그래프를 모두 조사해도 되지만 인접 리스트로 압축
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				if (graph[i][j] == 1) adj[i].push_back(j);
			}
		}

		// 위상정렬 수행
		topology();
	}

	return 0;
}
