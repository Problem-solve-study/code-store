//제출번호: 93519042
//메모리:   60636 KB
//실행시간: 684 ms
import java.io.*;
import java.util.*;

//처음에 일반적인 시뮬레이션을 작성했는데 시간초과 났음
//어떤 열에 대해서, 여러 번 돌을 떨어뜨리면 같은 경로가 반복적으로 나올 것이기 때문에
//그 경로를 스택을 이용해서 저장해보기로 함

//돌이 미끄러질 때 인접한 열에 대해서 어느 지점에 떨어질 지 계산해야 하는데,
//이걸 O(N)으로 구하게 되면 시간초과 날 것 같았음
//관찰해보니 X....X... 이라는 열이 있을 때(맵을 90도 회전한 모양)
//XOOO.XOO. 가 최대로 쌓일 수 있다는 것을 알 수 있음
//다시 말하면 어떤 X에 대해서 돌이 쌓여도 다음 X를 절대 침범하지 못 함
//==> X의 위치를 잘 저장하면 리스트의 원소가 항상 내림차순으로 관리되게 만들 수 있구나 싶었음 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<List<Integer>> availPos = new ArrayList<>();
        List<Deque<int[]>> stacks = new ArrayList<>();
        for (int i = -1; i < c; i++) {
            availPos.add(new ArrayList<>()); //돌이 놓있 수 있는 위치
            stacks.add(new ArrayDeque<>()); //열마다 이동 경로를 저장할 스택
        }

		//코드를 일반화시키기 위해서 
		//바닥과 양쪽 벽은 항상 X로 가득하게끔 만들고, 그 사이에 입력값을 넣는다.
        int[][] map = new int[r + 1][c + 2];

        for (int i = r; i > 0; i--) {
            String line = br.readLine();
            for (int j = 1; j <= c; j++) {
				//벽이면 1, 아니면 0을 저장
                map[i][j] = line.charAt(j - 1) == 'X' ? 1 : 0;

				//이번이 벽이면 돌을 놓을 수 있는 위치인지 확인해 봄
                if (line.charAt(j - 1) == 'X') {
                    boolean canPut = true;
					//벽의 위쪽 2칸을 보면서, 두 칸 모두 벽이 없으면
                    for (int l = 1; l < 3; l++) {
                        if (i + l <= r && map[i + l][j] == 1) {
                            canPut = false;
                        }
                    }

					//벽의 한 칸 위쪽은 돌을 놓을 수 있는 위치임
                    if (canPut) {
                        availPos.get(j).add(i + 1);
                    }
                }
            }
			//맵의 양쪽을 벽으로 채움
            map[i][0] = map[i][c + 1] = 1;
        }

		//바닥은 항상 돌을 놓을 수 있는 위치라고 하고,
		//바닥 아래에는 벽을 세움
        for (int i = 1; i <= c; i++) {
            availPos.get(i).add(1);
            map[0][i] = 1;
        }        

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
			//돌을 떨어뜨릴 열을 입력받음
            int shootCol = Integer.parseInt(br.readLine());

			//해당 열의 경로를 저장해뒀던 스택을 가져옴
            Deque<int[]> stack = stacks.get(shootCol);

			//현재 저장된 경로에 대해서
            while (!stack.isEmpty()) {
                int[] pos = stack.peekLast();
				//해당 위치가 돌이나 벽으로 인해서 더 이상 돌이 미끄러질 수 없다면 경로에서 제거함
				//경로에서 제거하는 이유는 지금 저장된 경로가 다른 열에 떨어진 돌에 의해서 더 이상 쓸 수 없어졌기 때문
                if (map[pos[0]][pos[1]] > 0) {
                    stack.pollLast();
                } else {
                    break;
                }
            }

			//돌을 떨어뜨릴 위치를 설정함
            int stonePos, downCol;
            if (stack.isEmpty()) {
				//현재 설정된 경로가 없다면 맵의 최상단을 돌을 떨어뜨릴 지점으로 설정
                stonePos = r;
                downCol = shootCol;
            } else {
				//현재 설정된 경로가 있다면 경로의 가장 마지막 위치를 돌을 떨어드릴 지점으로 설정
                int[] pos = stack.pollLast();
                stonePos = pos[0];
                downCol = pos[1];
            }

            while (true) {
				//현재 돌의 위치에 대해서, 돌을 떨어뜨렸을 때 놓일 수 있는 위치를 이분탐색으로 계산
                int idx = getIdx(availPos.get(downCol), stonePos);
                int nextPos = availPos.get(downCol).get(idx); //실제 Row을 가져옴

                if (map[nextPos - 1][downCol] == 1) {
					//만약에 바로 아래가 벽이라면 
                    map[nextPos][downCol] = 2; //현재 위치를 돌이라고 설정하고
                    availPos.get(downCol).set(idx, nextPos + 1); //다음에 돌을 놓을 수 있는 위치는 현재 위치에서 한 칸 위로 설정
                    break;
                } else if (map[nextPos - 1][downCol - 1] == 0 && map[nextPos][downCol - 1] == 0) {
					//만약에 돌이 왼쪽으로 미끄러질 수 있다면
                    stack.add(new int[]{nextPos, downCol}); //현재 위치를 경로로 저장하고

					//돌을 왼쪽으로 미끄러트림
                    downCol -= 1;
                    stonePos = nextPos;
                } else if (map[nextPos - 1][downCol + 1] == 0 && map[nextPos][downCol + 1] == 0) {
					//만약에 돌이 오른쪽으로 미끄러질 수 있다면
                    stack.add(new int[]{nextPos, downCol}); //현재 위치를 경로로 저장하고

					//돌을 오른쪽으로 미끄러트림
                    downCol += 1;
                    stonePos = nextPos;
                } else {
					//그 외는 모두 현재 위치에 돌을 놓아야 하기 때문에
                    map[nextPos][downCol] = 2; //현재 위치에 돌을 놓고
                    availPos.get(downCol).set(idx, nextPos + 1); //다음에 돌을 놓을 수 있는 위치를 현재 위치에서 한 칸 위로 설정
                    break;
                }
            }
        }

		//숫자로 저장된 맵 정보를 문자로 변환하고, sb에 담음
        for (int i = r; i > 0; i--) {
            for (int j = 1; j <= c; j++) {
                switch (map[i][j]) {
                    case 0: sb.append('.'); break;
                    case 1: sb.append('X'); break;
                    case 2: sb.append('O'); break;
                }
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }

	//내림차순 정렬에 대한 Upperbound
    static int getIdx(List<Integer> list, int pos) {
        int left = 0, right = list.size() - 1, mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (list.get(mid) > pos) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}