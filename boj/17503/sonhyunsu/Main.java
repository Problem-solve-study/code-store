//제출번호: 92674111
//메모리:   33812 KB
//실행시간: 908 ms
import java.io.*;
import java.util.*;

//문제를 보고 N, M, K의 범위를 보자마자 매개변수탐색을 써서 풀어야겠다고 생각함
//mid 레벨에 대해서 먹을 수 있는 맥주의 선호도 최댓값을 가져오면 됨
//N개의 맥주에 대해서 선호도가 최대가 되도록 선택하기 위해서는
//그리디 하게 선호도가 가장 높은 순서대로 하나씩 골라보면 됨 
//반드시 N개의 맥주를 선택해야 한다는 것에 주의할 것
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    long m = nextInt();
	    int k = nextInt();
	    
	    int[][] beers = new int[k][2];
	    for (int i = 0; i < k; i++) {
	        beers[i][0] = nextInt();
	        beers[i][1] = nextInt();
	    }
	    
        //선호도를 기준으로 비오름차순으로 정렬
	    Arrays.sort(beers, (i1, i2) -> -Integer.compare(i1[0], i2[0]));
	    
        //선호도가 가장 높은 N개의 맥주를 골라봄
	    long sum = 0;
	    for (int i = 0; i < n; i++) {
	        sum += beers[i][0];
	    }
	    
	    long res = 0;
        //만약에 N를 골라도 목표 선호도를 만들 수 없다면 아예 불가능한 경우임
	    if (sum < m) {
	        res = -1;
	    } else {
            //만들 수 있다면 답이 존재하므로 매개변수 탐색을 진행
            long start = 0, end = Integer.MAX_VALUE, mid;
	        
	        while (start <= end) {
	            mid = (start + end) >> 1;
	            
	            int selected = 0; //맥주를 선택한 개수
	            sum = 0; //선호도 총합
	            for (int i = 0; i < k && selected < n; i++) {
                    //맥주의 도수레벨보다 간 레벨이 크거나 같다면 마실 수 있음
	                if (beers[i][1] <= mid) {
	                    selected++;
	                    sum += beers[i][0];
	                }
	            }
	            
                //만약 N개를 선택하지 못 했거나, 선택했는데 목표 선호도보다 작다면
	            if (selected < n || sum < m) {
                    //간 레벨을 더 키워봄
	                start = mid + 1;
	            } else {
                    //아니라면 간 레벨을 더 낮춰 봄
	                end = mid - 1;
	            }
	        }
	        
            //start가 목표 선호도를 만들 수 있는 간 레벨의 최솟값을 가지고 있음
	        res = start;
	    }
	    
	    System.out.print(res);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}