//문제: BOJ 1213번 팰린드롬 만들기
//메모리: 11528 KB
//시간: 68 ms

/*
 * 팰린드롬을 만들기 위해서는
 * 길이가 짝수일때는 모든 알파벳의 개수가 짝수여야 하고
 * 길이가 홀수일때는 하나의 알파벳의 개수가 홀수여야 하고 나머지 알파벳들의 개수가 짝수여야한다.
 * 
 * 위 조건을 만족하면 팰린드롬을 만든다.
 * 방법은 아래 코드에서 설명
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		int[] cnt = new int[27];
		for (char c : input) //입력받은 문자열에서 각 알파벳에 대해 카운트
			cnt[c & 31]++;
		int oddCnt = 0;
		int oddIdx = 0;
		for (int i = 1; i < 27; i++) {
			if ((cnt[i] & 1) == 1) { //만약 홀수개라면
				oddCnt++; //홀수인 알파벳 카운트
				oddIdx = i; //홀수인 알파벳 저장
			}
		}
		if(oddCnt>(input.length&1)) { //만약 길이가 짝수일때는 홀수개인 알파벳이 없어야하고 홀수일때는 홀수개인 알파벳이 하나여야한다.
			System.out.print("I'm Sorry Hansoo");
		} else {
			StringBuilder half = new StringBuilder(); //팰린드롬의 앞쪽 절반
			char mid = (char) (oddIdx|64); //길이가 홀수일때 중간 알파벳
			cnt[oddIdx]--; //중간 알파벳 개수 감소
			int idx = 1; //사전 순으로 가장 앞서는 것을 만들어야하므로 앞쪽 절반의 알파벳들이 정렬되어 있어야 한다.
			while(idx<27) {
				if(cnt[idx]==0) { //0개 남았다면 다음 알파벳으로
					idx++;
					continue;
				}
				half.append((char)(idx|64));
				cnt[idx]-=2; //2개씩 감소(뒤쪽에서 같은 알파벳을 사용하므로)
			}
			StringBuilder result = new StringBuilder();
			//정답은 앞쪽 절반 + (홀수라면 중간 알파벳) + 앞쪽 절반의 뒤집은 문자열
			result.append(half.toString()).append(mid==64?"":mid).append(half.reverse().toString());
			System.out.println(result);
		}
	}
}
