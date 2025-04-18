//17932KB 192ms
import java.util.*;
public class Main {
	static int N;
	static String str1 = "\"재귀함수가 뭔가요?\"";
	static String str2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
	static String str3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지."; 
	static String str4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	static String str5 = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	static String str6 = "라고 답변하였지.";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recursive(0, "");
		sc.close();
	}

	public static void recursive(int num, String underBar){
		if (num == N){
			System.out.println(underBar + str1);
			System.out.println(underBar + str5);
			System.out.println(underBar + str6);
			return ;
		}
		System.out.println(underBar + str1);
		System.out.println(underBar + str2);
		System.out.println(underBar + str3);
		System.out.println(underBar + str4);

		recursive(num + 1, underBar + "____");
		System.out.println(underBar + str6);
	}
}
