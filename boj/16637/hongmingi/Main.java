import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n, maxValue, len;
	static int[][] map;
    static char[] formula;
    static boolean[] visited;
    //int pipe[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        formula = br.readLine().toCharArray();
        
        maxValue = Integer.MIN_VALUE;
        len = formula.length;

        findMax(0, formula[0]-'0');    
        System.out.println(maxValue);    
     
	}	
    public static int calculate(int x, char op, int y) {
    	int ans = 0;
    	switch(op){
    	case '+':
    		ans = x+y;
    		break;
    	case '-':
    		ans = x-y;
    		break;
    	case '*':
    		ans = x*y;
    		break;
    	}
    	return ans;
    }
    static void findMax(int idx, int partSum){
        if(idx==n-1){
            maxValue = Math.max(maxValue, partSum);
        }
        else{
            findMax(idx+2, calculate(partSum, formula[idx+1], formula[idx+2]-'0'));
            if(idx<=n-5){
                int partSum2 = calculate(formula[idx+2]-'0', formula[idx+3], formula[idx+4]-'0');
                findMax(idx+4, calculate(partSum, formula[idx+1], partSum2));
            }
        }
    }
}

