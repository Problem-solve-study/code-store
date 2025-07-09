import java.io.*;

/**
 * 	11492KB	64ms
 *  2진수 비트마스킹 -- K보다 큰 가장 작은 2의제곱수 찾기
 *  부수는 횟수 = LSB - bit의 지수 
 */

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        int bit = 1; // 현재 검사 중인 2의 거듭제곱
        int min = 0; // LSB
        boolean flag = false;
        int i = 0; // bit 의 지수

        for(i = 0; bit < K; i++){
            if(!flag && (bit & K) == bit){
                min = i;
                flag = true;
            }
            bit *= 2;
        }

        if(bit == K) i = min; // 거듭제곱 그자체면 부수는 횟수 0

        System.out.println(bit + " " + (i - min));
    }
}