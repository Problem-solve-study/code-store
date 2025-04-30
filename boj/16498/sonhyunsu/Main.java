//제출번호:	93735956
//메모리:	51556 KB
//실행시간:	1008 ms
import java.io.*;
import java.util.*;

//A, B, C의 카드 중 2개를 먼저 고르고, 그게 min, max가 될 수 있는 나머지 하나가 있는지 확인해보고
//있으면 선택된 2개의 카드로 res를 업데이트
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int a = nextInt();
        int b = nextInt();
        int c = nextInt();
        
        TreeSet<Integer> aCards = new TreeSet<>();
        TreeSet<Integer> bCards = new TreeSet<>();
        TreeSet<Integer> cCards = new TreeSet<>();

        for (int i = 0; i < a; i++) {
            aCards.add(nextInt());
        }
        for (int i = 0; i < b; i++) {
            bCards.add(nextInt());
        }
        for (int i = 0; i < c; i++) {
            cCards.add(nextInt());
        }


		//a, b 중 카드를 2개 고르고 c에서 가능한지 확인
		//b, c 중 카드를 2개 고르고 a에서 가능한지 확인
        int res = Math.min(find(aCards, bCards, cCards), find(bCards, cCards, aCards));
		//a, c 중 카드를 2개 고르고 b에서 가능한지 확인
        res = Math.min(res, find(cCards, aCards, bCards));
        
        System.out.print(res);
    }

    static int find(TreeSet<Integer> cards1, TreeSet<Integer> cards2, TreeSet<Integer> cards3) {
        int res = Integer.MAX_VALUE;

        for (int num1 : cards1) {
            for (int num2 : cards2) {
				//2개를 고르고 나머지 하나의 덱에서 그 2개를 min, max로 만들어주는 숫자가 있는 지 확인
                Integer min = cards3.higher(num1-1); //-1을 해야 num1을 포함함
                Integer max = cards3.lower(num2+1); //+1을 해야 num2를 포함함

                if (min != null && max != null && min <= max) {
                    res = Math.min(res, Math.abs(num1 - num2));
                }

                min = cards3.higher(num2-1);
                max = cards3.lower(num1+1);

                if (min != null && max != null && min <= max) {
                    res = Math.min(res, Math.abs(num1 - num2));
                }
            }
        }

        return res;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}