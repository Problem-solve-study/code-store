//메모리:215632KB
//시간:1068ms
import java.math.BigInteger;
import java.util.*;
public class Main {
	static class Vec{
		int x, y;
		public Vec(int x, int y) {
			int gcd=BigInteger.valueOf(x).gcd(BigInteger.valueOf(y)).intValue();
			this.x=x/gcd;
			this.y=y/gcd;
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vec other = (Vec) obj;
			return x == other.x && y == other.y;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		HashMap<Vec,Integer> hm = new HashMap<>();
		int max=0;
		for(int i=0;i<n;i++) {
			Vec vec = new Vec(sc.nextInt(),sc.nextInt());
			hm.put(vec, hm.getOrDefault(vec, 0)+1);
			max=Math.max(max, hm.get(vec));
		}
		System.out.println(max);
		sc.close();
	}
}
