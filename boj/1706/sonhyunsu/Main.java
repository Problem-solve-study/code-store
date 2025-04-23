//제출번호: 93474538
//메모리:   11572 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//퍼즐맵을 보고 단어를 찾은 다음, 잘 정렬해서 보여주면 됨
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] map = new char[r][];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
		//가로 기준으로 만들어진 단어들을 리스트에 넣음
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == '#') {
					//sb에 저장된 문자열이 2글자 이상이라면 단어가 됨
                    if (sb.length() > 1) {
                        words.add(sb.toString());
                    }
                    sb.delete(0, sb.length());
                } else {
                    sb.append(map[i][j]);
                }
            }

			//sb에 저장된 문자열이 2글자 이상이라면 단어가 됨
            if (sb.length() > 1) {
                words.add(sb.toString());
            }
            sb.delete(0, sb.length());
        }
        
		//세로 기준으로 만들어진 단어들을 리스트에 넣음
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                if (map[i][j] == '#') {
                    if (sb.length() > 1) {
                        words.add(sb.toString());
                    }
                    sb.delete(0, sb.length());
                } else {
                    sb.append(map[i][j]);
                }
            }

            if (sb.length() > 1) {
                words.add(sb.toString());
            }
            sb.delete(0, sb.length());   
        }

		//사전 순으로 정렬해서
        words.sort(Comparator.naturalOrder());

		//가장 앞에 있는 단어 출력
        System.out.print(words.get(0));
    }
}