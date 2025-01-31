import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int chicken=1, people=2;
		
		int[] min = new int[n+1];
		int[] max = new int[n+1];
		
		for(int i=1;i<n+1;i++) {
			min[i]=999_999_999;
		}
		
		while(people<=n) {
			for(int i=people;i<=n;i++) {
				min[i]=Math.min(min[i], min[i-people]+chicken);
				max[i]=Math.max(max[i], max[i-people]+chicken);
			}
			int temp = people;
			people+=chicken;
			chicken=temp;
		}
		
		System.out.printf("%d %d",min[n],max[n]);
		
		sc.close();
	}
}
