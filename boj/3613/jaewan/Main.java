
// 11480 KB, 68 ms
/*
 * Java의 변수 네이밍과 C++의 변수 네이밍 서로 변환하기
 * _가 나오면 C++, upper case가 나오면 java. 둘 다 나오면 Error
 */
import java.io.IOException;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        boolean cFlag = false, javaFlag = false;

        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = System.in.read()) > 32) {

            // 언더바일 때
            if (c == 95) {
                cFlag = true;
                // 첫 글자에 나오면 안됨
                if (sb.length() == 0) {
                    javaFlag = true;
                    break;
                }
                c = System.in.read();
                c -= 32;
                // 다음 글자가 올바른 입력인지 확인
                if ('A' > c || c > 'Z') {
                    javaFlag = true;
                    break;
                }
                sb.append((char) c);
                continue;
            }
            // 대문자일 때
            if ('A' <= c && c <= 'Z') {
                javaFlag = true;
                // 첫 글자 대문자면 안됨
                if (sb.length() == 0) {
                    cFlag = true;
                    break;
                }

                // 앞에 _를 붙이고, 소문자로 변경
                sb.append('_');
                c += 32;
            }
            sb.append((char) c);
        }
        if (cFlag && javaFlag)
            System.out.println("Error!");
        else
            System.out.println(sb.toString());
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}