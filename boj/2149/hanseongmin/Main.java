import java.io.*;
import java.util.*;

/*
 * 11596KB, 68ms
 * 
 * 복호화는 암호화의 반대이므로
 * 암호화의 과정을 반대로 수행하면 된다.
 * 구현이 실버치고는 좀 복잡한 편 헷갈리는 요소들이 많다.
 */

public class Main {
	//복호화 시 원래 키의 값이 어떤 인덱스였는지 체크하기 위한 클래스
	static class KeySegment implements Comparable<KeySegment> {
		char keyChar;
		int index;
		
		public KeySegment(char keyCchar, int index) {
			this.keyChar = keyCchar;
			this.index = index;
		}

		@Override
		public int compareTo(KeySegment o) {
			return Character.compare(keyChar, o.keyChar);
		}
	}
	
	//키의 한 글자별로 지니고 있는 char[]를 담는 클래스
	static class Data implements Comparable<Data> {
		int index;
		char[] charArr;
		
		Data(int index, char[] charArr) {
			this.index = index;
			this.charArr = charArr;
		}
		
		@Override
		public int compareTo(Data o) {
			return Integer.compare(index, o.index);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//키를 입력받고
		String key = br.readLine();
		//키 세그먼트 리스트를 생성
		//stable sort를 활용하기 위해 배열이 아닌 리스트를 사용
		ArrayList<KeySegment> keySegments = new ArrayList<>();
		ArrayList<KeySegment> sortedKeySegments = new ArrayList<>();
		for (int i = 0; i < key.length(); i++) {
			keySegments.add(new KeySegment(key.charAt(i), i));
			sortedKeySegments.add(new KeySegment(key.charAt(i), i));
		}
		Collections.sort(sortedKeySegments);
		
		//암호문을 입력받고
		//행렬로 만듦
		String str = br.readLine();
		int rSize = str.length() / key.length();
		int cSize = key.length();
		char[][] mat = new char[rSize][cSize];
		for (int j = 0; j < cSize; j++) {
			for (int i = 0; i < rSize; i++) {
				mat[i][j] = str.charAt(j * rSize + i);
			}
		}
		
		//주어진 행렬을 데이터 배열로 만들고
		Data[] datas = new Data[key.length()];
		for (int j = 0; j < cSize; j++) {
			char[] temp = new char[rSize];
			for (int i = 0; i < rSize; i++) {
				temp[i] = mat[i][j];
			}
			datas[j] = new Data(sortedKeySegments.get(j).index, temp);
		}
		//정렬시켜버림
		//데이터 클래스 내부는 원래 키의 인덱스가 존재하여
		//이대로 정렬하면 키를 정렬하기 이전의 상태로 돌아감
		Arrays.sort(datas);
		
		//이후 데이터를 그대로 출력해주면 됨
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < rSize; j++) {
			for (int i = 0; i < datas.length; i++) {
				sb.append(datas[i].charArr[j]);
			}
		}
		System.out.print(sb);
	}
}