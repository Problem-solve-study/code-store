import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1. IOI 패턴이면서 O가 주어진 길이만큼 나오는 첫 구간 찾기
// 2. 구간의 뒤에 OI가 나오는 만큼 개수 추가
// 3. 구간의 뒤에 OI가 안 나오면, 구간의 바로 뒤부터 1번 반복
public class Main {
    static int ioiLength;
    static int stringLength;
    static char[] s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ioiLength = Integer.parseInt(br.readLine()) * 2 + 1;
        stringLength = Integer.parseInt(br.readLine());
        s = br.readLine().toCharArray();

        int j = -1;
        int count = 0;
        while (j + 1 < stringLength) {
            j = findIoi(j + 1, ioiLength);
            if (j == -1) {
                break;
            }

            count++;
            while (j + 2 < stringLength) {
                if (s[j + 1] == 'O' && s[j + 2] == 'I') {
                    count++;
                    j += 2;
                } else {
                    break;
                }
            }
        }
        
        System.out.println(count);
    }

    static int findIoi(int start, int length) {
        int i = start;
        while (i < stringLength) {
            if (s[i] == 'I') {
                int j = 1;
                boolean flag = true;
                while (j < length) {
                    if (i + j >= stringLength) {
                        flag = false;
                        break;
                    }

                    if (j % 2 == 0 && s[i + j] == 'O') {
                        flag = false;
                        break;
                    }
        
                    if (j % 2 == 1 && s[i + j] == 'I') {
                        flag = false;
                        break;
                    }

                    j++;
                }

                if (flag) {
                    return i + j - 1;
                }

                i += j;
                continue;
            }

            i++;
        }

        return -1;
    }
}
