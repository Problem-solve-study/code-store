//문제: BOJ 2002번 추월
//메모리: 12024 KB
//시간: 72 ms

/*
 * 들어간 차량의 번호 n개를 순서대로 저장하고
 * 나오는 차량의 번호들과 비교를 한다.
 * 만약, 들어간 차량의 번호와 다른 번호의 차량이 먼저 나온다면 추월 차량이고
 * 추월 차량을 이후에 다른 차량과 비교하면 안되므로 들어간 차량 번호에서 삭제해준다.
 */

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		List<String> list = new ArrayList<>(); //들어간 차량 번호를 저장
		for (int i = 0; i < n; i++) 
			list.add(br.readLine());

		int result = 0;
		int idx = 0;
		while (n-->0) {
			String input = br.readLine();
			if(!list.get(idx).equals(input)) { //만약, 나와야할 차량과 실제로 나온 차량의 번호가 다르다면(추월한 차량)
				list.remove(input); //들어간 차량 번호에서 현재 나온 차량 삭제
				result++; //추월 차량 카운트
				continue;
			}
			idx++; //정상적이라면 idx증가
		}

		System.out.println(result);
	}
}
