import java.util.Scanner;

public class Solution{
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int P = sc.nextInt();
            int maxHeight = 0;

            for (int i = 1; i <= N; i++)
            {
                maxHeight += i; // 도달가능한 최대 층의 합
                if (maxHeight == P) // 도달한 최대 층이 폭탄이 터지는 층일 때
                    maxHeight -= 1; // 제일 낮은 층인 i = 1, 즉 1층의 값을 제외
            }
            System.out.println(maxHeight);
        }
    }
}