// 11540 KB, 68 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 원숭이가 멍멍이를 쓰다듬기 위해 키가 같아야 한다.
 * 원숭이는 키를 늘릴 수 있다.
 * 첫째 날과 마지막 날에는 무조건 1cm 만큼.
 * 하루에 늘릴 수 있는 키의 양은 1cm 씩 조절.
 * 
 * 원숭이와 멍멍이의 키가 주어질 때, 원숭이가 키를 늘려서 멍멍이와 키가 같아지는 최소 일수
 * 0 <= X <= Y < 2^31 
 * 
 * 1일동안 늘릴 수 있는 키의 범위는 1~1 : 1
 * 2일동안 늘릴 수 있는 키의 범위는 2~2 : 1+1
 * 3일동안 .. 3~4 : 1+1+1, 1+2+1
 * 4일동안 .. 5~6 : 1+2+1+1, 1+2+2+1 
 * 5일동안 .. 7~9 : 1+2+2+1+1, 1+2+2+2+1, 1+2+3+2+1
 * 6일동안 .. 10~12 : 1+2+3+2+1+1, 1+2+3+2+2+1, 1+2+3+3+2+1
 * 7일동안 .. 13~16 : 1+2+3+3+2+1+1, 1+2+3+3+2+2+1, 1+2+3+3+3+2+1, 1+2+3+4+3+2+1
 * 8일동안 .. 17~20 : 1+2+3+4+3+2+1+1, 1+2+3+4+3+2+2+1, 1+2+3+4+3+3+2+1, 1+2+3+4+4+3+2+1
 * 
 * 숫자가 하나 늘어나면, 기존 숫자의 개수에 따라 범위가 늘어나는 형식인 걸 볼 수 있다.
 * 홀수 일 마다 늘어나는 범위가 1 증가함.
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		int diff = Integer.parseInt(input[1]) - Integer.parseInt(input[0]);

		int day = 0;
		long sum = 0;
		while (sum < diff) {
			day++;
			sum += (day + 1) / 2;
		}
		System.out.println(day);
	}
}