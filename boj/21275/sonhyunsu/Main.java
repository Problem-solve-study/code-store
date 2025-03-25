//제출번호: 91976349
//메모리:   11728 KB
//실행시간: 72 ms
import java.io.*;
import java.util.*;

//진법이 36까지 밖에 없어서 36^2 으로 모두 돌면서 같은 값이 나오는 지 확인만 하면 됨
//진법 변환할 때 Long 범위를 넘어가는 지 확인 필요
//여러 개 있는 것은 돌다가 중간에 확인만 하면 됨
//문자열을 최대한 안 쓰니까 64ms 까지 단축 성공
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();

        boolean isMultiple = false;
        long x = -1;
        int fa = 0;
        int fb = 0;

        form:
        for (int i = 2; i < 37; i++) {
            //a를 i진법으로 변환한다
            long ca = convertForm(a, i);

            //만약 진법 변환이 불가능하다면 다음 진법으로 변환해본다
            if (ca == -1) {
                continue;
            }

            for (int j = 2; j < 37; j++) {
                //A, B는 다른 진법이므로 패스
                if (i == j) {
                    continue;
                }

                //b를 j진법으로 변환한다
                long cb = convertForm(b, j);

                //만약 진법 변환이 불가능하다면 다음 진법으로 변환해본다.
                if (cb == -1) {
                    continue;
                }

                //만약 진법 변환한 값이 같다면 성공
                if (ca == cb) {
                    //이 때 이미 x에 저장된 값이 있다면 진법 변환이 여러 개 가능한 것
                    if (x != -1) {
                        isMultiple = true;
                        break form;
                    }

                    x = ca;
                    fa = i;
                    fb = j;
                }
            }
        }

        if (isMultiple) {
            System.out.print("Multiple");
        } else if (x == -1) {
            System.out.print("Impossible");
        }else {
            System.out.printf("%d %d %d", x, fa, fb);
        }
    }

    //진법 변환
    static long convertForm(String type, int form) {
        long num = 0;

        for (int i = 0; i < type.length(); i++) {
            //자릿 수를 숫자로 변환한다.
            int f = convertChar(type.charAt(i));

            //만약 숫자가 진법보다 크다면 해당 진법으로 표현할 수 없는 숫자이므로
            //-1을 리턴
            if (f >= form) {
                return -1;
            }

            //만약 num에 진법을 곱했을 때 Long 범위를 넘어간다면
            //Long 범위 안에서 표현할 수 없는 숫자이므로 -1을 리턴
            if (num != 0 && Long.MAX_VALUE / num < form) {
                return -1;
            }

            num = num * form + f;
        }

        //만들어진 숫자 반환
        return num;
    }

    //문자 변환 - 0~9 => 0~9, a~z => 10~35
    static int convertChar(char c) {
        return c <= '9' ? c - '0' : c - 'a' + 10;
    }
}