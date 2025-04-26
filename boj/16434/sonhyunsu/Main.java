//제출번호: 93609910
//메모리:   15824 KB
//실행시간: 200 ms
import java.io.*;

//문제를 보고 최대 체력을 설정하지 않은 채로 시뮬레이션을 돌린 후 필요한 최소의 최대 체력을 구하면 될 것 같았음
//초기 체력을 0으로 잡고, 층을 돌파하면서 만들어지는 음수로 내려가는 체력들 중 가장 작은 값을 가져오면 필요한 최소의 최대 체력을 구할 수 있음
//나머지는 다 long 처리 했는데 용사의 공격력을 long 처리하지 않아서 1틀
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    long heroA = nextInt(); //용사의 초기 공격력 설정
	    long curH = 0; //현재 체력
	    long needH = 0; //필요한 최대 체력
	    
	    for (int i = 0; i < n; i++) {
	        int type = nextInt();
	        if (type == 1) {
	            long enemyA = nextInt();
	            long enemyH = nextInt();
	            
				//용사가 몬스터를 죽이기 위해 필요한 공격 횟수를 구함
	            long attackCount = enemyH / heroA + (enemyH % heroA != 0 ? 1 : 0);

				//그리고 무찌른 후 깎이는 체력을 구하고
	            curH -= enemyA * (attackCount - 1);

				//현재 층까지 돌파하기 위한 체력 중 최댓값을 needH에 업데이트 함 
	            needH = Math.min(needH, curH); 
	        } else {
	            heroA += nextInt();
	            curH += nextInt();
	            
				//0을 넘어서는 체력은 필요 없으므로 버림
	            if (curH > 0) {
	                curH = 0;
	            }
	        }
	        
	    }
	    
		//층을 돌파하면서 최대 -needH만큼 체력이 떨어질 수 있기 때문에 필요한 최소의 최대 체력은 -needH + 1이 됨
	    System.out.print(-needH+1);
	} 
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}