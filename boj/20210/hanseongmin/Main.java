import java.io.*;
import java.math.BigInteger;
import java.util.*;

/*
 * 181136KB, 808ms
 * 
 * 구현 문제라서 생각나는대로 구현만 하면 된다.
 * 근데 실수할만한 건덕지가 많아서 조심해야했던 문제
 * 5번 조건(같은 값을 가지면 앞에 따라붙는 0의 개수가 적은 것이 앞에 온다)의 조건을
 * 잘못 설정해서 1틀
 */

public class Main {
    //정렬의 조건이 다르므로 정렬의 기준을 저장해둘 map
	static HashMap<String, Integer> map = new HashMap<>();
	static {
		int idx = 0;
		
        //숫자가 제일 큰 우선순위
		for (int i = 0; i <= 9; i++) {
			map.put(String.valueOf(i), idx++);
		}
		
        //이후 알파벳 대문자 -> 소문자순의 우선순위
		for (int i = 0; i < 26; i++) {
			map.put(String.valueOf((char) (i + 'A')), idx++);
			map.put(String.valueOf((char) (i + 'a')), idx++);
		}
	}
	
    //이런 문제는 클래스로 따로 식별하는 편이 편해서 클래스 정의
	static class FileName implements Comparable<FileName> {
        //String의 덩어리를 저장해둘 리스트
        //str123ing이 들어오면 str | 123 | ing으로 끊어서 저장한다
		ArrayList<String> nameList = new ArrayList<>();

        //파일 이름을 문자열로 입력받고
        //적절히 파싱하여 nameList에 저장
		public FileName(String name) {
			StringBuilder sb = new StringBuilder();
			
			boolean digit = Character.isDigit(name.charAt(0));
			for (int i = 0; i < name.length(); i++) {
				char curC = name.charAt(i);
				if (Character.isDigit(curC) && !digit) {
					nameList.add(sb.toString());
					sb = new StringBuilder();
					digit = true;
				} else if (Character.isAlphabetic(curC) && digit) {
					nameList.add(sb.toString());
					sb = new StringBuilder();
					digit = false;
				}
				sb.append(curC);
			}
			
			if (sb.length() != 0) {
				nameList.add(sb.toString());
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (String s : nameList) {
				sb.append(s);
			}
			return sb.toString();
		}
		
        //문자열 끼리의 비교
		private int strCompare(String s1, String s2) {
			for (int i = 0; i < s1.length(); i++) {
				if (i >= s2.length()) {
					return 1;
				}
				
                //문자열 내부의 문자를 하나씩 가져와서 map의 정렬 기준을 가져오고
                //그것을 토대로 값 반환
				int thisV = map.get(String.valueOf(s1.charAt(i)));
				int oV = map.get(String.valueOf(s2.charAt(i)));
				int comp = Integer.compare(thisV, oV);
				if (comp != 0) {
					return comp;
				}
			}
			
			if (s1.length() < s2.length()) {
				return -1;
			}
			return 0;
		}
		
        //숫자끼리의 비교
		private int numberCompare(String s1, String s2) {
            //long의 범위를 벗어날 수 있으므로 BigInteger 사용
			BigInteger n1 = new BigInteger(s1);
			BigInteger n2 = new BigInteger(s2);
			
            //일단 비교해보고
			int comp = n1.compareTo(n2);
            //같은 값이라면 혹시 앞에 0이 있을 수 있으니 이를 검사
			if (comp == 0) {
				if (s1.length() > s2.length()) {
					return 1;
				} else if (s1.length() < s2.length()) {
					return -1;
				}
			}
			
			return comp;
		}

		@Override
		public int compareTo(FileName o) {
			for (int i = 0; i < nameList.size(); i++) {
				//this가 더 긴 경우
				if (i >= o.nameList.size()) {
					return 1;
				}
				
				int comp = 0;
                //숫자열끼리는 다른 정렬 조건을 적용
				if (Character.isDigit(nameList.get(i).charAt(0)) &&
						Character.isDigit(o.nameList.get(i).charAt(0))
						) {
					comp = numberCompare(nameList.get(i), o.nameList.get(i));
				} else {
                    //그게 아니면 문자열끼리의 비교로 간주하여 문자열 비교
					comp = strCompare(nameList.get(i), o.nameList.get(i));
				}
				
				if (comp != 0) {
					return comp;
				}
			}
			
			//o가 더 긴 경우
			if (nameList.size() < o.nameList.size()) {
				return -1;
			}
			
			return 0;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		FileName[] fileNames = new FileName[N];
        //파일 이름을 입력받고
		for (int i = 0; i < N; i++) {
			fileNames[i] = new FileName(br.readLine());
		}
        //정렬한 뒤
		Arrays.sort(fileNames);
		
        //출력
		StringBuilder sb = new StringBuilder();
		for (FileName fn : fileNames) {
			sb.append(fn).append('\n');
		}
		System.out.print(sb);
	}
	
}