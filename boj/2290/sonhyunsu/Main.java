//제출번호: 92503351
//메모리:   11544 KB
//실행시간: 68 ms
import java.io.*;

//디지털 숫자 노가다
//lcd[7][] 배열로 출력해야 하는 라인을 체크했음
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int s = (int) nextLong();
		long n = nextLong();

		boolean[][] lcd = new boolean[7][10];
		int i = 0;
		while (n > 0) {
            //각 숫자마다 어느 라인을 켜야하는 지 체크함
            //  0
            //1   2
            //  3
            //4   5
            //  6
			switch ((int) (n % 10)) {
				case 0: 
					lcd[0][i] = lcd[1][i] = lcd[2][i] = lcd[4][i] = lcd[5][i] = lcd[6][i] = true;
				break;
				
				case 1: 
					lcd[2][i] = lcd[5][i] = true;
				break;

				case 2: 
					lcd[0][i] = lcd[2][i] = lcd[3][i] = lcd[4][i] = lcd[6][i] = true;
				break;
				
				case 3: 
					lcd[0][i] = lcd[2][i] = lcd[3][i] = lcd[5][i] = lcd[6][i] = true;
				break;
				
				case 4: 
					lcd[1][i] = lcd[2][i] = lcd[3][i] = lcd[5][i] = true;
				break;
				
				case 5: 
					lcd[0][i] = lcd[1][i] = lcd[3][i] = lcd[5][i] = lcd[6][i] = true;
				break;
				
				case 6: 
					lcd[0][i] = lcd[1][i] = lcd[3][i] = lcd[4][i] = lcd[5][i] = lcd[6][i] = true;
				break;
				
				case 7: 
					lcd[0][i] = lcd[2][i] = lcd[5][i] = true;
				break;
				
				case 8: 
					lcd[0][i] = lcd[1][i] = lcd[2][i] = lcd[3][i] = lcd[4][i] = lcd[5][i] = lcd[6][i] = true;
				break;
				
				case 9: 
					lcd[0][i] = lcd[1][i] = lcd[2][i] = lcd[3][i] = lcd[5][i] = lcd[6][i] = true;
				break;
			}

			n /= 10;
			i++;
		}

        //출력한 그림판을 만듦
		int h = (2 * s + 3);
		int w = (s + 3) * i;
		char[][] map = new char[h][w];

        //모든 판을 스페이스 바로 채움
		for (int x = 0; x < h; x++) {
			for (int y = 0; y < w; y++) {
				map[x][y] = ' ';
			}
		}

		for (int pos = 0; pos < i; pos++) {
			int idx = i - 1 - pos;
			int yPos = pos * (s + 3);

            //가로 줄 긋는 코드
			for (int cnt = 0, xPos = 0; cnt < 7; cnt += 3, xPos += s + 1) {
				if (lcd[cnt][idx]) {
					for (int t = 1; t <= s; t++) {
						map[xPos][yPos + t] = '-';
					}
				}
			}

            //왼쪽 세로 줄 긋는 코드
			for (int cnt = 1, xPos = 1; cnt < 7; cnt += 3, xPos += s + 1) {
				if (lcd[cnt][idx]) {
					for (int t = 0; t < s; t++) {
						map[xPos + t][yPos] = '|';
					}
				}
			}

            //오른쪽 세로 줄 긋는 코드
			yPos += s + 1;
			for (int cnt = 2, xPos = 1; cnt < 7; cnt += 3, xPos += s + 1) {
				if (lcd[cnt][idx]) {
					for (int t = 0; t < s; t++) {
						map[xPos + t][yPos] = '|';
					}
				}
			}
		}

        //만들어진 그림판을 출력함
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < h; x++) {
			for (int y = 0; y < w; y++) {
				sb.append(map[x][y]);
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}

	static long nextLong() throws IOException {
		st.nextToken();
		return (long) st.nval;
	}
}
