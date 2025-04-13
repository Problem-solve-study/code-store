//Memory : 14468KB Time : 124ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;


// 바꿀 때 최대한 다음 배치에 유리하도록 (각 도형의 자리에 맞도록)
//1번에 존재하는 2 & 2번에 존재하는 1 => SWAP
//1번에 존재하는 3 & 3번에 존재하는 1 => SWAP
//2번에 존재하는 3 & 3번에 존재하는 2 => SWAP
//2번째의 SWAP 횟수 = 원래 2번에 존재하던 3 + (2번에 존재하는 1 - 1번에 존재하는 2)

public class Main{
	static int n;
	static int[] shapes;
	static int[] shapeCnt = new int[4];
	static int[][] cnts = new int[4][4]; //첫 배치 도형 기준 counting 저장
	static void input() throws IOException {
		n = nextInt();
		shapes = new int[n];
		for(int i =0;i<n;i++) {
			shapes[i] = nextInt();
			shapeCnt[shapes[i]]++;
		}
	}
	
	static int getCnt(int first, int second) {
		int third;
		if(first != 1 && second != 1)
			third =1;
		else if(first != 2 && second != 2)
			third =2;
		else
			third =3;
		
		
		int cnt = shapeCnt[first];
		int[] count = new int[4];
		for(int i =0;i<cnt;i++) {
			if(shapes[i]!= first) {
				count[shapes[i]]++;
			}
		}
			
		cnt += shapeCnt[second];
		for(int i =shapeCnt[first];i<cnt;i++) {
			if(shapes[i]!= second) {
				count[shapes[i]]++;
			}
		}
		
		return Math.max(count[first], count[second]) + count[third]; 
	}
	
	static void solution() {
		int min = Integer.MAX_VALUE;
		for(int i =1;i<4;i++) {
			for(int j = 1;j<4;j++) {
				if(i==j) continue;
				min = Math.min(min, getCnt(i,j));
			}
		}
		System.out.println(min);
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
	
	static int nextInt() throws IOException {
        int n = System.in.read() & 15;
        int c = System.in.read();
        
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        
        return n;
	}
}