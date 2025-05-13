//제출번호: 94227575
//메모리:   17956 KB
//실행시간: 192 ms
import java.io.*;
import java.util.Arrays;

//키와 암호문이 주어지니까 복호화만 잘 하면 됨
//(키 알파벳, 원래 인덱스)를 암호문에 맞춰서 정렬한 다음, 원래 인덱스를 기준으로 암호 알파벳을 재배치
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        char[] key = nextString().toCharArray();
        int kLen = key.length;

        int[][] order = new int[kLen][];
        for (int i = 0; i < kLen; i++) {
            order[i] = new int[]{key[i], i};
        }
        
        //키 알파벳을 기준으로 정렬하되, 알파벳이 같으면 인덱스가 앞선게 앞으로 가도록 정렬 ==> 암호문에 맞춤
        Arrays.sort(order, (i1, i2) -> i1[0] == i2[0] ? Integer.compare(i1[1], i2[1]) : Integer.compare(i1[0], i2[0]));

        char[] cryptMessage = nextString().toCharArray();
        int cLen = cryptMessage.length, lineCnt = cLen / kLen;

        char[] decryptMessage = new char[cLen]; //복호화 문자열을 저장할 공간
        for (int i = 0; i < kLen; i++) {
            int idx = order[i][1]; //정렬된 키의 원래 위치를 가져와서
            for (int j = 0; j < lineCnt; j++) {
                //복호화 문자열의 올바른 위치에 저장함
                decryptMessage[idx + kLen * j] = cryptMessage[i * lineCnt + j];
            }
        }

        System.out.print(new String(decryptMessage));
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}