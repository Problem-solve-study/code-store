import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int test_case = 0;test_case<T;test_case++) {
			int n = sc.nextInt();
			int[] arr = new int[n];
			for(int i=0;i<n;i++) {arr[i]=sc.nextInt();}
			ArrayList<Integer> al = new ArrayList<Integer>();
			for(int i=1;i<=n;i++) {al.add(i);}
			boolean impossible = false;
			for(int i=n-1;i>=0;i--) {
				if(arr[i]>=al.size()) {
					impossible=true;
					break;
				}
				arr[i]=al.get(arr[i]);
				al.remove(Integer.valueOf(arr[i]));
			}
			if(impossible) { System.out.println("IMPOSSIBLE");
			} else {
				for(int i=0;i<n;i++) {System.out.printf("%d ",arr[i]);}
				System.out.println();
			}
		}
		
		sc.close();
	}
}
