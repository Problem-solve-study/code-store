/**
 * 17872KB	292ms
 * 
 * [사고 흐름]
 * DP로 구간 별 문자 개수를 구해두고 파라메트릭 서치를 하면 될 것이라고 생각했는데,
 * 이렇게 하면 너무 어려워져서 태그를 확인했습니다.
 * 
 * 각 문자별로 슬라이딩 윈도우를 이용해서 구간 길이를 구하는 방식입니다.
 * 100 * 26 * 10,000 이므로 안정적으로 통과합니다.
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
    	int tcSize = ni();
    	
    	StringBuilder res = new StringBuilder();
    	for (int tc=0; tc<tcSize; ++tc) {
    		char[] w = ns().toCharArray();
    		int k = ni();
    		int min = Integer.MAX_VALUE;
    		int max = Integer.MIN_VALUE;
    		for (int i=0; i<26; ++i) {
    			int[] cur = getMinMax(w, k, (char)('a'+i));
    			min = Math.min(min, cur[0]);
    			max = Math.max(max, cur[1]);
    		}
    		if (min != Integer.MAX_VALUE) {
    			res.append(min).append(' ').append(max).append('\n');
    		} else {
    			res.append(-1).append('\n');
    		}
    	}
    	System.out.print(res);
    }
    
    static int[] getMinMax(char[] w, int k, char t) {
    	int l = 0;
    	int r = -1;
    	int cnt = 0;
    	int[] res = {Integer.MAX_VALUE, Integer.MIN_VALUE};
    	while (l<w.length && r<w.length) {
    		while (cnt<k && ++r<w.length && w[r]==t) ++cnt;
    		while (l<w.length && w[l]!=t) ++l;
    		if (cnt==k) {
    			res[0] = Math.min(res[0], r-l+1);
    			res[1] = Math.max(res[1], r-l+1);
    			++l;
    			--cnt;
    		}
    	}
    	return res;
    }
    
    static int ni() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
    
    static String ns() throws Exception {
    	st.nextToken();
    	return st.sval;
    }
}