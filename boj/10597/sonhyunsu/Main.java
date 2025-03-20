//제출번호: 91686851
//메모리:   12820 KB
//실행시간: 72 ms
import java.io.*;
import java.util.*;

//수열에 들어가는 숫자가 작아서 가능한 모든 경우를 탐방하는 방식을 채택함
//수열을 만드는 것을 성공하면 재귀를 탈출하도록 코드 작성
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[] visited;
    static int n, len;
    static char[] sequence;
    static ArrayDeque<Integer> ad = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        sequence = br.readLine().toCharArray();

        len = sequence.length;
        //문자열 길이에 따라 n이 결정됨
        if (len < 10) {
            //10 미만의 문자열은 1~9 까지 밖에 없으므로 len이 곧 n이 됨
            n = len;
        } else {
            //10 이상의 문자열은 1~9는 한 자리, 10~50은 두 자리를 차지하므로
            //그에 맞춰서 n을 계산해줌
            n = 9 + (len - 9 >> 1);
        }

        //숫자를 찾았는 지 기록하는 배열
        visited = new boolean[n + 1];
        parse(0); //한 번 파싱해봄

        StringBuilder sb = new StringBuilder();
        
        //stack에 담긴 숫자를 빼면서 출력 생성
        while (!ad.isEmpty()) {
            sb.append(ad.pollFirst()).append(' ');
        }
        System.out.print(sb);
    }

    static boolean parse(int pos) {
        //만약 pos가 len까지 왔으면 파싱이 성공한 것
        if (pos == len) {
            return true;
        }

        int num = sequence[pos] & 15;
        //만약 num이 0이면 현재 상태로는 파싱이 불가능한 것
        if (num == 0) {
            return false;
        }

        //파싱한 숫자가 n 이하이면서 아직 파싱되지 않은 숫자라면
        if (num <= n && !visited[num]) {
            visited[num] = true;

            //다음도 파싱해봄
            if (parse(pos + 1)) {
                //다음 파싱도 성공한다면 파싱이 완료된 것이므로 stack에 num을 넣음
                ad.addFirst(num);
                return true;
            }

            visited[num] = false;
        }

        //만약 두 자리 파싱을 할 수 있다면
        if (pos + 1 < len) {
            //두 자리 num을 만들어 봄
            num = (num << 3) + (num << 1) + (sequence[pos + 1] & 15);

            //파싱한 숫자가 n 이하이면서 아직 파싱되지 않은 숫자라면
            if (num <= n && !visited[num]) {
                visited[num] = true;

                //지금 파싱한 위치의 다음 위치부터 파싱해봄
                if (parse(pos + 2)) {
                    //다음 파싱도 성공한다면 파싱이 완료된 것이므로 stack이 num을 넣음
                    ad.addFirst(num);
                    return true;
                }

                visited[num] = false;
            }
        }

        //위에서 true로 함수가 종료되지 않았다면 파싱이 불가능하므로 false 반환
        return false;
    }
}

/* 메모리, 실행시간을 최적화한 버전,
    위 코드는 앞에서부터 뒤로 파싱했다면,
    아래 코드는 뒤에서부터 앞으로 파싱한다.
    덕분에 파싱을 성공한 경우 바로 sb에 출력을 넣을 수 있다. 

//제출번호: 91688205
//메모리:   11452 KB
//실행시간: 64 ms
import java.io.*;

public class Main {

    static boolean[] visited;
    static int n, len;
    static int[] sequence = new int[91];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int c = System.in.read();
        while (c > 47) {
            sequence[len++] = c & 15;
            c = System.in.read();
        }

        if (len < 10) {
            n = len;
        } else {
            n = 9 + (len - 9 >> 1);
        }

        visited = new boolean[n + 1];
        
        parse(len - 1);
        
        System.out.print(sb);
    }

    static boolean parse(int pos) {
        if (pos == -1) {
            return true;
        }

        int num = 0;
        for (int i = pos, diff = 0; diff < 2 && i >= 0; i--, diff++) {
            num += diff == 0 ? sequence[i] : (sequence[i] << 3) + (sequence[i] << 1);

            if (num == 0) {
                continue;
            }

            if (num <= n && !visited[num]) {
                visited[num] = true;

                if (parse(i - 1)) {
                    sb.append(num).append(' ');
                    return true;
                }

                visited[num] = false;
            }
        }

        return false;
    }
}
 */