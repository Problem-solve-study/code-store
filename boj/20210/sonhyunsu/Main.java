//제출번호: 94274019
//메모리:   36112 KB
//실행시간: 596 ms
import java.io.*;
import java.util.*;

//커스텀 정렬 함수를 만들어야 하는데 매우 까다로웠던 문제
//알파벳은 문제가 안 되는데 숫자를 비교하는 과정에서 4틀함 - 얌전히 BigInteger 썼으면 빨리 통과했을 듯
//숫자는 다르게 정렬해야 하기 때문에 숫자 정렬 함수를 따로 만들어서 배열 정렬 함수에 적용함
public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String[] fileNames = new String[n];
        for (int i = 0; i < n; i++) {
            fileNames[i] = br.readLine();
        }
        
        Arrays.sort(fileNames, Main::compare);

        StringBuilder sb = new StringBuilder();
        for (String fileName : fileNames) {
            sb.append(fileName).append('\n');
        }
        System.out.print(sb);
    }

    static int compare(String a, String b) {
        //한 문자씩 비교하는게 아니기 때문에 현재 비교할 위치를 변수로 관리
        int aIdx = 0, bIdx = 0;
        int aLen = a.length(), bLen = b.length();

        //둘 중 하나를 모두 비교하면 반복 종료
        while (aIdx < aLen && bIdx < bLen) {
            //현재 비교할 문자를 가져옴
            char aChr = a.charAt(aIdx), bChr = b.charAt(bIdx);

            if (aChr < 'A' && bChr < 'A') {
                //만약 둘 다 숫자라면 숫자 정렬 기준에 맞게 정렬해야 함
                //연속된 숫자 구간을 구함
                int aLastIdx = aIdx + 1, bLastIdx = bIdx + 1;
                while (aLastIdx < aLen && a.charAt(aLastIdx) < 'A') {
                    aLastIdx++;
                }
                while (bLastIdx < bLen && b.charAt(bLastIdx) < 'A') {
                    bLastIdx++;
                }

                //숫자 구간에 대해서 숫자 정렬 함수를 실행하고
                int res = numCompare(a.substring(aIdx, aLastIdx), b.substring(bIdx, bLastIdx));
                //만약 순서가 결정된다면 그 기준에 따름
                if (res != 0) {
                    return res;
                }

                //순서가 결정되지 않는다면 숫자 구간을 뛰어넘고 계속 비교함
                aIdx = aLastIdx;
                bIdx = bLastIdx;
                continue;
            } else if (aChr > '9' && bChr > '9') {
                //만약 둘 다 문자라면 문자 비교를 진행
                if ((aChr | 32) == (bChr | 32)) {
                    //두 문자의 소문자가 동일하다면 문자의 대소를 비교해봄
                    int aLower = aChr & 32, bLower = bChr & 32;
                    if (aLower != bLower) {
                        //두 문자의 대소가 다르다면 대문자가 앞섬
                        return Integer.compare(aLower, bLower);
                    }
                } else {
                    //두 문자의 소문자가 다르다면 소문자로 변환 후 알파벳 순으로 정렬
                    return Integer.compare(aChr | 32, bChr | 32);
                }
            } else {
                //둘 중 하나가 숫자고, 하나가 문자면 숫자가 먼저 나오도록 정렬
                //숫자의 아스키코드가 문자의 아스키코드보다 작기 때문에 아래 수식으로도 정렬이 가능
                return Integer.compare(aChr, bChr);
            }

            //비교할 위치를 모두 한 칸씩 옮김
            //사실 보니까 이 코드는 else if 쪽에 넣어두고 if의 마지막에 있는 continue를 삭제해도 됨
            aIdx++;
            bIdx++;
        }

        //만약 둘 중 한 문자열을 끝까지 읽었다면 더 긴 문자열이 뒤에 오도록 정렬
        //둘 다 읽었다면 compare은 0을 반환함
        return Integer.compare(aLen - aIdx, bLen - bIdx);
    }

    static int numCompare(String a, String b) {
        //숫자는 거꾸로 읽으면서 비교함
        int aIdx = a.length() - 1, bIdx = b.length() - 1;
        int res = 0;

        //둘 중 한 문자열을 모두 읽기 전까지 반복
        while (aIdx >= 0 && bIdx >= 0) {
            //현재 위치의 두 숫자를 비교함
            //만약 0이 아니라면 tmp로 덮어씌우고, 0이면 이전의 정렬 기준을 그대로 사용
            int tmp = Integer.compare(a.charAt(aIdx), b.charAt(bIdx));
            res = tmp == 0 ? res : tmp;

            aIdx--;
            bIdx--;
        }

        //만약 a문자열을 끝까지 읽지 않았다면
        while (aIdx > -1) {
            //끝까지 읽으면서 1 이상의 숫자가 있는지 확인
            //만약에 있다면 a문자열이 b문자열보다 뒤에 있어야 함
            if (a.charAt(aIdx) > '0') {
                res = 1;
            }
            aIdx--;
        }

        //만약 b문자열을 끝까지 읽지 않았다면
        while (bIdx > -1) {
            //끝까지 읽으면서 1 이상의 숫자가 있는지 확인
            //만약에 있다면 b문자열이 a문자열보다 뒤에 있어야 함
            if (b.charAt(bIdx) > '0') {
                res = -1;
            }
            bIdx--;
        }

        //만약 정렬 순서가 정해졌으면 그대로 적용하고
        //정렬 순서가 0이면 앞에 0이 더 적은 문자열이 더 앞으로 가도록 정렬
        return res != 0 ? res : Integer.compare(a.length(), b.length());
    }   
}