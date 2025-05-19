//제출번호: 94319011
//메모리:   13352 KB
//실행시간: 364 ms
import java.io.*;

//모든 소용돌이 숫자를 다 탐색한 다음에 출력해야 할 범위에 있는 숫자들만 배열에 저장하고 출력하기
//나올 수 있는 수는 10001 * 10001 인데 10000 * 10000 까지만 계산해서 1틀
//int[10001][10001]은 약 400MB여서 메모리 초과임
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int r1 = nextInt(), c1 = nextInt(), r2 = nextInt(), c2 = nextInt();

        //실제로 필요한 행, 열 길이를 구하고 그만큼만 배열을 만듦
        int rLen = r2 - r1 + 1, cLen = c2 - c1 + 1;
        int[][] map = new int[rLen][cLen];

        int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
        int x = 0, y = 0, dir = 0, num = 1;

        //0, 0이 출력할 범위 안에 있으면 배열에 추가함
        if (r1 <= 0 && 0 <= r2 && c1 <= 0 && 0 <= c2) {
            map[0-r1][0-c1] = 1;
        }

        //모든 소용돌이 숫자를 확인하면서
        for (int i = 1; i < 10002; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < i; k++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    num++;
                    
                    //nx, ny 위치가 출력할 범위 안에 있으면 배열에 숫자를 추가함
                    if (r1 <= nx && nx <= r2 && c1 <= ny && ny <= c2) {
                        map[nx-r1][ny-c1] = num;
                    }
                    x = nx;
                    y = ny;
                }

                dir = (dir + 1) & 3;
            }
        }

        //기본 셀 크기는 2 (공백 1칸 + 1자리 숫자)
        int cellSize = 2;
        
        //출력할 숫자들을 모두 확인하면서 셀 크키가 가장 많이 필요한 숫자로 맞춤
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                num = map[i][j];
                int size = 0;
                while (num > 0) {
                    num /= 10;
                    size++;
                }

                //앞 공백 1칸 + 숫자 길이 만큼 셀 크기가 필요함
                cellSize = Math.max(cellSize, size+1);
            }
        }

        StringBuilder sb = new StringBuilder();
        String firstFormat = "%" + (cellSize - 1) + "d"; //맨 앞은 앞 공백이 필요 없음
        String restFormat = "%" + cellSize + "d"; //나머지는 앞 공백이 필요함
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                //형식에 맞춰서 숫자를 출력
                sb.append(String.format(j == 0 ? firstFormat : restFormat, map[i][j]));
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