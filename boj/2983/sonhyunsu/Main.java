//제출번호: 93132804
//메모리:	55756 KB
//실행시간:	804 ms
import java.io.*;
import java.util.*;

//현재 식물에서 명령을 받았을 때 갈 수 있는 가장 가까운 식물을 찾아보자
//모든 식물들을 다 탐색하는 건 불가능할 거 같고,
//대각선만 갈 수 있으니까 x + y, x - y 가 같은 값을 가지는 식물들을 한 번 묶어보자
//근데 그 식물들에서 가장 가까운 건 어떻게 찾지? 한 번 밟은 식물은 없애야 하는데 어떻게 하지?

//처음에는 TreeSet에 담고, 현재 식물이 있는 좌표의 index + 1 or index - 1에 있는 식물로 이동하고,
//현재 식물은 TreeSet에서 삭제하는 방법으로 하려고 했는데 TreeSet의 index를 구하는 방법이 없었음 
//식물들이 삭제되고, 가장 가까운 식물들만 알면 되니까 Double Linked List로 구현해보자
//DLL은 STL이 없어서 직접 구현... 너무 귀찮음

//double linked list를 이용해서 현재 식물에서 갈 수 있는 식물들을 연결함
//DLL을 구현할 때는 갈 수 있는 식물들을 Map과 List를 가지고 찾은 뒤에
//인접한 식물들을 연결하는 방향으로 구현
//이 때 대각선 x + y, x - y 값을 기준으로 식물들을 묶었음
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        nextInt();

        String cmds = nextString();

        int[][] plant = new int[2][n + 1];
        Map<Integer, List<int[]>> diagonal1 = new TreeMap<>();
        Map<Integer, List<int[]>> diagonal2 = new TreeMap<>();
        for (int i = 1; i <= n; i++) {
            int x = nextInt();
            int y = nextInt();

			//식물의 위치를 마지막에 출력하기 위해서 위치를 기록함
            plant[0][i] = x;
            plant[1][i] = y;

			//x + y = a을 만족하는 (x, y) 쌍을 하나의 리스트로 묶음
            List<int[]> tmp = diagonal1.getOrDefault(x + y, new ArrayList<>());
            tmp.add(new int[]{x - y, i}); //x - y만 알면 x, y를 역으로 구할 수 있음
            diagonal1.put(x + y, tmp);

			//x - y = a를 만족하는 (x, y) 쌍을 하나의 리스트로 묶음
            tmp = diagonal2.getOrDefault(x - y, new ArrayList<>());
            tmp.add(new int[]{x + y, i}); //x + y만 알면 x, y를 역으로 구할 수 있음
            diagonal2.put(x - y, tmp);
        }

        int[] d1Next = new int[n + 1]; //x + y 대각선의 오른쪽 방향
        int[] d1Prev = new int[n + 1]; //x + y 대각선의 왼쪽 방향
        int[] d2Next = new int[n + 1]; //x - y 대각선의 오른쪽 방향
        int[] d2Prev = new int[n + 1]; //x - y 대각선의 왼쪽 방향

        for (Map.Entry<Integer, List<int[]>> edge : diagonal1.entrySet()) {
            List<int[]> vals = edge.getValue();
			//x - y를 기준으로 정렬함 ==> 그러면 x + y = a인 모든 식물들이 왼쪽에서 오른쪽 순서로 정렬됨
            vals.sort((i1, i2) -> Integer.compare(i1[0], i2[0]));

			//인접한 두 식물에 대해서
            for (int i = 0, j = 1; j < vals.size(); i++, j++) {
                int iIdx = vals.get(i)[1];
                int jIdx = vals.get(j)[1];

				//i번째 식물은 다음에 j번째 식물을 갈 수 있고
                d1Next[iIdx] = jIdx;
				//j번째 식물은 이전에 i번째 식물을 갈 수 있음
                d1Prev[jIdx] = iIdx;
            }
        }

        for (Map.Entry<Integer, List<int[]>> edge : diagonal2.entrySet()) {
            List<int[]> vals = edge.getValue();
			//x + y를 기준으로 정렬함 ==>  그러면 x - y = a인 모든 식물이 왼쪽에서 오른쪽 순서로 정렬됨
            vals.sort((i1, i2) -> Integer.compare(i1[0], i2[0]));

			//인접한 두 식물에 대해서
            for (int i = 0, j = 1; j < vals.size(); i++, j++) {
                int iIdx = vals.get(i)[1];
                int jIdx = vals.get(j)[1];

				//i번째 식물은 다음에 j번째 식물을 갈 수 있고
                d2Next[iIdx] = jIdx;
				//j번째 식물은 이전에 i번째 식물을 갈 수 있음
                d2Prev[jIdx] = iIdx;
            }
        }

        int pos = 1; //위치는 첫 식물에서 시작
        for (char cmd : cmds.toCharArray()) {
            int next = 0;
            switch (cmd) {
                case 'A':
					//x + p, y + p 인 식물을 찾음
					//현재 위치에서 x - y 대각선에서 오른쪽으로 가는 방향임
					//이 때 오른쪽에 식물이 없으면 갈 수 없음 (==> 명령을 무시함)
                    if (d2Next[pos] == 0) {
                        continue;
                    }

					//다음 위치를 오른쪽에 있는 식물로 선택함
                    next = d2Next[pos];
                    break;
                case 'B':
					//x + p, y - p 인 식물을 찾음
					//현재 위치에서 x + y 대각선에서 오른쪽으로 가는 방향임
					//이 때 오른쪽에 식물이 없으면 갈 수 없음 (==> 명령을 무시함)
                    if (d1Next[pos] == 0) {
                        continue;
                    }

					//다음 위치를 오른쪽에 있는 식물로 선택함
                    next = d1Next[pos];
                    break;
                case 'C':
					//x - p, y + p 인 식물을 찾음
					//현재 위치에서 x + y 대각선에서 왼쪽으로 가는 방향임
					//이 때 왼쪽에 식물이 없으면 갈 수 없음 (==> 명령을 무시함)
                    if (d1Prev[pos] == 0) {
                        continue;
                    }

					//다음 위치를 왼쪽에 있는 식물로 선택함
                    next = d1Prev[pos];
                    break;
                case 'D':
					//x - p, y - p 인 식물을 찾음
					//현재 위치에서 x - y 대각선에서 왼쪽으로 가는 방향임
					//이 때 왼쪽에 식물이 없으면 갈 수 없음 (==> 명령을 무시함)
                    if (d2Prev[pos] == 0) {
                        continue;
                    }
					
					//다음 위치를 왼쪽에 있는 식물로 선택함
                    next = d2Prev[pos];
                    break;
            }

			//여기까지 내려왔으면 다음 식물로 점프할 수 있다는 것
			//점프했으므로 현재 식물은 DLL에서 삭제해야 함
            d1Prev[d1Next[pos]] = d1Prev[pos]; //다음 식물의 이전 위치는 현재 식물의 이전 위치
            d1Next[d1Prev[pos]] = d1Next[pos]; //이전 식물의 다음 위치는 현재 식물의 다음 위치
            d2Prev[d2Next[pos]] = d2Prev[pos]; //아래도 동일
            d2Next[d2Prev[pos]] = d2Next[pos];

			//현재 식물의 위치를 다음으로 옮김
            pos = next;
        }

		//도착한 식물의 x, y 좌표 출력
        System.out.printf("%d %d", plant[0][pos], plant[1][pos]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}