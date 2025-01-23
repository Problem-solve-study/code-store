import java.util.Scanner;
public class Main {
	static final int SIZE = 6;
	static int k;
	static int[] arr;
	static int[] ans = new int[SIZE];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			k=sc.nextInt();
			if(k == 0) break;
			arr = new int[k];
			for(int i = 0 ;i<k;i++)
				arr[i]=sc.nextInt();
			backTracking(0,0);
			System.out.println();
		}
		sc.close();
	}
	
	public static void backTracking(int now, int idx) {
		if(idx==SIZE) {
			for(int i=0;i<SIZE;i++) {
				System.out.print(ans[i]+" ");
			}
			System.out.println();
			return;
		}
		if(now>=k) {return;}
		if(idx + k - now < SIZE) {return;}
		
		ans[idx]=arr[now];
		backTracking(now+1,idx+1);
		backTracking(now+1,idx);
	}
}
