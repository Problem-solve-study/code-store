//제출번호: 92086849
//메모리:   11540 KB
//실행시간: 64 ms
import java.io.*;

//깡 구현 문제..
//조건에 맞게 변수를 파싱하면 됨
//Error가 날 조건이 많기 때문에 모두 다 찾는 것이 중요함
//1. _가 맨 앞에 있는 경우
//2. _가 맨 뒤에 있는 경우
//3. _가 연속으로 2번 이상 나오는 경우
//4. 대문자가 맨 앞에 나오는 경우
//5. 대문자와 _가 동시에 나오는 경우
//Error가 나지 않으면 파싱 가능
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();

        boolean isSnake = name.contains("_");
        StringBuilder sb = new StringBuilder();
        boolean isError = false;

        //_가 있으면 C++ 타입이라고 판단하고 파싱을 시작함
        //C++ 단어를 Java 단어로 변경
        if (isSnake) {
            boolean nextUpper = name.charAt(0) == '_';

            //만약 맨 앞에 _가 있다면 Error
            if (nextUpper) {
                isError = true;
            } else {
                for (int i = 0; i < name.length(); i++) {
                    char c = name.charAt(i);
                    if (c == '_') {
                        //만약 _가 연속으로 2번 이상 나온다면
                        if (nextUpper) {
                            isError = true;
                            break;
                        }

                        nextUpper = true;
                    } else if ('a' <= c && c <= 'z') {
                        //다음에 대문자가 와야 한다면 대문자, 아니면 소문자 알파벳
                        if (nextUpper) {
                            sb.append((char) (c ^ 32));
                            nextUpper = false;
                        } else {
                            sb.append(c);
                        }
                    } else {
                        //만약 대문자가 있다면 Error
                        isError = true;
                        break;
                    }
                }

                //만약 맨 뒤에 _가 있다면 Error
                if (nextUpper) {
                    isError = true;
                }
            }
        } else {
            //Java 단어를 C++로 변경
            //만약 맨 앞에 대문자가 있다면 Error
            if (name.charAt(0) < 'a') {
                isError = true;
            } else {
                for (int i = 0; i < name.length(); i++) {
                    char c = name.charAt(i);

                    //대문자가 나오면 그냥 _소문자 로만 바꾸면 됨
                    if ((c & 32) == 0) {
                        sb.append('_').append((char) (c | 32));
                    } else {
                        //소문자는 그대로
                        sb.append(c);
                    }
                }
            }
        }

        System.out.print(isError ? "Error!" : sb);
    }
}