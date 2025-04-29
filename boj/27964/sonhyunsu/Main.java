//제출번호: 93691972
//메모리:   11656 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//그냥 접미사가 Cheese인 문자열들을 Set에 담아서 중복을 제거하고, Set의 크기가 4 이상인지만 확인하면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        Set<String> cheeseSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String cheese = nextString();
			
			//접미사가 Cheese라면 Set에 담음
            if (cheese.endsWith("Cheese")) {
                cheeseSet.add(cheese);
            }
        }

		//Set의 크기가 4 이상이면 가능, 아니면 불가능
        System.out.print(cheeseSet.size() > 3 ? "yummy" : "sad");
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}