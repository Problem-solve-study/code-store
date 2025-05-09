//제출번호: 94068201
//메모리:   11736 KB
//실행시간: 68 ms
import java.io.*;

//모든 소리에서 다른 동물들의 소리를 지운 다음 출력하면 되는 문제
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String[] sounds = br.readLine().split(" "); //모든 동물의 소리를 기록
            String line;
            //마지막 질문이 들어오기 전까지
            while (!"what does the fox say?".equals((line = br.readLine()))) {
                String sound = line.split(" ")[2]; //동물 소리를 가져와서
                for (int i = 0; i < sounds.length; i++) {
                    //sounds 배열에 해당 동물 소리가 있으면 삭제
                    if (sound.equals(sounds[i])) {
                        sounds[i] = "";
                    }
                }
            }

            for (String sound : sounds) {
                //남은 동물 소리를 sb에 담음
                if (sound.length() > 0) {
                    sb.append(sound).append(' ');
                }
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}