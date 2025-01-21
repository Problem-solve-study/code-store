import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int P = sc.nextInt();

			int cnt = 0, curr = 0;
			boolean existP = false;
			while (cnt++ < N) {
				curr += cnt;
				if (curr == P)
					existP = true;
			}

			if (existP)
				curr--;

			System.out.println(curr);
		}

		sc.close();
	}
}