import java.io.*;
import java.util.*;

/*
 * 20932KB, 216ms
 * 
 * 스택으로 시뮬레이션해보고 되면 출력, 안되면 NO 출력하면 되겠다 싶었음
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int n = nextInt();
		//마지막에 몇 번째 숫자까지 저장되었는지를 저장
		int lastPushNum = 0;
		boolean isNo = false;
		ArrayDeque<Integer> d = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int cur = nextInt();
			//현재 입력된 수가 마지막에 저장된 수보다 크다면 스택에 푸시 
			while (lastPushNum < cur) {
				d.add(++lastPushNum);
				sb.append('+').append('\n');
			}
			
			//이후 peek 해보고 꺼낼 수 있으면 꺼냄
			if (d.peekLast() == cur) {
				d.removeLast();
				sb.append('-').append('\n');
			} else { //이때 못꺼낸다면 안되는 경우
				isNo = true;
				break;
			}
		}
		
		if (isNo) {
			System.out.print("NO");
		} else {
			System.out.print(sb);
		}
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}