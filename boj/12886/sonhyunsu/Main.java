//제출번호: 93692407
//메모리:   161812 KB
//실행시간: 1360 ms
import java.io.*;
import java.util.*;

//500*500*500이 1.25억 정도 되어 보여서 그냥 깡으로 돌았는데 통과함..
//visited를 500*500*500으로 만들 수는 없으니까 Set에 담아두고, Set에 있으면 방문했다고 처리
//실제로 나올 수 있는 값은 최대 998이어서 인덱스는 좀 더 느슨하게 구성
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.out.print(backtrack(nextInt(), nextInt(), nextInt()) ? 1 : 0);
    }

    static boolean backtrack(int a, int b, int c) {
		//셋 다 똑같으면 성공
        if (a == b && b == c) {
            return true;
        }

		//a, b, c를 가지고 인덱스 생성
        int idx = ((a-1) * 1001 + b-1) * 1001 + c-1;
        if (visited.contains(idx)) {
			//방문했던 점이면 불가능한 것
            return false;
        }

        visited.add(idx);

        boolean isPossible = false;
		//a와 c에 대해서 비교
        if (a > c) {
            isPossible |= backtrack(a - c, b, c << 1);
        } else if (a < c) {
            isPossible |= backtrack(a << 1, b, c - a);
        }

		//성공한 경우가 있으면 바로 리턴
        if (isPossible) {
            return true;
        }

		//b와 c에 대해서 비교
        if (b > c) {
            isPossible |= backtrack(a, b - c, c << 1);
        } else if (b < c) {
            isPossible |= backtrack(a, b << 1, c - b);
        }

		//성공한 경우가 있으면 바로 리턴
        if (isPossible) {
            return true;
        }
        
		//a와 b에 대해서 비교
        if (a > b) {
            isPossible |= backtrack(a - b, b << 1, c);
        } else if (a < b) {
            isPossible |= backtrack(a << 1, b - a, c);
        }

		//결과 리턴
        return isPossible;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}