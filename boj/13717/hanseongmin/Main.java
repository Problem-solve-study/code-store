import java.io.*;
import java.util.*;

/*
 * 11728KB, 64ms
 * 
 * 그냥 문제로 주어진대로 구현하기
 */

public class Main {
	static class Pokemon {
		String name;
		int needCandy;
		int candyCnt;
		int evolCnt;
		
		public Pokemon(String name, int needCandy, int candyCnt) {
			super();
			this.name = name;
			this.needCandy = needCandy;
			this.candyCnt = candyCnt;
			this.evolCnt = 0;
		}
		
		public void evol() {
			while (needCandy <= candyCnt) {
				candyCnt -= needCandy;
				candyCnt += 2;
				evolCnt++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int totalCnt = 0;
		int resCnt = 0;
		String res = "";
		for (int i = 0; i < N; i++) {
			String name = br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Pokemon p = new Pokemon(name, a, b);
			p.evol();
			totalCnt += p.evolCnt;
			
			if (p.evolCnt > resCnt) {
				resCnt = p.evolCnt;
				res = p.name;
			}
		}
		
		System.out.print(totalCnt + "\n" + res);
	}
}