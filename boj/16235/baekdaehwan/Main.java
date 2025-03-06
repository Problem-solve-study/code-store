/**
 * 25788KB	392ms
 * 과거에 한 번 풀었던 문제다.
 * 이 문제에서 얻을 수 있는 점은 다음과 같다.
 * 보통, 연산 중간에 다른 값이 추가될 수 있는 원소집합에서 가장 작은 값을 꺼내야 하는 문제는 힙으로 접근하고들 한다.
 * 하지만 추가되는 값이 기존 집합의 원소보다 작(크)거나 같을 경우에는 데크만 사용하여, 정렬된 상태를 유지할 수 있다.
 * 
 * 이 문제를 통해서 추가되는 값의 특징에 따라서 데크도 사용할 수 있다는 점을 배울 수 있다.
 * 꼭 추가되거나 제거되는 값의 특징을 살펴본 후, 사용할 자료구조를 결정하는 습관을 들이자.
 * 
 * 
 * [알고리즘 설명]
 * 시뮬레이션 문제치고 제한시간이 악랄하다.
 * 이 문제의 경우, 중간에 나무가 늘어나기 때문에 시간 복잡도를 작성하기 애매하다. 그래서 결과론적으로 분석해보았다.
 * 
 * 아래와 같은 예시를 정답코드에 돌려보면, 약 8000개 이상의 나무가 맵에 존재하는 것이 가능함을 알 수 있다.
 * 
 * 10 10 1000
 * 100 100 100 100 100 100 100 100 100 100
 * 100 100 100 100 100 100 100 100 100 100
 * 100 100 100 100 100 100 100 100 100 100
 * 100 100 100 100 100 100 100 100 100 100
 * 100 100 100 100 100 100 100 100 100 100
 * 100 100 100 100 100 100 100 100 100 100
 * 100 100 100 100 100 100 100 100 100 100
 * 100 100 100 100 100 100 100 100 100 100
 * 100 100 100 100 100 100 100 100 100 100
 * 100 100 100 100 100 100 100 100 100 100
 * 1 1 5
 * 1 2 5
 * 1 3 5
 * 1 4 5
 * 1 5 5
 * 1 6 5
 * 1 7 5
 * 1 8 5
 * 1 9 5
 * 1 10 5
 * 
 * ANS: 8234
 * 
 * 문제에서 동일한 칸에 존재하는 나무들 중 어린 나무부터 양분을 먹는다는 조건이 있기 때문에, 
 * 만약 정렬을 사용한다면 K * (N + NlogN)이 된다. 이러면 N이 8234일 때 10,000,000은 확실히 넘어간다.
 * 여기서 복잡도를 줄일수 있는 방법은 정렬을 하지 않는 방법밖에 없다.
 * 
 * 이 문제의 특징은 한 격자에서 나이 순서대로 양분을 먹을 때, 
 * 연속된 나무들이 죽게 된다는 것과, 새로운 나무는 항상 나이가 1이라는 점이다.
 * 즉, 힙과 같은 자료구조 대신 데크를 사용할 수 있다.
 * 
 * 먼저, 모든 그리드를 데크로 만든다.
 * 이 데크에는 그 그리드에 심겨있는 나무가 '오름차순'으로 저장되어있다.
 * 
 * 우리는 아래 두 경우에만 데크를 변경한다.
 * 1. (봄) 나무들이 양분을 먹고 나이를 먹는다.(혹은 양분을 먹지 못하고 죽는다)
 * 2. (가을) 나이가 5의 배수인 나무들이 번식한다.
 * 
 * 
 * 1번의 경우를 살펴보자.
 * 데크에 있는 나무들에게 순서대로 양분을 먹이고, 나이를 +1해주어야 한다.(혹은 데크에서 제거)
 * 
 * 데크에는 나무들이 나이 오름차순으로 들어가 있기 때문에, 
 * 데크의 최초 크기만큼 셔큘러 시프트를 하면서 양분을 먹이고 나이를 증가시킨다.
 * 만약 양분을 먹을 수 없다면, 나무를 시프팅하는 중에 다시 데크에 넣어주지 않으면 된다.
 * 
 * 이러면 데크의 모든 나무가 나이가 증가하거나 죽고, 데크는 나이 오름차순임이 보장된다.
 * 
 * 
 * 2번의 경우를 살펴보자.
 * 항상 번식으로 생겨나는 새로운 나무의 나이는 1이다.
 * 즉, 현재 존재하는 어떤 나무들보다 나이가 작거나 같다.
 * 그렇다면 인접한 그리드의 데크의 첫번째로 넣어주면, 모든 데크가 나이 오름차순으로 정렬됨이 보장된다.
 * 
 * 
 * 이제 위 내용을 토대로 시뮬레이션을 돌리면 아래와 같이 코드를 작성할 수 있다.
 */

import java.io.*;
import java.util.*;


public class Main {
	static int N, M, K;
	static int[][] fuel;
	static int[][] fuelInterval;
	static List<Deque<Integer>> map;
	static int[] dr = {0,0,1,-1,1,1,-1,-1};
	static int[] dc = {1,-1,0,0,-1,1,-1,1};
	
	static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		N=ni();
		M=ni();
		K=ni();

		fuel = new int[N][N];
		fuelInterval = new int[N][N];
		map = new ArrayList<>();

		for (int r=0; r<N; ++r) {
			for (int c=0; c<N; ++c) {
				fuel[r][c] = 5;
			}
		}
		for (int r=0; r<N; ++r) {
			for (int c=0; c<N; ++c) {
				fuelInterval[r][c] = ni();
			}
		}
		
		int tmpLen = N*N;
		for (int i=0; i<tmpLen; ++i) map.add(new ArrayDeque<>());
		for (int i=0; i<M; ++i) grid(ni()-1, ni()-1).addLast(ni());
		
		for (int i=0; i<K; ++i) {
//			봄
			for (int r=0; r<N; ++r) {
				for (int c=0; c<N; ++c) {
					Deque<Integer> cur = grid(r, c);
					int size = cur.size();
					
					int newFuel = 0;
					for (int j=0; j<size; ++j) {
						int age = cur.pollFirst();
						if (age <= fuel[r][c]) {
							fuel[r][c] -= age;
							cur.addLast(age+1);
						}
						else {
							newFuel += age>>1;
						}
					}
					
//					미리 여름
					fuel[r][c] += newFuel;
				}
			}
			
//			가을
			for (int r=0; r<N; ++r) {
				for (int c=0; c<N; ++c) {
					Deque<Integer> cur = grid(r, c);
					for (int t: cur) {
						if (t%5 == 0) {
							for (int d=0; d<8; ++d) {
								int nr = r+dr[d];
								int nc = c+dc[d];
								if (0<=nr&&nr<N && 0<=nc&&nc<N) grid(nr, nc).addFirst(1);
							}
						}
					}
				}
			}
			
//			겨울
			for (int r=0; r<N; ++r) {
				for (int c=0; c<N; ++c) {
					fuel[r][c] += fuelInterval[r][c];
				}
			}
		}
		
		int res = 0;
		for (Deque<Integer> grid: map) res += grid.size();
		System.out.println(res);
	}

	public static Deque<Integer> grid(int r, int c) {
		return map.get((r*N)+c);
	}
	
	public static int ni() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
