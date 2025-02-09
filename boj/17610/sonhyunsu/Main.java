//제출번호: 89810934
//메모리:   57688 KB
//실행시간: 428 ms
import java.util.*;
import java.io.*;

//문제를 보고 추의 개수(=K)가 매우 작은 것을 보고 완전탐색을 해도 되겠다고 판단. (3^13 == 1,594,323)
//모든 경우를 살펴본 다음 Set에 집어 넣으면 중복을 제거하면서 측정이 가능한 무게의 개수를 셀 수 있음
//S는 모든 추의 무게 합이니까 (S - 측정가능 무게)를 하면 정답을 구할 수 있음
//TreeSet을 사용하니 시간 초과 남, HashSet으로 변경해서 해결
public class Main {
    
    static int k;
    static int[] weights;
    static Set<Integer> availableWeights = new HashSet<>();
    
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    k = Integer.parseInt(br.readLine());
	    weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	    
	    bruteForce(0, 0);
	    
	    int s = Arrays.stream(weights).sum();
	    System.out.print(s - availableWeights.size());
	}
	
    //물은 항상 왼쪽 저울에 매단다고 가정한다.
	static void bruteForce(int i, int weight) {
	    if (i == k) {
	        if (weight > 0) { //불가능한 무게는 제외한다.
	            availableWeights.add(weight);
	        }
	        
	        return;
	    }
	    
	    bruteForce(i + 1, weight - weights[i]); //무게를 빼면 왼쪽 저울에 추를 올려 놓는 경우
	    bruteForce(i + 1, weight);              //추를 제외하는 경우
	    bruteForce(i + 1, weight + weights[i]); //무게를 더하면 오른쪽 저울에 추를 올려 놓는 경우
	}
}