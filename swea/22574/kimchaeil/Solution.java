import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int n = sc.nextInt(), p = sc.nextInt(), result = 0;
            for(int i=1;i<=n;i++){
                result+=i;
                if (result == p) result--;
            }
            System.out.println(result);
		}
		sc.close();
	}
}