//제출번호: 94182831
//메모리:   21940 KB
//실행시간: 332 ms
import java.io.*;
import java.util.*;

//순위가 바뀐 팀의 순위를 차례대로 바꿔보는 방향으로 구현했음
//만약에 차례대로 순위를 바꾸다가 순위를 더 바꿀 수 없는데, 아직 순위가 안 바뀐 경우가 있다면 불가능한 경우임
//작년 순위가 정해져 있기 때문에 불가능한 경우는 있어도, 순위가 모호한 경우는 없음 (?는 출력하지 않음)
//스왑을 잘못해서 1틀
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int[] arr = new int[501];
        int[] idx = new int[501];

        List<List<Integer>> inverse = new ArrayList<>();
        for (int i = 0; i < 501; i++) {
            inverse.add(new ArrayList<>());
        }

        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = nextInt();

            //입력을 받고, 각 팀이 몇번째에 있는지 idx에 저장
            for (int i = 0; i < n; i++) {
                int num = arr[i] = nextInt();
                idx[num] = i;
                inverse.get(num).clear();
            }

            int m = nextInt();
            for (int i = 0; i < m; i++) {
                int a = nextInt();
                int b = nextInt();

                //만약 순위가 바뀔 때 더 뒤에 있는 팀이 앞으로 와야 하므로 조건에 맞춰서 inverse에 저장
                if (idx[a] > idx[b]) {
                    inverse.get(a).add(b);
                } else {
                    inverse.get(b).add(a);
                }
            }

            boolean isPossible = true;
            for (int i = 0; i < n; i++) {
                List<Integer> inv = inverse.get(arr[i]);
                //현재 등수를 기준으로 내 앞에 있는 팀의 순서를 정렬
                //나를 기준으로 한 칸씩 앞으로 가면서 순서를 바꿀 것임
                inv.sort((i1, i2) -> -Integer.compare(idx[i1], idx[i2]));

                for (int j = 0; j < inv.size(); j++) {
                    int tIdx = i-j-1; //비교할 다음 인덱스를 찾음
                    if (arr[tIdx] != inv.get(j)) {
                        //지금 바꿔야 할 팀과 실제 등수의 팀이 다르다면 불가능한 경우가 됨
                        isPossible = false;
                        break;
                    }

                    //인접한 2개의 팀의 위치를 서로 바꿈
                    //버블정렬처럼 돌아가면서 i번째 팀이 바뀐 순위의 위치로 이동하는 것
                    idx[arr[tIdx+1]] = tIdx;
                    idx[inv.get(j)] = tIdx+1;

                    int tmp = arr[tIdx+1];
                    arr[tIdx+1] = arr[tIdx];
                    arr[tIdx] = tmp;
                }
            }

            if (!isPossible) {
                //만약 불가능하다면 불가능하다고 출력
                sb.append("IMPOSSIBLE");
            } else {
                //가능하다면 순위를 출력
                for (int i = 0; i < n; i++) {
                    sb.append(arr[i]).append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}