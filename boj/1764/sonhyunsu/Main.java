//제출번호: 92559200
//메모리:   30436 KB
//실행시간: 412 ms
import java.io.*;
import java.util.*;

//그냥 듣도 못한 사람의 명단 안에 보도 못한 사람이 있는 지 판단하고
//있으면 듣도 보도 못한 사람이므로 잘 출력하면 되는 문제
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();

        //듣도 못한 사람의 명단을 Set으로 관리
		Set<String> aSet = new HashSet<>();
		for (int i = 0; i < n; i++) {
			aSet.add(nextString());
		}

        //듣도 보도 못한 사람을 저장할 배열
		List<String> res = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			String name = nextString();

            //보도 못한 사람이 듣도 못한 사람의 명단에 있으면 배열에 추가
			if (aSet.contains(name)) {
				res.add(name);
			}
		}

        //사전 순으로 정렬
		res.sort(Comparator.naturalOrder());

        //sb에 담기 귀찮아서 그냥 그대로 출력..
		System.out.println(res.size());
		res.forEach(System.out::println);
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

