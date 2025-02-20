/**
 * 18180KB	188ms
 * 
 * [사고흐름]
 * 그래프 탐색 하면 될것 같지만, 비트마스킹으로 해보고싶었음.
 * 아래는 참고할만한 코드가 아님님
 * 
 * [알고리즘 설명]
 * 정보를 저장할 타입의 비트 수보다 많은 정보를 저장해야 할 경우, 배열로 구현하면 됨.
 * 이런 방법도 있구나.. 정도만 생각하면 좋을듯함.
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int SIZE;
	static int TYPE_SIZE = 64;
	static int REMAIN_BITS;
	static long[][] adj;
	static List<List<Integer>> groups;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        SIZE = (int) Math.ceil((double)N/TYPE_SIZE);
        REMAIN_BITS = TYPE_SIZE - (N % TYPE_SIZE);
        adj = new long[N][SIZE];
    
        for (int i=0; i<N; ++i) {
        	String s = br.readLine();
	        int level = 0;
	        int bitCnt = 0;
        	for (int j=0; j<N; ++j) {
        		adj[i][level] <<= 1;
        		if (s.charAt(j<<1) == '0') adj[i][level] |= 1L;
        		if (++bitCnt == TYPE_SIZE) {
        			bitCnt = 0;
        			level++;
        		}
        	}
        }

        groups = new ArrayList<List<Integer>>();
        for (int i=0; i<N; ++i) {
        	boolean isNew = true;
        	for (List<Integer> group: groups) {
        		if (isSame(i, group.get(0))) {
        			isNew = false;
        			group.add(i);
        			break;
        		}
        		else if (!isDiffGroup(i, group.get(0))) {
            		System.out.println(0);
            		System.exit(0);
        		}
        	}
        	if (isNew) {
        		ArrayList<Integer> newGroup = new ArrayList<>();
        		newGroup.add(i);
        		groups.add(newGroup);
        	}
        }
        
        for (List<Integer> group: groups) {
        	if (group.size() == 1) {
        		System.out.println(0);
        		System.exit(0);
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(groups.size()).append('\n');
        
        for (List<Integer> group: groups) Collections.sort(group);
        Collections.sort(groups, Comparator.comparing(e->e.get(0)));
        
        for (List<Integer> group: groups) {
        	for (int p: group) {
        		sb.append(p+1).append(' ');
        	}
        	sb.append('\n');
        }
        System.out.println(sb);
    }
    
    static boolean isSame(int a, int b) {
    	for (int i=0; i<SIZE; ++i) {
    		if (adj[a][i] != adj[b][i]) return false;
    	}
    	return true;
    }
    
    static boolean isDiffGroup(int a, int b) {
    	for (int i=0; i<SIZE; ++i) {
    		if ((adj[a][i]&adj[b][i]) > 0) return false;
    	}
    	return true;
    }
}