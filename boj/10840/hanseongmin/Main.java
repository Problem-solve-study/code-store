import java.io.*;
import java.math.BigInteger;
import java.util.*;

/*
 * 327976KB, 932ms
 * 
 * DP? 누적합? 해시? 세그먼트 트리? 처음에 어떤 태그인지 몰라서 많이 헤맸다.
 * 결국 태그를 보고 해시인걸 캐치하고 해시로 쭉 구현을 했는데 해시인걸 알아도 꽤나 어려운 편인듯
 * 각 알파벳을 적당한 소수로 매핑을 시켜두고 라빈-카프 같은 롤링 해시를 구현한다.
 * 문자열의 길이가 꽤 길어서 해시 충돌은 반드시 일어날 수 밖에 없어보여 해시값을 충돌했을 때를 대비하여 알파벳 개수를 세서
 * 정말로 동일한 성분일 경우에만 체크한다.
 */

public class Main {
	static String str1;
	static String str2;
	static TreeMap<Long, ArrayList<Data>> values = new TreeMap<>();
	static HashMap<Integer, Long> map = new HashMap<>();
	static long[][] hash1;
	static long[][] hash2;
	static class Data {
		long hash;
		int s;
		int e;
		
		Data(long h, int s, int e) {
			hash = h;
			this.s = s;
			this.e = e;
		}
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	//먼저 각 알파벳 별로 소수를 배정한다. 소수 판정은 BigInteger의 isProbablePrime 사용
    	int lastPrime = 1;
    	for (int i = 0; i < 26; i++) {
    		for (int j = lastPrime + 1; true; j++) {
    			if (BigInteger.valueOf(j).isProbablePrime(10)) {
    				map.put(i, (long) j);
    				lastPrime = j + 10000;
    				break;
    			}
    		}
    	}
    	
    	str1 = br.readLine();
    	str2 = br.readLine();
    	//구간의 해시값 저장 hash[i][j]: i ~ j 구간에서의 해시값
    	hash1 = new long[str1.length() + 1][str1.length() + 1];
    	hash2 = new long[str2.length() + 1][str2.length() + 1];
    	makeHash(str1, hash1);
    	makeHash(str2, hash2);
    
    	int res = 0;
    	int minLen = Math.min(str1.length(), str2.length());
    	//최대 길이 탐색
    	for (int i = 1; i <= minLen; i++) {
    		values = new TreeMap<>();
    		if (find(i)) res = i;
    	}
    	System.out.println(res);
    }
    
    //롤링해시 구현. 이전 구간에서 현재 문지열의 해시 값을 더해줌
    static void makeHash(String str, long[][] hash) {
    	for (int i = 1; i <= str.length(); i++) {
    		for (int j = i; j <= str.length(); j++) {
    			hash[i][j] = hash[i][j - 1] + map.get(str.charAt(j - 1) - 'a');
    		}
    	}
    }
    
    //해시 값을 비교하여 구간 성분 탐색
    static boolean find(int len) {
    	//먼저 str1의 해시 값을 모두 맵에 저장
    	for (int i = 1; i + len - 1 <= str1.length(); i++) {
    		values.putIfAbsent(hash1[i][i + len - 1], new ArrayList<>());
    		ArrayList<Data> list = values.get(hash1[i][i + len - 1]);
    		list.add(new Data(hash1[i][i + len - 1], i, i + len - 1));
    	}
    	
    	//이후 str2의 구간을 보며 동일 성분을 판별. 해시 값이 같다면 실제로 해당 두 구간의 알파벳 개수를 비교
    	for (int i = 1; i + len - 1 <= str2.length(); i++) {
    		if (values.containsKey(hash2[i][i + len - 1]) && check(values.get(hash2[i][i + len - 1]), i, i + len - 1)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    static boolean check(ArrayList<Data> d1, int s, int e) {
    	int[] arr2 = new int[26];
    	for (int i = s; i <= e; i++) {
    		arr2[str2.charAt(i - 1) - 'a']++;
    	}
    	
    	for (Data d : d1) {
    		int[] arr1 = new int[26];
    		for (int i = d.s; i <= d.e; i++) {
        		arr1[str1.charAt(i - 1) - 'a']++;
        	}
    		
    		boolean same = true;
        	for (int i = 0; i < 26; i++) {
        		if (arr1[i] != arr2[i]) {
        			same = false;
        			break;
        		}
        	}
        	
        	if (same) {
        		return true;
        	}
    	}
    	
    	return false;
    }
}